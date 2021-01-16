package com.amroz.ystore

import android.util.Log
import com.amroz.ystore.Api.YMangApi
import com.amroz.ystore.Api.YstoreApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ManagementFeatchers {



    private val mangApi: YMangApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.7:81/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        mangApi= retrofit.create(YMangApi::class.java)
    }


    //DELETECATOGREY
    fun deleteCategory(id: Int) {


        val cartRequest: Call< CodeResponse> = mangApi.deleteCategory(id)

        cartRequest.enqueue(object : Callback< CodeResponse> {

            override fun onResponse(call: Call< CodeResponse>, response: retrofit2.Response< CodeResponse>) {
                Log.d("tag", "yes")
            }

            override fun onFailure(call: Call<CodeResponse>, t: Throwable) {
                Log.d("tag", t.message.toString())
            }

        })

    }


    //DELETEPRODUCT

    fun deleteProduct(id: Int) {


        val cartRequest: Call< CodeResponse> = mangApi.deleteProduct(id)

        cartRequest.enqueue(object : Callback< CodeResponse> {
            override fun onFailure(call: Call< CodeResponse>, t: Throwable) {
                Log.d("tag", t.message.toString())
            }


            override fun onResponse(call: Call< CodeResponse>, response: retrofit2.Response< CodeResponse>) {
                Log.d("tag", "yes")
            }

        })

    }
//DELETEUSER

    fun deleteUser(id: Int) {


        val cartRequest: Call< CodeResponse> = mangApi.deleteUser(id)

        cartRequest.enqueue(object : Callback< CodeResponse> {
            override fun onFailure(call: Call< CodeResponse>, t: Throwable) {
                Log.d("tag", t.message.toString())
            }


            override fun onResponse(call: Call< CodeResponse>, response: retrofit2.Response< CodeResponse>) {
                Log.d("tag", "yes")
            }

        })

    }


    //DELETCART

    fun deleteCart(id: Int) {


        val cartRequest: Call< CodeResponse> =mangApi.deletecart(id)

        cartRequest.enqueue(object : Callback< CodeResponse> {
            override fun onFailure(call: Call< CodeResponse>, t: Throwable) {
                Log.d("tag", t.message.toString())
            }


            override fun onResponse(call: Call< CodeResponse>, response: retrofit2.Response< CodeResponse>) {
                Log.d("tag", "yes")
            }

        })

    }

    //DELETEREPORT
    fun deletereports(id: Int) {


        val cartRequest: Call< CodeResponse> =mangApi.deletereports(id)

        cartRequest.enqueue(object : Callback< CodeResponse> {
            override fun onFailure(call: Call< CodeResponse>, t: Throwable) {
                Log.d("tag", t.message.toString())

            }


            override fun onResponse(call: Call< CodeResponse>, response: retrofit2.Response< CodeResponse>) {
                Log.d("tag", "yes")
            }

        })

    }
}