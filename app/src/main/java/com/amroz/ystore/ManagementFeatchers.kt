package com.amroz.ystore

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.amroz.ystore.Api.YstoreApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



class ManagementFeatchers {



    private val mangApi: YstoreApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()

            .baseUrl("http://192.168.137.1/")

            .addConverterFactory(GsonConverterFactory.create())
            .build()

        mangApi= retrofit.create(YstoreApi::class.java)
    }



    //DELETECATOGREY
    fun deleteCategory(id: Int) {


        val cartRequest: Call<Response> = mangApi.deleteCategory(id)

        cartRequest.enqueue(object : Callback<Response> {

            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                Log.d("tag", "deleted")
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                Log.d("tag", t.message.toString())
            }

        })

    }


    //DELETEPRODUCT

    fun deleteProduct(id: Int) {


        val cartRequest: Call< Response> = mangApi.deleteProduct(id)

        cartRequest.enqueue(object : Callback< Response> {
            override fun onFailure(call: Call< Response>, t: Throwable) {
                Log.d("tag", t.message.toString())
            }


            override fun onResponse(call: Call< Response>, response: retrofit2.Response< Response>) {
                Log.d("tag", "yes")
            }

        })

    }
//DELETEUSER

    fun deleteUser(id: Int) {


        val cartRequest: Call< Response> = mangApi.deleteUser(id)

        cartRequest.enqueue(object : Callback< Response> {
            override fun onFailure(call: Call< Response>, t: Throwable) {
                Log.d("tag", t.message.toString())
            }


            override fun onResponse(call: Call< Response>, response: retrofit2.Response< Response>) {
                Log.d("tag", "yes")
            }

        })

    }


    //DELETCART

    fun deleteCart(id: Int) {


        val cartRequest: Call< Response> =mangApi.deletecart(id)

        cartRequest.enqueue(object : Callback< Response> {
            override fun onFailure(call: Call< Response>, t: Throwable) {
                Log.d("tag", t.message.toString())
            }


            override fun onResponse(call: Call< Response>, response: retrofit2.Response< Response>) {
                Log.d("tag", "yes")
            }

        })

    }

    //DELETEREPORT
    fun deletereports(id: Int) {


        val cartRequest: Call< Response> =mangApi.deletereports(id)

        cartRequest.enqueue(object : Callback< Response> {
            override fun onFailure(call: Call< Response>, t: Throwable) {
                Log.d("tag", t.message.toString())

            }


            override fun onResponse(call: Call< Response>, response: retrofit2.Response< Response>) {
                Log.d("tag", "yes")
            }

        })






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
            : MutableLiveData<Response> {
        val responseLiveData: MutableLiveData<Response> = MutableLiveData()
        var profileUpdate = mangApi.updateProduct(product_id,title,color,details,product_features,price_y,price_d)
        profileUpdate.enqueue(object : Callback<Response> {
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {

                Log.d("onResponseUpdate", "UpdatedReceived")
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                Log.d("onResponse", t.message.toString())
            } })
        return responseLiveData
    }

    /////////////////////UpdateCart
    fun updateCart(id:Int, quntity: String): MutableLiveData<Response> {
        val responseLiveData: MutableLiveData<Response> = MutableLiveData()
        var cartUpdate = mangApi.updateCart(id,quntity)
        cartUpdate.enqueue(object : Callback<Response> {
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {

                Log.d("onResponse", "yes")
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                Log.d("onResponse", t.message.toString())
            }


        })
        return responseLiveData


    }


    /////////////////////UpdateProfile
    fun updateProfile(id:Int, name: String,email:String,phone:String,address:String): MutableLiveData<Response> {
        val responseLiveData: MutableLiveData<Response> = MutableLiveData()
        var profileUpdate = mangApi.updateProfile(id,name,email,phone,address)
        profileUpdate.enqueue(object : Callback<Response> {
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {

                Log.d("onResponse", "UpdatedReceived")
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                Log.d("onResponse", t.message.toString())
            }


        })
        return responseLiveData

    }
    /////////////////////UpdateCategories
    fun updateCategory(id:Int, category: String): MutableLiveData<Response> {
        val responseLiveData: MutableLiveData<Response> = MutableLiveData()
        var catUpdate = mangApi.updateCategory(id, category)
        catUpdate.enqueue(object : Callback<Response> {
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {

                Log.d("onResponse", "yes")
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                Log.d("onResponse", t.message.toString())
            }


        })
        return responseLiveData

    }
}