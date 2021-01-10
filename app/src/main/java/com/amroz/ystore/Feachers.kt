package com.amroz.ystore

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.amroz.ystore.Api.YstoerResponse
import com.amroz.ystore.Models.Report
import com.amroz.ystore.Api.YstoreApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Feachers {
    var  reportApi: YstoreApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.51/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        reportApi = retrofit.create(YstoreApi::class.java)
    }

    fun fetchReports(): LiveData<List<Report>> {
        val responseLiveData: MutableLiveData<List<Report>> = MutableLiveData()
        val newsRequest: Call<YstoerResponse> = reportApi.fetchReports()
        newsRequest.enqueue(object : Callback<YstoerResponse> {
            override fun onFailure(call: Call<YstoerResponse>, t: Throwable) {
                Log.e("TAG", "Failed to fetch reports", t)
            }
            override fun onResponse(call: Call<YstoerResponse>, response: Response<YstoerResponse>
            ) {

                val response: YstoerResponse? = response.body()
                val reports:List<Report> = response?.reports
                    ?: mutableListOf()
                Log.d("TAG", "Response received")
                responseLiveData.value = reports
                Log.d("onResponse", reports.toString())
            }
        })
        return responseLiveData

    }
}