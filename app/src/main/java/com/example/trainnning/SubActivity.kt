package com.example.trainnning

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_sub.*

class SubActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        val intentExtra = intent.getSerializableExtra("WEATHER_INTENT")
        if (intentExtra is DataState){
            when (intentExtra.city){
                "東京" -> {
                    cityTitle.text = intentExtra.city
                    telopText.text = intentExtra.telop
                }
                "千葉" -> {
                    cityTitle.text = intentExtra.city
                    telopText.text = intentExtra.telop
                }
                "埼玉" -> {
                    cityTitle.text = intentExtra.city
                    telopText.text = intentExtra.telop
                }
            }
        }
    }
}