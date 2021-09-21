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

        val datas = arrayListOf<IceCream>()
        for (i in 0..100){
            datas.add(IceCream(icon = "",title = "",text = "").apply {
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
                // delete
                dao.deleteAll()
                // insert
                /** 遠藤コメント **/
//                var test = mutableListOf<IceCream>()
//                var test = datas.map{
//                    IceCream(
//                            title = it.title,
//                            icon = it.icon,
//                            text = it.text
//                    )
//                } as MutableList<IceCream>
//                dao.inserAll()
                /** 遠藤コメント  ここまで**/
                dao.insert(datas)
                // select
                val iceList = dao.selectAll()

                withContext(Dispatchers.Main){
                    // アダプターをセット
                    list_view.adapter = CustomAdapter(this@MainActivity, iceList as ArrayList<IceCream>)
                }
            }
        }
        // リストビューのクリックリスナー
        list_view.setOnItemClickListener { parent: AdapterView<*>, _, position, _ ->
            val iceCream = parent.getItemAtPosition(position) as IceCream
            val state = DataState(iceCream.icon, iceCream.title, iceCream.text)

            Intent(this,SubActivity::class.java).apply {
                this.putExtra("ICE_CREAM", state)
                startActivity(this)
            }
        }
    }
}