package com.amroz.ystore


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.amroz.ystore.Api.CartResponse
import com.amroz.ystore.Api.CategoryResponse
import com.amroz.ystore.Models.Cart
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Call
import retrofit2.Callback

class Featchers {

    private val ystoreApi: YstoreApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.7:81/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        ystoreApi = retrofit.create(YstoreApi::class.java)
    }
//category
    fun fetchCategory(): LiveData<List<Category>> {
        val responseLiveData: MutableLiveData<List<Category>> = MutableLiveData()
        val catRequest: Call<CategoryResponse> = ystoreApi.fetchCategory()

        catRequest.enqueue(object : Callback<CategoryResponse> {

            override fun onFailure(call: Call<CategoryResponse>, t: Throwable) {
                Log.e("fetchcat", "Failed to fetch ",t)
            }

            override fun onResponse(call: Call<CategoryResponse>,

                                    response: Response<CategoryResponse>
            ) {
                val Response: CategoryResponse? = response.body()
                val users:List<Category> = Response?.Cat
                    ?: mutableListOf()
                Log.d("TAG", "Response received")
                responseLiveData.value = users
                Log.d("onResponse", users.toString())
            }
        })

       return responseLiveData
    }

    //cart
    fun fetchCart(): LiveData<List<Cart>> {
        val responseLiveData: MutableLiveData<List<Cart>> = MutableLiveData()
        val cartRequest: Call<CartResponse> =ystoreApi.fetchCart()

        cartRequest.enqueue(object : Callback<CartResponse> {

            override fun onFailure(call: Call<CartResponse>, t: Throwable) {
                Log.e("fetchcart", "Failed to fetch ",t)
            }

            override fun onResponse(call: Call<CartResponse>,

                                    response: Response<CartResponse>
            ) {
                val Response: CartResponse? = response.body()
                val users:List<Cart> = Response?.Cart
                    ?: mutableListOf()
                Log.d("TAG", "Response received")
                responseLiveData.value = users
                Log.d("onResponse", users.toString())
            }
        })

        return responseLiveData
    }
}


