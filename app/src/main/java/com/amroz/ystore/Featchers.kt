package com.amroz.ystore



import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.amroz.ystore.Api.YstoreApi
import com.amroz.ystore.Models.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
class Featchers {

    private val ystoreApi: YstoreApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.1.124/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        ystoreApi = retrofit.create(YstoreApi::class.java)
    }




    //products
    fun fetchProducts(): LiveData<List<Products>> {
        val responseLiveData: MutableLiveData<List<Products>> = MutableLiveData()
        val newsRequest: Call<Response> = ystoreApi.fetchReports()
        newsRequest.enqueue(object : Callback<Response> {
            override fun onFailure(call: Call<Response>, t: Throwable) {
                Log.e("TAG", "Failed to fetch ", t)
            }
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>
            ) {

                val Response:Response? = response.body()
                val products:List<Products> = Response?.products
                    ?: mutableListOf()
                Log.d("TAG", "Response received")
                responseLiveData.value = products
                Log.d("onResponse", products.toString())
            }
        })
        return responseLiveData

    }
//category
fun fetchCat(): LiveData<List<Category>> {

    val responseLiveData: MutableLiveData<List<Category>> = MutableLiveData()
    val ystoreRequest: Call<Response> = ystoreApi.fetchCategory()
    ystoreRequest.enqueue(object : Callback<Response> {
        override fun onFailure(call: Call<Response>, t: Throwable) {
            Log.e("TAG", "Failed to fetch News", t)
        }
        override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>
        ) {

            val response:Response? = response.body()
            val cats:List<Category> = response?.cats
                ?: mutableListOf()
            Log.d("TAG", "Response received")
            responseLiveData.value = cats
            Log.d("onResponse", cats.toString())
        }
    })
    return responseLiveData

}

    //cart
    fun fetchCart(): LiveData<List<Cart>> {
        val responseLiveData: MutableLiveData<List<Cart>> = MutableLiveData()
        val newsRequest: Call<Response> = ystoreApi.fetchCart()
        newsRequest.enqueue(object : Callback<Response> {
            override fun onFailure(call: Call<Response>, t: Throwable) {
                Log.e("TAG", "Failed to fetch News", t)
            }
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>
            ) {

                val response:Response? = response.body()
                val carts:List<Cart> = response?.carts
                    ?: mutableListOf()
                Log.d("TAG", "Response received")
                responseLiveData.value = carts
                Log.d("onResponse", carts.toString())
            }
        })
        return responseLiveData

    }

    //reports
    fun fetchReport(): LiveData<List<Report>> {
        val responseLiveData: MutableLiveData<List<Report>> = MutableLiveData()
        val newsRequest: Call<Response> = ystoreApi.fetchCart()
        newsRequest.enqueue(object : Callback<Response> {
            override fun onFailure(call: Call<Response>, t: Throwable) {
                Log.e("TAG", "Failed to fetch Report", t)
            }
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>
            ) {

                val response:Response? = response.body()
                val reports:List<Report> = response?.reports
                    ?: mutableListOf()
                Log.d("TAG", "Response received")
                responseLiveData.value = reports
                Log.d("onResponse", reports.toString())
            }
        })
        return responseLiveData

    }

    //users

    fun fetchUsers(): LiveData<List<Users>> {
        val responseLiveData: MutableLiveData<List<Users>> = MutableLiveData()
        val newsRequest: Call<Response> = ystoreApi.fetchUsers()
        newsRequest.enqueue(object : Callback<Response> {
            override fun onFailure(call: Call<Response>, t: Throwable) {
                Log.e("TAG", "Failed to fetch ", t)
            }
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>
            ) {

                val response:Response? = response.body()
                val reports:List<Users> = response?.users
                    ?: mutableListOf()
                Log.d("TAG", "Response received")
                responseLiveData.value = reports
                Log.d("onResponse", reports.toString())
            }
        })
        return responseLiveData

    }



}


