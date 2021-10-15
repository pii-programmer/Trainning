package com.example.trainnning
//
//import android.util.Log
//import okhttp3.*
//import org.json.JSONObject
//import java.io.IOException
//
//class OkHttp() {
//    lateinit var client: OkHttpClient
//
//    fun httpGet(url:String) :String {
//        client = OkHttpClient()
//        val request = Request.Builder().url(url).build()
//
//        client.newCall(request).enqueue(object: Callback{
//            override fun onResponse(call: Call, response: Response) {
//                val jsonResponse = JSONObject(response.body?.string())
//            }
//
//            override fun onFailure(call: Call, e: IOException) {
//                Log.e("Error",e.toString())
//            }
//        })
//        return jsonResponse
//    }
//}
//  try {
//            //val json = JSONObject(responseBody)
//            //val jsonArray = json.getJSONArray("forecasts")
//            //val date = jsonArray.getJSONObject(0).getString("date")
//        } catch (e: JSONException){
//            e.printStackTrace()
//        }