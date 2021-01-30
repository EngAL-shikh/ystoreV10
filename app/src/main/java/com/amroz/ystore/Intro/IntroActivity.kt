package com.amroz.ystore.Intro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.airbnb.lottie.LottieAnimationView
import com.amroz.ystore.Dashboard
import com.amroz.ystore.MainActivity
import com.amroz.ystore.R


class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        var next:TextView=findViewById(R.id.next)
        var next2:TextView=findViewById(R.id.next2)
        var next3:TextView=findViewById(R.id.next3)
        var next4:TextView=findViewById(R.id.next4)
        var skip:TextView=findViewById(R.id.SKIP)
        var intro: LottieAnimationView =findViewById(R.id.introimage)

        next.setOnClickListener {
            intro.setAnimation(R.raw.tow)
            intro.playAnimation()
        next2.visibility=View.VISIBLE
        next3.visibility=View.GONE
        next.visibility=View.GONE
        next4.visibility=View.GONE


        }
        next2.setOnClickListener {
            intro.setAnimation(R.raw.three)
            intro.playAnimation()
            next3.visibility=View.VISIBLE
            next2.visibility=View.GONE
            next.visibility=View.GONE
            next4.visibility=View.GONE

        }
        next3.setOnClickListener {
            intro.setAnimation(R.raw.four)
            intro.playAnimation()
            next3.visibility=View.GONE
            next4.visibility=View.VISIBLE
            next.visibility=View.GONE
            next2.visibility=View.GONE
        }

        next4.setOnClickListener {
            var intent= Intent(this, MainActivity::class.java)
                  startActivity(intent)
                  finish()
        }

        skip.setOnClickListener {
            var intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}