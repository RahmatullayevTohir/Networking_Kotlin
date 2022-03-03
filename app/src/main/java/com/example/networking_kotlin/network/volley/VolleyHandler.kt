package com.example.networking_kotlin.network.volley

interface VolleyHandler {
    fun onSuccess(response:String)
    fun onError(error:String?)
}