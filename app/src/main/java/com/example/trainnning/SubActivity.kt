package com.example.trainnning

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_sub.*

class SubActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        val state = intent.getSerializableExtra("ICE_CREAM")
        if (state is DataState){
            when (state.icon){
                "lemon" -> {
                    iceIcon.setImageResource(R.drawable.ice_lemon)
                    iceTitle.text = state.title
                    iceText.text = state.text
                }
                "strawberry" -> {
                    iceIcon.setImageResource(R.drawable.ice_strawberry)
                    iceTitle.text = state.title
                    iceText.text = state.text
                }
                "choco" -> {
                    iceIcon.setImageResource(R.drawable.ice_choco)
                    iceTitle.text = state.title
                    iceText.text = state.text
                }
            }
        }

    }
}