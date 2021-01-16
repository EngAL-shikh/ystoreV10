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
        var image2:ImageView=findViewById(R.id.image_2)
        var image3:ImageView=findViewById(R.id.image_3)
        var image4:ImageView=findViewById(R.id.image_4)
        var image5:ImageView=findViewById(R.id.image_5)

        var products=intent.getSerializableExtra("data") as Products


        title.text=products.title
        details.text=products.details



      var images=  products.images.split(",").toTypedArray()

       var image1= images[0]
       var image22= images[1]


        Picasso.with(this).load(image1).into(image)
        Picasso.with(this).load(image22).into(image2)
        Picasso.with(this).load(images[2]).into(image3)
        Picasso.with(this).load(images[3]).into(image4)
        Picasso.with(this).load(images[4]).into(image5)




    }





}