package com.example.networking_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.networking_kotlin.model.Poster
import com.example.networking_kotlin.model.PosterResp
import com.example.networking_kotlin.network.retrofit.servis.RetrofitHttp
import com.example.networking_kotlin.network.volley.VolleyHandler
import com.example.networking_kotlin.network.volley.VolleyHttp
import com.example.networking_kotlin.utils.Logger
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initVews()

        workWithRetrofit()
    }

    private fun initVews() {

        var text = findViewById<TextView>(R.id.text)
        var text2 = findViewById<TextView>(R.id.text2)
//        VolleyHttp.get(VolleyHttp.API_LIST_POST, VolleyHttp.paramsEmpty(),object :VolleyHandler{
//            override fun onSuccess(response: String) {
//                Logger.d("VolleyHttp", response!!)
//                text.text = response
//            }
//
//            override fun onError(error: String?) {
//                Logger.d("VolleyHttp", error!!)
//            }
//
//        })


        val poster = Poster(1, 2, "PDP", "Online")
        VolleyHttp.post(VolleyHttp.API_CREATE_POST, VolleyHttp.paramsCreate(poster),object :
        VolleyHandler{
            override fun onSuccess(response: String) {
                Logger.d("@@@", response!!)
                text.text = response
            }

            override fun onError(error: String?) {
                Logger.d("@@@", error!!)
            }

        })

        val poster2 = Poster(1, 25, "Tohir", "Online")
        VolleyHttp.put(VolleyHttp.API_UPDATE_POST+poster.id, VolleyHttp.paramsUpdate(poster2),object :
            VolleyHandler{
            override fun onSuccess(response: String) {
                Logger.d("@@@2", response!!)
                text2.text = response
            }

            override fun onError(error: String?) {
                Logger.d("@@@2", error!!)
            }

        })


        VolleyHttp.del(VolleyHttp.API_DELETE_POST+poster.id,object :
            VolleyHandler{
            override fun onSuccess(response: String) {
                Logger.d("@@@2", response!!)
//                text2.text = response
            }

            override fun onError(error: String?) {
                Logger.d("@@@2", error!!)
            }

        })
    }

    fun workWithRetrofit(){

        var text3 = findViewById<TextView>(R.id.text3)

        RetrofitHttp.posterService.listPost().enqueue(object : Callback<ArrayList<PosterResp>> {
            override fun onResponse(call: Call<ArrayList<PosterResp>>, response: Response<ArrayList<PosterResp>>) {
                Log.d("@@@", response.body().toString())
                text3.text = response.body().toString()
            }

            override fun onFailure(call: Call<ArrayList<PosterResp>>, t: Throwable) {
                Log.d("@@@", t.message.toString())
            }
        })
    }
}