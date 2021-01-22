package com.amroz.ystore.Splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.amroz.ystore.LoginActivity
import com.amroz.ystore.MainActivity
import com.amroz.ystore.R

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val background = object : Thread(){

            override fun run() {
                try {
                    Thread.sleep(3000)

                    val toMain= Intent(baseContext, LoginActivity::class.java)
                    startActivity(toMain)
                    overridePendingTransition(R.anim.nav_default_pop_enter_anim,R.anim.nav_default_pop_exit_anim)



                }catch (e: Exception){
                    e.printStackTrace()
                }
            }

        }

        background.start()





    }

    override fun onBackPressed() {

        finish()
        super.onBackPressed()
    }
}