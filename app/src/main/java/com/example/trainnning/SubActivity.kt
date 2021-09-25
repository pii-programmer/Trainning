package com.example.trainnning

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_sub.*

class SubActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        val ice = intent.getSerializableExtra("ICE_CREAM")
        if (ice is DataState){
            when (ice.icon){
                "lemon" -> {
                    iceIcon.setImageResource(R.drawable.ice_lemon)
                    iceTitle.text = ice.title
                    iceText.text = ice.text
                }
                "strawberry" -> {
                    iceIcon.setImageResource(R.drawable.ice_strawberry)
                    iceTitle.text = ice.title
                    iceText.text = ice.text
                }
                "choco" -> {
                    iceIcon.setImageResource(R.drawable.ice_choco)
                    iceTitle.text = ice.title
                    iceText.text = ice.text
                }
            }
        }
    }
}