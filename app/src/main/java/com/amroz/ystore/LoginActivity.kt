package com.amroz.ystore

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.amroz.ystore.Models.Users
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.scottyab.showhidepasswordedittext.ShowHidePasswordEditText
import render.animations.Bounce
import render.animations.Render
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    val render = Render(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var loginbyphone:ImageView=findViewById(R.id.login_by_phone)
        var login:ImageView=findViewById(R.id.login)
        var username:EditText=findViewById(R.id.username)
        var password: ShowHidePasswordEditText = findViewById(R.id.password)
        var loginbyemail:ImageView=findViewById(R.id.login_by_email)
        var linearLoginbyemail:LinearLayout=findViewById(R.id.linear_login_by_email)
        var linearLoginbyphone:LinearLayout=findViewById(R.id.linear_login_by_phone)

// Create Render Class


// Set Animation

        login.setOnClickListener {

            var fetch=Featchers()
            var call: Call<Users> = fetch.ystoreApi.login(username.text.toString(),password.text.toString())
            call.enqueue(object : Callback<Users>{
                override fun onFailure(call: Call<Users>, t: Throwable) {
                    Toast.makeText(this@LoginActivity,"User Not found",Toast.LENGTH_LONG).show()
                }

                override fun onResponse(call: Call<Users>, response: Response<Users>) {
                    if (response.isSuccessful){
                        Log.d("cxz","${response}")
                        var intent = Intent(this@LoginActivity,MainActivity::class.java)
                        intent.putExtra("admin",username.text.toString())
                        startActivity(intent)
                    }
                }

            })
        }

        loginbyphone.setOnClickListener {
            linearLoginbyphone.visibility=View.VISIBLE
            linearLoginbyemail.visibility=View.GONE
            loginbyphone.visibility=View.GONE
            loginbyemail.visibility=View.VISIBLE
            render.setAnimation(Bounce().InDown(loginbyemail))
            render.start()
            YoYo.with(Techniques.BounceIn)
                .duration(2000)
                .playOn(findViewById(R.id.linear_login_by_phone))
        }

        loginbyemail.setOnClickListener {

            linearLoginbyphone.visibility=View.GONE
            linearLoginbyemail.visibility=View.VISIBLE
            loginbyphone.visibility=View.VISIBLE
            loginbyemail.visibility=View.GONE
            YoYo.with(Techniques.BounceIn)
                .duration(2000)
                .playOn(findViewById(R.id.linear_login_by_email))
        }




    }
}