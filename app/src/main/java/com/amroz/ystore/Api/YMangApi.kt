package com.amroz.ystore.Api

import com.amroz.ystore.*
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.Query

interface YMangApi {


    @DELETE("StoreApi/api/cat_api.php")
    fun  deleteCategory(@Query("cat_id")id:Int): Call<CodeResponse>

    @DELETE("StoreApi/api/users_api.php")
    fun  deleteUser(@Query("user_id")id:Int): Call< CodeResponse>

    @DELETE("StoreApi/api/products_api.php")
    fun  deleteProduct(@Query("product_id")id:Int): Call< CodeResponse>

    @DELETE("StoreApi/api/cart_api.php")
    fun  deletecart(@Query("user_id")id:Int): Call<CodeResponse>

    @DELETE("StoreApi/api/reports_api.php")
    fun  deletereports(@Query("report_id")id:Int): Call<CodeResponse>
}