
package com.amroz.ystore.Api


import com.amroz.ystore.*
import com.amroz.ystore.Models.Users

import com.amroz.ystore.Response
import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query



interface YstoreApi {

    @GET("StoreApi/api/cat_api.php")
    fun fetchCategory(): Call<Response>

    @GET("StoreApi/api/cart_api.php")
    fun fetchCart(@Query("user_id") user_id: Int): Call<Response>

    @GET("StoreApi/api/reports_api.php")
    fun fetchReports(): Call<Response>

    @GET("StoreApi/api/products_api.php")
    fun fetchProducts(): Call<Response>

    @GET("StoreApi/api/Users_api.php")
    fun fetchUsers(): Call<Response>

    @GET("StoreApi/api/rating_api.php")
    fun fetchRating(): Call<Response>

    @GET("StoreApi/api/reports.php")
    fun fetchReportsByID(@Query("report_id") report_id: Int): Call<Response>

    //payment
    @GET("StoreApi/api/bank_api.php")
    fun fetchpayinfo(@Query("cvv") cvv: Int): Call<Response>




    @GET("/StoreApi/api/products_api.php")
    fun fetchProductsByCat(@Query("cat_id") cat_id: Int): Call<Response>

    @GET("/StoreApi/api/gitProductsByUser.php?")
    fun fetchProductsByUser(@Query("user_id") user_id: Int): Call<Response>

    @GET("StoreApi/api/Users_api.php?")
    fun fetchSingleUsers(@Query("user_id") user_id: Int): Call<Response>

    @GET("StoreApi/api/gitUserid_api.php?")
    fun fetchSingleUsersbyemail(@Query("email") email: String): Call<Response>

    @GET("StoreApi/api/getProductsDetails.php")
    fun fetchProductsByReportID(@Query("report_id") report_id: Int,
                                @Query("product_id") product_id: Int): Call<Response>

    @GET("StoreApi/api/favorite_api.php?")
    fun fetchFavorite(@Query("user_id") user_id: Int): Call<Response>


    // adding/////////////////////////////////////////////////////////////////////////////////

    @FormUrlEncoded
    @POST("StoreApi/api/cat_api.php")
    fun addCategory(@Field("cat_title") cat_title:String,
                    @Field("images") images:String): Call<Response>
    @FormUrlEncoded
    @POST("StoreApi/api/products_api.php")
    fun addProduct( @Field("title") title: String,
                    @Field("details") details: String,
                    @Field("images") images: String,
                    @Field("color") color: String,
                    @Field("product_features") product_features: String,
                    @Field("rating") rating: Int,
                    @Field("price_y") price_y:Int,
                    @Field("price_d") price_d: Int,
                    @Field("user_id") user_id: Int,
                    @Field("cat_id") cat_id: Int,
                    @Field("report_id") report_id: Int,
                    @Field("order_date") order_date: String,
                    @Field("date")date: String
                                                  ): Call<Response>
    @FormUrlEncoded
    @POST("StoreApi/api/cart_api.php")
    fun addCart(@Field("user_id") user_id:Int,
                @Field("product_id") product_id:Int,
                @Field("Quantity") Quantity:Int
    ): Call<Response>

    @FormUrlEncoded
    @POST("StoreApi/api/favorite_api.php")
    fun addFovarite(@Field("user_id") user_id:Int,
                @Field("product_id") product_id:Int

    ): Call<Response>



    /// mange ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////



    @DELETE("StoreApi/api/cat_api.php")
    fun  deleteCategory(@Query("cat_id")id:Int): Call<Response>

    @DELETE("StoreApi/api/users_api.php")
    fun  deleteUser(@Query("user_id")id:Int): Call< Response>

    @DELETE("StoreApi/api/products_api.php")
    fun  deleteProduct(@Query("product_id")id:Int): Call< Response>

    @DELETE("StoreApi/api/cart_api.php")
    fun  deletecart(@Query("user_id") user_id:Int,
                    @Query("product_id") name: Int
    ): Call<Response>

    @DELETE("StoreApi/api/reports_api.php")
    fun  deletereports(@Query("report_id")id:Int): Call<Response>















    //////////////////////////////Update Category
    @FormUrlEncoded
    @PUT("StoreApi/api/cat_api.php")
    fun updateCategory(@Query("cat_id") cat_id: Int?,
                       @Field("cat_title") cat_title: String
    ): Call<Response>
    ///////////////////////////////Update Users**
    @FormUrlEncoded
    @PUT("StoreApi/api/users_api.php")
    fun updateProfile(@Query("user_id") user_id: Int?,
                      @Field("name") name: String,
                      @Field("email") email: String,
                      @Field("phone") phone: String,
                      @Field("address") address: String

    ): Call<Response>

    ////////////////////////////////////////UpdatedProduct**
    @FormUrlEncoded
    @PUT("StoreApi/api/products_api.php")
    fun updateProduct(@Query("product_id") product_id: Int?,
                      @Field("title") title: String,
        // @Field("images") images: String,
                      @Field("details") details: String,
                      @Field("color") color: String,
                      @Field("product_features") product_features: String,
                      @Field("price_y") price_y: Int,
                      @Field("price_d") price_d: Int
    ): Call<Response>
    ////////////////////////////////////////UpdateCart
    @FormUrlEncoded
    @PUT("StoreApi/api/cart_api.php")
    fun updateCart(@Query("cart_id") cat_id: Int?,
                   @Field("Quantity") quantity: String
    ): Call<Response>

    ////////////////////////////////////////UpdateRatingProduct
    @FormUrlEncoded
    @PUT("StoreApi/api/addRating.php")
    fun updateRatingVote(@Query("product_id") product_id: Int?,
                     @Field("rating_vote") rating_vote: Int

    ): Call<Response>
    ////////////////////////////////////////UpdateRatingUser
    @FormUrlEncoded
    @PUT("StoreApi/api/ratingUser.php")
    fun updateRatingUser(@Query("user_id") product_id: Int?,
                     @Field("user_raiting") rating: Int
    ): Call<Response>
//    fun updateCategory(@Path("cat_id") id:Int,
//                      @Body category: HashMap<String, Any>):Call<String>

    /////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////athu//////////////////////////////////////////////////////////////////////

    @FormUrlEncoded
    @POST("StoreApi/api/login.php")
    fun login(@Field("name") email:String, @Field("password")password:String) : Call<Users>


    @FormUrlEncoded
    @POST("StoreApi/api/users_api.php")
    fun addUser( @Field("name") name: String,
                    @Field("email") email: String,
                    @Field("password") password: String,
                    @Field("chat_id") chat_id: String,
                    @Field("phone") phone: String,
                    @Field("address") address: String,
                    @Field("image") image: String
    ): Call<Response>


//update user report

    @FormUrlEncoded
    @PUT("StoreApi/api/users_api.php")
    fun updateReportUser(@Query("user_id")user_id : Int?,
                   @Field("user_report") user_report: Int
    ): Call<Response>


    //produuct_report
    @FormUrlEncoded
    @POST("StoreApi/api/reports_api.php")
    fun productReport
                (@Field("report_id") report_id:Int,
                @Field("report_reason") report_reason:String,
                @Field("product_id") product_id:Int,
                 @Field("user_id") user_id:Int): Call<Response>



    //Reset_Password
    @FormUrlEncoded
    @PUT("StoreApi/api/users_api.php")
    fun ResetPassword(@Query("user_id")user_id : Int?,
                         @Field("password") password: String
    ): Call<Response>


    ///////////////////////////////RatingUs [ Post Put Delete]

    @FormUrlEncoded
    @PUT("StoreApi/api/rating_api.php")
    fun updateRating(@Query("rating_id")rating_id : Int?,
                         @Field("ratingNum") ratingNum: Float
    ): Call<Response>
//*****************//
    @FormUrlEncoded
    @POST("StoreApi/api/rating_api.php")
    fun addRating
                (
                 @Field("ratingNum") ratingNum:Float,
                 @Field("product_id") product_id:Int,
                 @Field("user_id") user_id:Int): Call<Response>
    //*****************//
    @DELETE("StoreApi/api/rating_api.php")
    fun  deleteRating(@Query("rating_id")id:Int): Call<Response>
    ///////////////////////////////////////////////


    ///////////user_status////////////////
    //user_status
    @FormUrlEncoded
    @PUT("StoreApi/api/status_api.php")
    fun updateUserStatus(@Query("user_id")user_id : Int?,
                         @Field("user_status") user_status: Int
    ): Call<Response>
    ///////////user_status////////////////


    ///////////payment////////////////
    //user_status
    @FormUrlEncoded
    @PUT("StoreApi/api/bank_api.php")
    fun checkOut(@Query("card_number") card_number : Int?,
                         @Field("amount") amount: Int
    ): Call<Response>
    ///////////user_status////////////////
    ///////////product_status////////////////

    @FormUrlEncoded
    @PUT("StoreApi/api//productStatus_api.php")
    fun updateProductStatus(@Query("product_id")product_id: Int?,
                            @Field("product_status") product_status: Int
    ): Call<Response>
    ///////////product_status////////////////

}

