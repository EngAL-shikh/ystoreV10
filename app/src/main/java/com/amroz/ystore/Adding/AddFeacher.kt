package com.amroz.ystore

import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.amroz.ystore.Api.YstoreApi

import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AddFeacher : AppCompatActivity() {
    private lateinit var addApi: YstoreApi

    init {
        val  gson=GsonBuilder().setLenient().create()

        val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))

            .baseUrl("http://172.16.16.246/")

            .build()

        addApi = retrofit.create(YstoreApi::class.java)
    }
    fun addCategory(cat_title:String,images:String) : MutableLiveData<Response>{
        val responseLiveData: MutableLiveData<Response> = MutableLiveData()
        val newsRequest: Call<Response> = addApi.addCategory(cat_title,images)
        newsRequest.enqueue(object : Callback<Response> {
            override fun onFailure(call: Call<Response>, t: Throwable) {
                Log.e("TAGADD", "Failed to post ", t)
            }
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>
            ) {


                Log.e("TAGADD", "Response received")

            }
        })
        return responseLiveData
    }
    fun addproduct(title:String,details:String,images:String,color:String,product_features:String,rating:Int,
                   price_y:Int,price_d:Int,user_id: Int,cat_id:Int,report_id:Int, order_data:String, data:String
    ): MutableLiveData<Response>{
        val responseLiveData: MutableLiveData<Response> = MutableLiveData()
        val newsRequest: Call<Response> = addApi.addProduct(title,details,images,color,
            product_features,rating,price_y,price_d,1,1,1,"","")
        newsRequest.enqueue(object : Callback<Response> {
            override fun onFailure(call: Call<Response>, t: Throwable) {
                Log.e("TAG", "Failed to post ", t)
            }
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>
            ) {


                Log.e("TAG", "Response received")

            }
        })
        return responseLiveData
    }
    fun addCart(user_id:Int,product_id:Int,Quantity:Int): MutableLiveData<Response> {
        val responseLiveData: MutableLiveData<Response> = MutableLiveData()
        val newsRequest: Call<Response> = addApi.addCart(user_id,product_id,Quantity)
        newsRequest.enqueue(object : Callback<Response> {
            override fun onFailure(call: Call<Response>, t: Throwable) {
                Log.e("TAG", "Failed to post ", t)
            }
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>
            ) {


                Log.e("TAG", "Response received")

            }
        })
        return responseLiveData
    }

    fun addUser(name: String, email: String,password: String,chat_id: String,
                phone: String,address: String): MutableLiveData<Response> {
        val responseLiveData: MutableLiveData<Response> = MutableLiveData()
        val newsRequest: Call<Response> = addApi.addUser(name,email,password,chat_id,phone,address)
        newsRequest.enqueue(object : Callback<Response> {
            override fun onFailure(call: Call<Response>, t: Throwable) {
                Log.e("TAG", "Failed to post ", t)
            }
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>
            ) {

                Toast.makeText(this@AddFeacher,"User Added",Toast.LENGTH_LONG).show()
                Log.e("TAG", "Response received")

            }
        })
        return responseLiveData
    }

}