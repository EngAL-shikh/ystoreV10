package com.amroz.ystore.Api

import com.amroz.ystore.CodeResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.PUT
import retrofit2.http.Query

interface YMangApi {
















    //////////////////////////////Update Category
    @FormUrlEncoded
    @PUT("StoreApi/api/cat_api.php")
    fun updateCategory(@Query("cat_id") cat_id: Int?,
                       @Field("cat_title") cat_title: String
    ): Call<CodeResponse>
    ///////////////////////////////Update Users**
    @FormUrlEncoded
    @PUT("StoreApi/api/users_api.php")
    fun updateProfile(@Query("user_id") cat_id: Int?,
                      @Field("name") name: String,
                      @Field("phone") phone: String,
                      @Field("address") address: String

    ): Call<CodeResponse>

    ////////////////////////////////////////UpdatedProduct**
    @FormUrlEncoded
    @PUT("StoreApi/api/products_api")
    fun updateProduct(@Query("product_id") product_id: Int?,
                      @Field("title") title: String,
                     // @Field("images") images: String,
                      @Field("details") details: String,
                      @Field("color") color: String,
                      @Field("product_features") product_features: String,
                      @Field("price_y") price_y: Int,
                      @Field("price_d") price_d: Int
    ): Call<CodeResponse>
    ////////////////////////////////////////UpdateCart
    @FormUrlEncoded
    @PUT("StoreApi/api/cart_api.php")
    fun updateCart(@Query("cart_id") cat_id: Int?,
                       @Field("Quantity") quantity: String
    ): Call<CodeResponse>
//    fun updateCategory(@Path("cat_id") id:Int,
//                      @Body category: HashMap<String, Any>):Call<String>

}