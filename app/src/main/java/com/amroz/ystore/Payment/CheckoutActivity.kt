package com.amroz.ystore.Payment

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.amroz.ystore.Featchers
import com.amroz.ystore.R
import com.balysv.materialripple.MaterialRippleLayout

class CheckoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)
        var checkout: MaterialRippleLayout = findViewById(R.id.checkout)
        var nick_name2: EditText = findViewById(R.id.nick_name2)
        var card_number2: EditText = findViewById(R.id.card_number2)
        var expire2: EditText = findViewById(R.id.expire2)
        var cvv2: EditText = findViewById(R.id.cvv2)
        var nick_name: TextView = findViewById(R.id.nick_name)
        var card_number: TextView = findViewById(R.id.card_number)
        var expire: TextView = findViewById(R.id.expire)
        var cvv: TextView = findViewById(R.id.cvv)
        var back: ImageButton = findViewById(R.id.back)




        checkout.setOnClickListener {
            var products = Featchers()
            val newsLiveData = products.fetchpayinfo(cvv2.text.toString().toInt())
            newsLiveData.observe(this, Observer {
                Log.d("fetchpayinfo", "Response received: ${it}")

                nick_name.text = it[0].name
                card_number.text = it[0].number.toString()
                expire.text = it[0].Expiry_date
                cvv.text = it[0].cvv.toString()

            })
        }

        back.setOnClickListener {
            onBackPressed()
        }
    }
}