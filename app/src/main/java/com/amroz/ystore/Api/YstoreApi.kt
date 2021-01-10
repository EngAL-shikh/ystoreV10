package com.amroz.ystore

import com.amroz.ystore.Api.CartResponse
import com.amroz.ystore.Api.CategoryResponse
import retrofit2.Call
import retrofit2.http.GET


interface YstoreApi {

    @GET("StoreApi/api/cat_api.php")
    fun fetchCategory(): Call<CategoryResponse>

    @GET("StoreApi/api/cart_api.php")
    fun fetchCart(): Call<CartResponse>
}
