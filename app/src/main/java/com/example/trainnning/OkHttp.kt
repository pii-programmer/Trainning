package com.example.trainnning

import okhttp3.*
import org.json.JSONObject

class OkHttp {
    lateinit var client: OkHttpClient
    lateinit var request: Request
    lateinit var response: Response
    lateinit var responseBody: ResponseBody
    // FIXME
    //  lateinit var responseJSONObject: JSONObject

    fun httpGet(url:String): ResponseBody {
        client = OkHttpClient()
        request = Request.Builder().url(url).get().build()
        response = client.newCall(request).execute()

        responseBody = response.body!!  // WARNING

        return responseBody
    }
}
// FIXME
//        val json = JSONObject(response.body as String)
//        return json
//        responseJSONObject = json.getJSONObject("forecasts").getJSONObject("date")

//    companion object{
//        val JSON = "application/json; charset=utf-8".toMediaTypeOrNull()
//    }

//                        try{
//
//                        }catch (e: JSONException){
//                            e.printStackTrace()
//                        }