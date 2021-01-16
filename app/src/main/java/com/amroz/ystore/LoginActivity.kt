package com.amroz.ystore

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import render.animations.Bounce
import render.animations.Render

class LoginActivity : AppCompatActivity() {
    val render = Render(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var loginbyphone:ImageView=findViewById(R.id.login_by_phone)
        var login:ImageView=findViewById(R.id.login)
        var loginbyemail:ImageView=findViewById(R.id.login_by_email)
        var linearLoginbyemail:LinearLayout=findViewById(R.id.linear_login_by_email)
        var linearLoginbyphone:LinearLayout=findViewById(R.id.linear_login_by_phone)

// Create Render Class


// Set Animation


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


        var intent = Intent(this,MainActivity::class.java)
        startActivity(intent)


    }
}