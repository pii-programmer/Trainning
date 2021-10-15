package com.example.trainnning

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
import okhttp3.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    lateinit var db: AppDatabase
    lateinit var dao: WeatherDao
    lateinit var client: OkHttpClient

    private val spinnerItems = arrayOf("東京","埼玉","千葉")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapterToSpinner = ArrayAdapter(this,android.R.layout.simple_spinner_item, spinnerItems)
        adapterToSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapterToSpinner

        db = Room.databaseBuilder(this, AppDatabase::class.java, "weather_database").build()
        dao = db.WeatherDao()

        weatherButton.setOnClickListener {
            progressBar.visibility = View.VISIBLE

            GlobalScope.launch {
                withContext(Dispatchers.IO) {

                    dao.deleteAll()

                    client = OkHttpClient()
                    val request = Request.Builder().url("https://weather.tsukumijima.net/api/forecast?city=130010").build()

                    client.newCall(request).enqueue(object : Callback {
                        override fun onResponse(call: Call, response: Response) {
                            val jsonResponse = JSONObject(response.body?.string())
                            val description = jsonResponse.getString("description")
                            val descriptionData = description.split(",")
                            val headlineText2 = descriptionData[2]
                            val headlineTextData = headlineText2.split(":")
                            val headlineText = headlineTextData[1]
                            // TODO: headlineTextをinsertする
                        }
                        override fun onFailure(call: Call, e: IOException) {
                            Log.e("Error", e.toString())
                        }
                    })

//                    val datas = arrayListOf<Weather>()
//                    datas.map{
//                        Weather(
//                            description = it.description,
//                            forecasts = it.forecasts,
//                            copyright = it.copyright
//                        )
//                    } as MutableList<Weather>

// TODO: tableをネスト。親のtableで付与されてるidを、子tableで読み込みアソシエーションを組む。

//                    dao.insert(datas)
//
//                    val weatherList = dao.selectAll()
//
//                    withContext(Dispatchers.Main) {
//                        progressBar.visibility = View.GONE
//                        list_view.adapter = CustomAdapter(this@MainActivity, weatherList as ArrayList<Weather>)
//                    }
                }
            }
        }

        // リストビューのクリックリスナー
        list_view.setOnItemClickListener { parent: AdapterView<*>, _, position, _ ->
            val weatherIntent = parent.getItemAtPosition(position) as Weather
//            val state = DataState(weatherIntent.city, weatherIntent.dateLabel, weatherIntent.telop)

            Intent(this, SubActivity::class.java).apply {
//                this.putExtra("WEATHER_INTENT", state)
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
// } catch (e: JSONException) {
//   println(e.toString())
// }