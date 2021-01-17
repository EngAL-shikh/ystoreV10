package com.amroz.ystore.Api

import com.amroz.ystore.CodeResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface YMangApi {





























































    /////////add///////////////////////////////////////
    @FormUrlEncoded
    @POST("StoreApi/api/cat_api.php")
    fun addCategory(@Field("cat_title") cat_title:String,
                    @Field("images") images:String): Call<CodeResponse>
    @FormUrlEncoded
    @POST("StoreApi/api/products_api.php")
    fun addProduct( @Field("title") title: String,
                    @Field("details") details: String,
                    @Field("images") images: String,
                    @Field("color") color: String,
                    @Field("product_features") product_features: String,
                    @Field("rating") rating: Int,
                    @Field("price_y") price_y:Double,
                    @Field("price_d") price_d: Double,
                    @Field("user_d") user_d: Int,
                     @Field("cat_d") cat_d: Int,
                    @Field("report_id") report_id: Int,
                    @Field("order_date") order_date: String,
                    @Field("date")date: String    ): Call<CodeResponse>
    @FormUrlEncoded
    @POST("StoreApi/api/cart_api.php")
    fun addCart(@Field("user_id:") user_id:Int,
                @Field("product_id") product_id:Int,
                @Field("Quantity") Quantity:Int
    ): Call<CodeResponse>
}
