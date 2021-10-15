package com.example.trainnning

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import java.util.*

class CustomAdapter(context: Context, list: ArrayList<Weather>) : ArrayAdapter<Weather>(context,0,list){
    private lateinit var data:Weather

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null){
            //一行分のレイアウトを表示させておく
            view = LayoutInflater.from(context).inflate(R.layout.row_view, parent,false)
        }

        //一行分のデータを取得
        data = getItem(position) as Weather
        view?.findViewById<TextView>(R.id.text1)?.apply { text = data.description }
        view?.findViewById<TextView>(R.id.text2)?.apply { text = data.forecasts}
        view?.findViewById<TextView>(R.id.text3)?.apply { text = data.copyright }
        return view!!
    }
}

data class Data(
    var icon: String? = null,
    var title: String? = null,
    var text: String? = null
)

/** iconに画像を入れる場合 **/
// when (data.icon){
//            "strawberry" -> {
//                view?.findViewById<ImageView>(R.id.icon)?.setImageResource(R.drawable.ice_strawberry)
//            }
//            "lemon" -> {
//                view?.findViewById<ImageView>(R.id.icon)?.setImageResource(R.drawable.ice_lemon)
//            }
//            "choco" -> {
//                view?.findViewById<ImageView>(R.id.icon)?.setImageResource(R.drawable.ice_choco)
//            }
//        }