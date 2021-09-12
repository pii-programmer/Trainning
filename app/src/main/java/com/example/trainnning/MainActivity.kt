package com.example.trainnning

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import androidx.room.CoroutinesRoom
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row_view.*
import com.example.trainnning.CustomAdapter
import com.example.trainnning.Data
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.coroutines.coroutineContext

class MainActivity : AppCompatActivity() {
    lateinit var db: AppDatabase
    lateinit var dao: IceCreamDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // カスタムアダプターに渡すData
        val dataList = arrayListOf<Data>()
        for (i in 0..100){
            dataList.add(Data().apply {
                when{
                    i %3 == 0 -> {
                        icon = "strawberry"
                        title = "ストロベリーアイス"
                        text = "¥100 おすすめ"
                    }
                    i %2 == 0 -> {
                        icon = "lemon"
                        title = "レモンアイス"
                        text = "¥200 爽やか"
                    }
                    else -> {
                        icon = "choco"
                        title = "チョコアイス"
                        text = "¥300 濃厚チョコ"
                    }
                }
            })
        }

        // Roomで作ったDBを初期化する
        db = Room.databaseBuilder(this, AppDatabase::class.java, "iceCream_database").build()
        dao = db.iceCreamDao()

        // アダプターをセット
        val adapter = CustomAdapter(this, dataList)
        list_view.adapter = adapter

        // リストビューのクリックリスナー
        list_view.setOnItemClickListener { parent: AdapterView<*>, _, position, _ ->
            val iceCream = parent.getItemAtPosition(position) as Data
            val state = DataState(iceCream.icon, iceCream.title, iceCream.text)

            // DB処理をコルーチンのDispatcherIOで実行する
            GlobalScope.launch {
                withContext(Dispatchers.IO) {
                    when(state.icon){
                        "strawberry" -> {
                            val strawberryIce = IceCream(1, "strawberry", "ストロベリーアイス", "¥100 おすすめ")
                            dao.insert(strawberryIce)
                        }
                        "lemon" -> {
                            val lemonIce = IceCream(2, "lemon", "レモンアイス", "¥200 爽やか")
                            dao.insert(lemonIce)
                        }
                        "choco" -> {
                            val chocoIce = IceCream(3, "choco", "チョコアイス", "¥300 濃厚チョコ")
                            dao.insert(chocoIce)
                        }
                    }
                    val select = dao.selectAll()
                    println(select)
                }
            }
            // メインスレッドでIntent
            val ice = CoroutinesRoom.toString()
            Intent(this,SubActivity::class.java).apply {
                this.putExtra("ICE_CREAM", ice)
                startActivity(this)
            }
        }
    }
}

//    Log.v("TAG", "${iceCreamDao.selectAll().toString()}")
//      アダプターをセットする
//        val adapter = CustomAdapter(this, dataList)
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