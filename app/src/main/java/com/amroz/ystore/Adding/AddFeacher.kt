package com.amroz.ystore

import android.app.Application
import android.app.PendingIntent
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.MutableLiveData
import com.amroz.ystore.Api.YstoreApi
import com.amroz.ystore.Models.Users

import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AddFeacher : AppCompatActivity() {
    private lateinit var addApi: YstoreApi


    init {
        val  gson=GsonBuilder().setLenient().create()

        val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))


            .baseUrl("http://192.168.5.1/")


            .build()

        addApi = retrofit.create(YstoreApi::class.java)
    }
    fun addCategory(cat_title:String,images:String) : MutableLiveData<Response>{
        val responseLiveData: MutableLiveData<Response> = MutableLiveData()
        val newsRequest: Call<Response> = addApi.addCategory(cat_title,images)
        newsRequest.enqueue(object : Callback<Response> {
            override fun onFailure(call: Call<Response>, t: Throwable) {
                Log.e("TAGADD", "Failed to post ", t)
            }
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>
            ) {


                Log.e("TAGADD", "Response received")

            }
        })
        return responseLiveData
    }
    fun addproduct(title:String,details:String,images:String,color:String,product_features:String,rating:Int,
                   price_y:Int,price_d:Int,user_id: Int,cat_id:Int,report_id:Int, order_data:String, data:String
    ): MutableLiveData<Response>{
        val responseLiveData: MutableLiveData<Response> = MutableLiveData()
        val newsRequest: Call<Response> = addApi.addProduct(title,details,images,color,
            product_features,rating,price_y,price_d,user_id,cat_id,1,"","")
        newsRequest.enqueue(object : Callback<Response> {
            override fun onFailure(call: Call<Response>, t: Throwable) {
                Log.e("TAG", "Failed to post ", t)
            }
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>
            ) {

                Log.d("esharat", "Response received")

            }
        })
        return responseLiveData
    }
    fun addCart(user_id:Int,product_id:Int,Quantity:Int): MutableLiveData<Response> {
        val responseLiveData: MutableLiveData<Response> = MutableLiveData()
        val newsRequest: Call<Response> = addApi.addCart(user_id,product_id,Quantity)
        newsRequest.enqueue(object : Callback<Response> {
            override fun onFailure(call: Call<Response>, t: Throwable) {
                Log.e("TAG", "Failed to post ", t)
            }
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>
            ) {


                Log.e("TAG", "Response received")

            }
        })
        return responseLiveData
    }

//    fun addUser(name: String, email: String,password: String,chat_id: String,
//                phone: String,address: String,image:String): MutableLiveData<Response> {
//        val responseLiveData: MutableLiveData<Response> = MutableLiveData()
//        val newsRequest: Call<Response> = addApi.addUser(name,email,password,chat_id,phone,address,image)
//        newsRequest.enqueue(object : Callback<Response> {
//            override fun onFailure(call: Call<Response>, t: Throwable) {
//                Log.e("TAG", "Failed to post ", t)
//            }
//            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>
//            ) {
//
//                //Toast.makeText(this@AddFeacher,"User Added",Toast.LENGTH_LONG).show()
//                Log.e("TAG", "Response received")
//
//            }
//        })
//        return responseLiveData
//    }

    fun addUser(name: String, email: String,password: String,chat_id: String,
                phone: String,address: String,image:String): MutableLiveData<List<Users>> {
        val responseLiveData: MutableLiveData<List<Users>> = MutableLiveData()
        val newsRequest: Call<Response> = addApi.addUser(name,email,password,chat_id,phone,address,image)
        newsRequest.enqueue(object : Callback<Response> {
            override fun onFailure(call: Call<Response>, t: Throwable) {
                Log.e("adkljfadsf", t.message.toString())
                Log.e("TAG", "Response received")


            }
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>
            ) {

                //Toast.makeText(this@AddFeacher,"User Added",Toast.LENGTH_LONG).show()
                val response:Response? = response.body()
                val user:List<Users> = response?.getUserIDByEmail
                    ?: mutableListOf()
                Log.d("TAG", "Response received")
                responseLiveData.value = user
                Log.d("alksdjflkjaskfj", user.toString())

               // val context: Context = AddFeacher.applicationContext()

             //   QueryPreferences.setStoredQuery(this@AddFeacher, user[0].user_id.toString())
//               QueryPreferences.setStoredQueryUserid(context, user[0].user_id.toString())
//                QueryPreferences.setStoredQuery(context, user[0].rule)
//                QueryPreferences.setStoredQueryUserimage(context, user[0].user_image)
//                QueryPreferences.setStoredQueryUsername(context, user[0].name)
//                QueryPreferences.setStoredQueryUseraddress(context, user[0].address)
//                QueryPreferences.setStoredQueryChatid(context, user[0].chat_id)

            }
        })
        return responseLiveData
    }

}