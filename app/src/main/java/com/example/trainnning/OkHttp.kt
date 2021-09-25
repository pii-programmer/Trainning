package com.example.trainnning

import okhttp3.*

class OkHttp() {
    lateinit var client: OkHttpClient
    lateinit var request: Request

    fun httpGet() {
        // clientの初期化
        client = OkHttpClient()
        // requestの初期化
        request = Request.Builder().url("https://weather.tsukumijima.net/api/forecast/city/130010").build()

        // requestの実行
        val response = client.newCall(request).execute()
        val body = response.body as Unit
        return body
    }
}