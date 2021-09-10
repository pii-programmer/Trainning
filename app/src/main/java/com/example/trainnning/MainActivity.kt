package com.example.trainnning

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row_view.*
import com.example.trainnning.CustomAdapter
import com.example.trainnning.Data
import java.util.*

class MainActivity : AppCompatActivity() {
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
//        val database = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "iceCream_database").build()
//
//        // Roomはメインスレッドで実行できないので、コルーチンを使って実行する。
//        val scope = MainScope()
//        scope.async(Dispatchers.Main) {
//            val iceCreamDao = database.iceCreamDao()
//            iceCreamDao.insert(IceCream(1, "strawberry", "ストロベリーアイス", "¥100 おすすめ"))
//            iceCreamDao.selectAll()
//            return@async
//        }

        // アダプターをセットする
        val adapter = CustomAdapter(this, dataList)
        list_view.adapter = adapter

        list_view.setOnItemClickListener { parent: AdapterView<*>, _, position, _ ->
            val iceCream = parent.getItemAtPosition(position) as Data
            val state = DataState(iceCream.icon, iceCream.title, iceCream.text)
            Intent(this,SubActivity::class.java).apply{
                this.putExtra("ICE_CREAM", state)
                startActivity(this)
            }
        }
    }
}
//    Log.v("TAG", "${iceCreamDao.selectAll().toString()}")
//    suspend fun create():List<IceCream>{
//            return withContext(Dispatchers.IO){
//                val iceCreamDao = database.iceCreamDao()
//                iceCreamDao.insert(IceCream(1,"strawberry","ストロベリーアイス","¥100 おすすめ"))
//                iceCreamDao.selectAll()
//            }
//        }


//      アダプターをセットする
//        val adapter = CustomAdapter(this, dataList)
//        list_view.adapter = adapter

//      リストビューのクリックリスナ　　　　　　 // 使わない view と id は _ で伏せておく
//        list_view.setOnItemClickListener { parent: AdapterView<*>, _, position, _ ->
//            val iceCream = parent.getItemAtPosition(position) as Data
//            val state = DataState(iceCream.icon, iceCream.title, iceCream.text)
//            Intent(this, SubActivity::class.java).apply {
//                this.putExtra("ICE_CREAM", state)
//                startActivity(this)
//            }
//        }
