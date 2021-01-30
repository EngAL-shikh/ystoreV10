package com.amroz.ystore.Payment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.airbnb.lottie.LottieAnimationView
import com.amroz.ystore.Featchers
import com.amroz.ystore.MainActivity
import com.amroz.ystore.R
import com.amroz.ystore.YstoreViewModels
import com.balysv.materialripple.MaterialRippleLayout

class CheckoutActivity : AppCompatActivity() {
    var total_amount_in_bank=0
    private lateinit var userViewModel: ViewModel
    private val ystoreViewModels: YstoreViewModels by lazy {
        ViewModelProviders.of(this).get(YstoreViewModels::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)
        var checkout: MaterialRippleLayout = findViewById(R.id.checkout)
        var pay: MaterialRippleLayout = findViewById(R.id.pay)
        var nick_name2: EditText = findViewById(R.id.nick_name2)
        var card_number2: EditText = findViewById(R.id.card_number2)
        var expire2: EditText = findViewById(R.id.expire2)
        var cvv2: EditText = findViewById(R.id.cvv2)
        var nick_name: TextView = findViewById(R.id.nick_name)
        var card_number: TextView = findViewById(R.id.card_number)
        var expire: TextView = findViewById(R.id.expire)
        var cvv: TextView = findViewById(R.id.cvv)
        var back: ImageButton = findViewById(R.id.back)
        var total: TextView = findViewById(R.id.total)
        var cardpay: CardView = findViewById(R.id.cardpay)
        var amipay: LottieAnimationView = findViewById(R.id.amipay)


        var total_amount=intent.extras?.getString("total_amount")
        total.text=total_amount


        checkout.setOnClickListener {
            var products = Featchers()
            val newsLiveData = products.fetchpayinfo(cvv2.text.toString().toInt())
            newsLiveData.observe(this, Observer {
                Log.d("fetchpayinfo", "Response received: ${it}")

                nick_name.text = it[0].name
                card_number.text = it[0].number.toString()
                expire.text = it[0].Expiry_date
                cvv.text = it[0].cvv.toString()
                total_amount_in_bank=it[0].amount.toInt()

            })

            checkout.visibility=View.GONE
            pay.visibility=View.VISIBLE

        }


        pay.setOnClickListener {
            if (total_amount_in_bank < total_amount?.toInt()!! ){
                Toast.makeText(this,"you dont have mony to pay this",Toast.LENGTH_LONG).show()
                checkout.visibility=View.VISIBLE
                pay.visibility=View.GONE

            }else{
                ystoreViewModels.checkOut(card_number.text.toString().toInt(),total_amount.toInt())
                cardpay.visibility=View.VISIBLE
                amipay.playAnimation()
                Handler().postDelayed(Runnable {
                    var intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }, 4000)


            }
        }


        back.setOnClickListener {
            onBackPressed()
        }
    }
}