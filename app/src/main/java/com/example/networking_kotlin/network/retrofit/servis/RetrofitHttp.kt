package com.example.networking_kotlin.network.retrofit.servis

import com.example.networking_kotlin.network.volley.VolleyHttp.Companion.server
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitHttp {
    val IS_TESTER = true
    val SERVER_DEVELOPMENT = "https://jsonplaceholder.typicode.com/"
    val SERVER_PRODUCTION = "https://jsonplaceholder.typicode.com/"

    val retrofit = Retrofit.Builder().baseUrl(server()).addConverterFactory(GsonConverterFactory.create()).build()

    fun server():String{
        if (IS_TESTER) return SERVER_DEVELOPMENT
        return SERVER_PRODUCTION

    }

    val posterService: PosterService = retrofit.create(PosterService::class.java)
}