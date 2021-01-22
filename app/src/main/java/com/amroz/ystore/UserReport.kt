package com.amroz.ystore

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.lifecycle.ViewModelProviders
import com.amroz.ystore.Models.Products

class UserReport : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_report)

        lateinit var nameUser: EditText
        lateinit var ed_email: EditText
        lateinit var ed_address: EditText


        var user_report:YstoreViewModels = ViewModelProviders.of(this).get(YstoreViewModels::class.java)
        var image: ImageView =findViewById(R.id.image)
        val report = findViewById<ImageButton>(R.id.bt_report)
        var image_back: ImageView =findViewById(R.id.ba)

        var products=intent.getSerializableExtra("data") as Products

        ed_email=findViewById(R.id.email)
        ed_email.setText(products.email)
       nameUser =findViewById(R.id.name)
        nameUser.setText(products.user_name)
        ed_address=findViewById(R.id.address)
       ed_address.setText(products.adress)
       // Log.d("fatma", products.email)
       // Log.d("fatma",(products.user_name))

        report.setOnClickListener{

            val builder = AlertDialog.Builder(this)

            builder.setMessage("Are you sure ")

            builder.setPositiveButton("Continue"){_,_->
                user_report.update_user_report(products.user_id,0 )

                Toast.makeText(applicationContext, "  This user has been reported", Toast.LENGTH_SHORT).show()
            }
            builder.setNegativeButton("Cancel") { _, _ ->

            }
            val dialog = builder.create()
            dialog.show()

        }
    image_back.setOnClickListener {

        var intent= Intent(this,MoreDetails::class.java)
        startActivity(intent)
    }

    }
}