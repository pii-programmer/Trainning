package com.example.trainnning

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
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
    lateinit var dao: WeatherDao
    lateinit var http: OkHttp

    private val spinnerItems = arrayOf("東京","埼玉","千葉")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapterToSpinner = ArrayAdapter(this,android.R.layout.simple_spinner_item, spinnerItems)
        adapterToSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapterToSpinner

        db = Room.databaseBuilder(this, AppDatabase::class.java, "weather_database").build()
        dao = db.WeatherDao()
        http = OkHttp()

        weatherButton.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            val datas = arrayListOf<Weather>()

            GlobalScope.launch {
                withContext(Dispatchers.IO) {
                    http.httpGet("https://weather.tsukumijima.net/api/forecast/city/130010")
                    dao.deleteAll()
                    datas.map{
                        Weather(
                            city = it.city,
                            dateLabel = it.dateLabel,
                            telop = it.telop
                        )
                    } as MutableList<Weather>
                    dao.insertAll()
                    val weatherList = dao.selectAll()

                    withContext(Dispatchers.Main) {
                        list_view.adapter = CustomAdapter(this@MainActivity, weatherList as ArrayList<Weather>)
                        progressBar.visibility = View.GONE
                    }
                }
            }
        }

        // リストビューのクリックリスナー
        list_view.setOnItemClickListener { parent: AdapterView<*>, _, position, _ ->
            val weatherIntent = parent.getItemAtPosition(position) as Weather
            val state = DataState(weatherIntent.city, weatherIntent.dateLabel, weatherIntent.telop)

            Intent(this, SubActivity::class.java).apply {
                this.putExtra("WEATHER_INTENT", state)
                startActivity(this)
            }
        }
    }
}

/** 遠藤コメント **/
//                var test = mutableListOf<IceCream>()
//                var test = datas.map{
//                    IceCream(
//                            title = it.title,
//                            icon = it.icon,
//                            text = it.text
//                    )
//                } as MutableList<IceCream>
//                dao.insertAll()
/** 遠藤コメント  ここまで**/
//try {
// } catch (e: Exception) {
//   println(e.toString())
// }