package com.example.trainnning

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row_view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONException
import org.json.JSONObject
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    lateinit var db: AppDatabase
    lateinit var dao: IceCreamDao
    lateinit var http: OkHttp   //OkHttpクラスのインスタンス化を遅らせる

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        http = OkHttp()

        weatherButton.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            GlobalScope.launch {
                withContext(Dispatchers.IO) {
                    http.httpGet("https://weather.tsukumijima.net/api/forecast/city/130010")
                    // TODO: GETを実行後、responseBodyをDBに保存
                    withContext(Dispatchers.Main) {
                        resultText.text = http.responseBody.toString()
                        progressBar.visibility = View.GONE
                    }
                }
            }
        }

        val datas = arrayListOf<IceCream>()
        for (i in 0..100) {
            datas.add(IceCream(icon = "", title = "", text = "").apply {
                when (i % 3) {
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

        db = Room.databaseBuilder(this, AppDatabase::class.java, "iceCream_database").build()
        dao = db.iceCreamDao()

        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                dao.deleteAll()
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
                val iceList = dao.selectAll()

                withContext(Dispatchers.Main) {
                    list_view.adapter =
                        CustomAdapter(this@MainActivity, iceList as ArrayList<IceCream>)
                }
            }
        }
        // リストビューのクリックリスナー
        list_view.setOnItemClickListener { parent: AdapterView<*>, _, position, _ ->
            val iceCream = parent.getItemAtPosition(position) as IceCream
            val state = DataState(iceCream.icon, iceCream.title, iceCream.text)

            Intent(this, SubActivity::class.java).apply {
                this.putExtra("ICE_CREAM", state)
                startActivity(this)
            }
        }
    }
}

//try {
// } catch (e: Exception) {
//   println(e.toString())
// }