
package com.amroz.ystore.Api

import com.amroz.ystore.Category
import com.amroz.ystore.CodeRedponse
import com.amroz.ystore.Models.Cart
import com.amroz.ystore.Models.Products
import com.amroz.ystore.Response
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


}

