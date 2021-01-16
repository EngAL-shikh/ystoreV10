
package com.amroz.ystore.Api

import com.amroz.ystore.CodeResponse
import com.amroz.ystore.Response
import retrofit2.Call

import retrofit2.http.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query



interface YstoreApi {

    @GET("StoreApi/api/cat_api.php")
    fun fetchCategory(): Call<Response>

    @GET("StoreApi/api/cart_api.php")
    fun fetchCart(): Call<Response>

    @GET("StoreApi/api/reports_api.php")
    fun fetchReports(): Call<Response>

    @GET("StoreApi/api/products_api.php")
    fun fetchProducts(): Call<Response>

    @GET("StoreApi/api/Users_api.php")
    fun fetchUsers(): Call<Response>

    @GET("/StoreApi/api/products_api.php")
    fun fetchProductsByCat(@Query("cat_id") cat_id: Int): Call<Response>

    @GET("StoreApi/api/Users_api.php?")
    fun fetchSingleUsers(@Query("user_id") user_id: Int): Call<Response>


}

