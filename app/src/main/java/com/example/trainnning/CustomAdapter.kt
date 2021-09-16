package com.example.trainnning

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import java.util.*

class CustomAdapter(context: Context, list: ArrayList<Data>) : ArrayAdapter<Data>(context,0,list){
    private lateinit var data:Data

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null){
            //一行分のレイアウトを表示させておく
            view = LayoutInflater.from(context).inflate(R.layout.row_view, parent,false)
        }

        //一行分のデータを取得
        data = getItem(position) as Data    // 今回はここで data:Data の初期化が完了している
        when (data.icon){
            "strawberry" -> {
                view?.findViewById<ImageView>(R.id.icon)?.setImageResource(R.drawable.ice_strawberry)
            }
            "lemon" -> {
                view?.findViewById<ImageView>(R.id.icon)?.setImageResource(R.drawable.ice_lemon)
            }
            "choco" -> {
                view?.findViewById<ImageView>(R.id.icon)?.setImageResource(R.drawable.ice_choco)
            }
        }
        view?.findViewById<TextView>(R.id.title)?.apply { text = data.title }
        view?.findViewById<TextView>(R.id.text)?.apply { text = data.text}
        return view!!
    }
}
// データ型のDataクラス
data class Data(
    var icon: String? = null,
    var title: String? = null,
    var text: String? = null
)