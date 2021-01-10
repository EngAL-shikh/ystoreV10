
package com.amroz.ystore.Api

import retrofit2.Call
import retrofit2.http.GET

interface YstoreApi {
    @GET("StoreApi/api/reports_api.php")
    fun fetchReports(): Call<YstoerResponse>

}



interface YstoreApi {

    @GET("StoreApi/api/cat_api.php")
    fun fetchCategory(): Call<CategoryResponse>

    @GET("StoreApi/api/cart_api.php")
    fun fetchCart(): Call<CartResponse>
}

