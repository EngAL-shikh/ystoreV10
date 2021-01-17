package com.amroz.ystore

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.amroz.ystore.Api.YMangApi
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ManagementFeatchers {
    private lateinit var mangApi: YMangApi

    init {
        val  gson=GsonBuilder().setLenient().create()

        val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("http://10.0.1.124/")
            .build()

        mangApi = retrofit.create(YMangApi::class.java)
    }

































































































































































































































    ///////////////////////////////////////// add category///////////////////////////////
    fun addCategory(cat_title:String,images:String) : MutableLiveData<CodeResponse>{
        val responseLiveData: MutableLiveData<CodeResponse> = MutableLiveData()
        val newsRequest: Call<CodeResponse> =  mangApi.addCategory(cat_title,images)
        newsRequest.enqueue(object : Callback<CodeResponse> {
            override fun onFailure(call: Call<CodeResponse>, t: Throwable) {
                Log.e("TAG", "Failed to post ", t)
            }
            override fun onResponse(call: Call<CodeResponse>, response: retrofit2.Response<CodeResponse>
            ) {


                Log.e("TAG", "Response received")

            }
        })
        return responseLiveData
    }
    fun addproduct(title:String,details:String,images:String,color:String,product_features:String,rating:Int,
                   price_y:Double,price_d:Double,user_id: Int,cat_id:Int,report_id:Int, order_data:String, data:String
    ): MutableLiveData<CodeResponse>{
        val responseLiveData: MutableLiveData<CodeResponse> = MutableLiveData()
        val newsRequest: Call<CodeResponse> =  mangApi.addProduct(title,details,images,color,
            product_features,rating,price_y,price_d,1,1,1,"","")
        newsRequest.enqueue(object : Callback<CodeResponse> {
            override fun onFailure(call: Call<CodeResponse>, t: Throwable) {
                Log.e("TAG", "Failed to post ", t)
            }
            override fun onResponse(call: Call<CodeResponse>, response: retrofit2.Response<CodeResponse>
            ) {


                Log.e("TAG", "Response received")

            }
        })
        return responseLiveData
    }
    fun addCart(user_id:Int,product_id:Int,Quantity:Int): MutableLiveData<CodeResponse> {
        val responseLiveData: MutableLiveData<CodeResponse> = MutableLiveData()
        val newsRequest: Call<CodeResponse> = mangApi.addCart(user_id,product_id,Quantity)
        newsRequest.enqueue(object : Callback<CodeResponse> {
            override fun onFailure(call: Call<CodeResponse>, t: Throwable) {
                Log.e("TAG", "Failed to post ", t)
            }
            override fun onResponse(call: Call<CodeResponse>, response: retrofit2.Response<CodeResponse>
            ) {


                Log.e("TAG", "Response received")

            }
        })
        return responseLiveData
    }

}