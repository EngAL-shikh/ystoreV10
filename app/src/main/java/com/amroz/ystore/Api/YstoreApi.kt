package com.amroz.ystore.Api

import retrofit2.Call
import retrofit2.http.GET

interface YstoreApi {
    @GET("StoreApi/api/reports_api.php")
    fun fetchReports(): Call<YstoerResponse>

}