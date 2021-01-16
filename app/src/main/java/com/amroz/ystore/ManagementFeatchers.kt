package com.amroz.ystore

import android.util.Log
import androidx.lifecycle.MutableLiveData
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

    /////////////////////UpdateCategories
    fun updateCategory(id:Int, category: String): MutableLiveData<CodeResponse> {
        val responseLiveData: MutableLiveData<CodeResponse> = MutableLiveData()
        var catUpdate = mangApi.updateCategory(id, category)
        catUpdate.enqueue(object : Callback<CodeResponse> {
            override fun onResponse(call: Call<CodeResponse>, response: retrofit2.Response<CodeResponse>) {

                Log.d("onResponse", "yes")
            }

            override fun onFailure(call: Call<CodeResponse>, t: Throwable) {
                Log.d("onResponse", t.message.toString())
            }


        })
        return responseLiveData

    }

    /////////////////////UpdateProfile
    fun updateProfile(id:Int, name: String,phone:String,address:String): MutableLiveData<CodeResponse> {
        val responseLiveData: MutableLiveData<CodeResponse> = MutableLiveData()
        var profileUpdate = mangApi.updateProfile(id,name,phone,address)
        profileUpdate.enqueue(object : Callback<CodeResponse> {
            override fun onResponse(call: Call<CodeResponse>, response: retrofit2.Response<CodeResponse>) {

                Log.d("onResponse", "UpdatedReceived")
            }

            override fun onFailure(call: Call<CodeResponse>, t: Throwable) {
                Log.d("onResponse", t.message.toString())
            }


        })
        return responseLiveData

    }
    ////////////////////////////////////////////UpdateProduct
    fun updateProduct(product_id:Int,
                      title: String,
                      details:String,
                      color:String,
                      product_features:String,
                      price_y:Int,
                      price_d:Int
                     // images:String
    )
            : MutableLiveData<CodeResponse> {
        val responseLiveData: MutableLiveData<CodeResponse> = MutableLiveData()
        var profileUpdate = mangApi.updateProduct(product_id,title,color,details,product_features,price_y,price_d)
        profileUpdate.enqueue(object : Callback<CodeResponse> {
            override fun onResponse(call: Call<CodeResponse>, response: retrofit2.Response<CodeResponse>) {

                Log.d("onResponseUpdate", "UpdatedReceived")
            }

            override fun onFailure(call: Call<CodeResponse>, t: Throwable) {
                Log.d("onResponse", t.message.toString())
            } })
        return responseLiveData
    }
    /////////////////////UpdateCart
    fun updateCart(id:Int, quntity: String): MutableLiveData<CodeResponse> {
        val responseLiveData: MutableLiveData<CodeResponse> = MutableLiveData()
        var cartUpdate = mangApi.updateCart(id,quntity)
        cartUpdate.enqueue(object : Callback<CodeResponse> {
            override fun onResponse(call: Call<CodeResponse>, response: retrofit2.Response<CodeResponse>) {

                Log.d("onResponse", "yes")
            }

            override fun onFailure(call: Call<CodeResponse>, t: Throwable) {
                Log.d("onResponse", t.message.toString())
            }


        })
        return responseLiveData


    }
}