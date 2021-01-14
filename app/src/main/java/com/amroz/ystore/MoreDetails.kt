package com.amroz.ystore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.amroz.ystore.Models.Products
import com.squareup.picasso.Picasso

class MoreDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_details)
        var title:TextView=findViewById(R.id.title)
        var details:TextView=findViewById(R.id.deatils)
        var image:ImageView=findViewById(R.id.image)

        var products=intent.getSerializableExtra("data") as Products


        title.text=products.title
        details.text=products.details
        Picasso.with(this).load(products.images).into(image)




    }
}