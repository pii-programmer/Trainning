package com.example.trainnning

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row_view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    lateinit var db: AppDatabase
    lateinit var dao: IceCreamDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data = arrayListOf<Data>()
        for (i in 0..100){
            data.add(Data().apply {
                when(i % 3){
                    0 -> {
                        icon = "strawberry"
                        title = "ストロベリーアイス"
                        text = "おすすめ"
                    }
                    1 -> {
                        icon = "lemon"
                        title = "レモンアイス"
                        text = "爽やか"
                    }
                    else -> {
                        icon = "choco"
                        title = "チョコアイス"
                        text = "甘いよ"
                    }
                }
            })
        }
        // Roomで作ったDBを初期化する
        db = Room.databaseBuilder(this, AppDatabase::class.java, "iceCream_database").build()
        dao = db.iceCreamDao()
        // DB処理をDispatchersIOで実行する
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                // insert
                val ice = IceCream(icon = "strawberry",title = "ストロベリーアイス",text = "おすすめ")
                dao.insert(ice)
                // select
                val iceList = arrayListOf<Data>()
                iceList.add(Data().apply{dao.selectAll()})
                // delete
                dao.deleteAll()
                withContext(Dispatchers.Main){
                    // アダプターをセット
                    list_view.adapter = CustomAdapter(this@MainActivity, iceList)
                }
            }
        }
        // リストビューのクリックリスナー
        list_view.setOnItemClickListener { parent: AdapterView<*>, _, position, _ ->
            val iceCream = parent.getItemAtPosition(position) as Data
            val state = DataState(iceCream.icon, iceCream.title, iceCream.text)

            Intent(this,SubActivity::class.java).apply {
                this.putExtra("ICE_CREAM", state)
                startActivity(this)
            }
        }
    }
}

// try{ DBの処理 } catch(e:ClassCastException){ println("キャスト失敗") }
// DBは例外が起きやすいためtry catchが最適
//      アダプターをセットする
//        val adapter = CustomAdapter(this, data)
//        list_view.adapter = adapter
//      Intent処理
//      list_view.setOnItemClickListener { parent: AdapterView<*>, _, position, _ ->
//            val iceCream = parent.getItemAtPosition(position) as Data
//            val state = DataState(iceCream.icon, iceCream.title, iceCream.text)
//            Intent(this,SubActivity::class.java).apply{
//                this.putExtra("ICE_CREAM", state)
//                startActivity(this)
//            }
//        }