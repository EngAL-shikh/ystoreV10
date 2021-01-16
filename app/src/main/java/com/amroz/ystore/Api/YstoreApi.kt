
package com.amroz.ystore.Api

import com.amroz.ystore.*
import retrofit2.Call
import retrofit2.http.*


interface YstoreApi {

    @GET("StoreApi/api/cat_api.php")
    fun fetchCategory(): Call<Response>

    @GET("StoreApi/api/cart_api.php")
    fun fetchCart(): Call<Response>

    @GET("StoreApi/api/reports_api.php")
    fun fetchReports(): Call<Response>

    @GET("StoreApi/api/products_api.php")
    fun fetchProducts(): Call<Response>

    @GET("StoreApi/api/products_api.php")
    fun fetchUsers(): Call<Response>

    @POST
    fun xyz(@Body x:HashMap<String,String>)


}

