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
open class Featchers {

    val ystoreApi: YstoreApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()

            .baseUrl("http://192.168.1.3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        ystoreApi = retrofit.create(YstoreApi::class.java)
    }


    //products
    fun fetchProducts(): LiveData<List<Products>> {
        val responseLiveData: MutableLiveData<List<Products>> = MutableLiveData()
        val ystoreRequest: Call<Response> = ystoreApi.fetchProducts()
        ystoreRequest.enqueue(object : Callback<Response> {
            override fun onFailure(call: Call<Response>, t: Throwable) {
                Log.e("TAG", "Failed to fetch ", t)
            }
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>
            ) {

                val response:Response? = response.body()
                val products:List<Products> = response?.products
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
    //cart
    fun fetchCart(user_id:Int): LiveData<List<Products>> {
        val responseLiveData: MutableLiveData<List<Products>> = MutableLiveData()
        val newsRequest: Call<Response> = ystoreApi.fetchCart(user_id)
        newsRequest.enqueue(object : Callback<Response> {
            override fun onFailure(call: Call<Response>, t: Throwable) {
                Log.e("TAG", "Failed to fetch ", t)
            }
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>
            ) {

                val response:Response? = response.body()
                val carts:List<Products> = response?.carts
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
        val reportRequest: Call<Response> = ystoreApi.fetchReports()
        reportRequest.enqueue(object : Callback<Response> {
            override fun onFailure(call: Call<Response>, t: Throwable) {
                Log.e("TAG", "Failed to fetch ", t)
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
                val users:List<Users> = response?.users
                    ?: mutableListOf()
                Log.d("TAG", "Response received")
                responseLiveData.value = users
                Log.d("onResponse", users.toString())
            }
        })
        return responseLiveData

    }
    fun fetchProductsByCat(cat_id:Int): LiveData<List<Products>> {
        val responseLiveData: MutableLiveData<List<Products>> = MutableLiveData()
        val ystoreRequest: Call<Response> = ystoreApi.fetchProductsByCat(cat_id)
        ystoreRequest.enqueue(object : Callback<Response> {
            override fun onFailure(call: Call<Response>, t: Throwable) {
                Log.e("TAG", "Failed to fetch ", t)
            }
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>
            ) {

                val response:Response? = response.body()
                val products:List<Products> = response?.productByCat
                    ?: mutableListOf()
                Log.d("TAG", "Response received")
                responseLiveData.value = products
                Log.d("onResponse", products.toString())
            }
        })
        return responseLiveData

    }

    //get userinfo
    fun fetchUsersInfo(id:Int): LiveData<List<Users>> {
        val responseLiveData: MutableLiveData<List<Users>> = MutableLiveData()
        val ystoreRequest: Call<Response> = ystoreApi.fetchSingleUsers(id)
        ystoreRequest.enqueue(object : Callback<Response> {
            override fun onFailure(call: Call<Response>, t: Throwable) {
                Log.e("TAG", "Failed to fetch ", t)
            }
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>
            ) {

                val response:Response? = response.body()
                val users:List<Users> = response?.singleUsers
                    ?: mutableListOf()
                Log.d("TAG", "Response received")
                responseLiveData.value = users
                Log.d("onResponse", users.toString())
            }
        })
        return responseLiveData

    }


    ///////////////////fetchRating

    fun fetchRating(): LiveData<List<RatingUs>> {
        val responseLiveData: MutableLiveData<List<RatingUs>> = MutableLiveData()
        val reportRequest: Call<Response> = ystoreApi.fetchRating()
        reportRequest.enqueue(object : Callback<Response> {
            override fun onFailure(call: Call<Response>, t: Throwable) {
                Log.e("TAG", "Failed to fetch ", t)
            }
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>
            ) {
                val response:Response? = response.body()
                val ratingUs:List<RatingUs> = response?.rating
                    ?: mutableListOf()
                Log.d("TAG", "Response received")
                responseLiveData.value =ratingUs
                Log.d("onResponse", ratingUs.toString())
            }
        })
        return responseLiveData

    }


    //get id by email
    fun fetchUsersInfoBYemail(email:String): LiveData<List<Users>> {
        val responseLiveData: MutableLiveData<List<Users>> = MutableLiveData()
        val ystoreRequest: Call<Response> = ystoreApi.fetchSingleUsersbyemail(email)
        ystoreRequest.enqueue(object : Callback<Response> {
            override fun onFailure(call: Call<Response>, t: Throwable) {
                Log.e("TAG", "Failed to fetch ", t)
            }
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>
            ) {

                val response:Response? = response.body()
                val users:List<Users> = response?.getSingleuUserid
                    ?: mutableListOf()
                Log.d("TAG", "Response received")
                responseLiveData.value = users
                Log.d("onResponse", users.toString())
            }
        })
        return responseLiveData

    }


    fun fetchProductsByUser(user_id:Int): LiveData<List<Products>> {
        val responseLiveData: MutableLiveData<List<Products>> = MutableLiveData()
        val ystoreRequest: Call<Response> = ystoreApi.fetchProductsByUser(user_id)
        ystoreRequest.enqueue(object : Callback<Response> {
            override fun onFailure(call: Call<Response>, t: Throwable) {
                Log.e("TAG", "Failed to fetch ", t)
            }
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>
            ) {

                val response:Response? = response.body()
                val products:List<Products> = response?.productByUser
                    ?: mutableListOf()
                Log.d("TAG", "Response received")
                responseLiveData.value = products
                Log.d("onResponse", products.toString())
            }
        })
        return responseLiveData

    }

}



