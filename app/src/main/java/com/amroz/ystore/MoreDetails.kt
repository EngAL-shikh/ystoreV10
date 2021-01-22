package com.amroz.ystore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProviders
import com.amroz.ystore.Fragments.ProductsFragment
import com.amroz.ystore.Fragments.ReportFragment
import com.amroz.ystore.Models.Products
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_more_details.*

class MoreDetails : AppCompatActivity(), RatingBar.OnRatingBarChangeListener{
    var count:Int=0
    private val ystoreViewModels: YstoreViewModels by lazy {
        ViewModelProviders.of(this).get(YstoreViewModels::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_details)
        var title:TextView=findViewById(R.id.title)
        var price:TextView=findViewById(R.id.price)
        var details:TextView=findViewById(R.id.deatils)
        var image:ImageView=findViewById(R.id.image)
        var image_1:ImageView=findViewById(R.id.image_1)
        var image2:ImageView=findViewById(R.id.image_2)
        var image3:ImageView=findViewById(R.id.image_3)
        var image4:ImageView=findViewById(R.id.image_4)
        var image5:ImageView=findViewById(R.id.image_5)
        var addtocard:AppCompatButton=findViewById(R.id.bt_add_to_cart)
        var orederdate:TextView=findViewById(R.id.orderdate)
        var owner:TextView=findViewById(R.id.owner)
        var addqn:FloatingActionButton=findViewById(R.id.fab_qty_add)
        var subqn:FloatingActionButton=findViewById(R.id.fab_qty_sub)
        var tv_qty:TextView=findViewById(R.id.tv_qty)
        var btn_ratingProduct:ImageButton=findViewById(R.id.btn_ratingProduct)

        var products=intent.getSerializableExtra("data") as Products

        var avgRatingtv:TextView=findViewById(R.id.avrage_rating_tv)
        var userRating:TextView=findViewById(R.id.user_rating_tv)
        var ratingBar:RatingBar=findViewById(R.id.ratingProduct)




        title.text=products.title
        price.text="$ "+products.price_d
        details.text=products.details
        details.text=products.details
        orederdate.text="Oreder Date : after 10 days frome this date "+products.order_date
        owner.text="sold by:"+products.user_name


        report.setOnClickListener {
            //cart.visibility= View.VISIBLE

            var intent= Intent(this,product_report::class.java)
            intent.putExtra("data",products)
            startActivity(intent)
        }
      owner.setOnClickListener{

          var intent= Intent(this,UserReport::class.java)
          intent.putExtra("data",products)
          startActivity(intent)

}

      var images=  products.images.split(",").toTypedArray()

       var image1= images[0]
       var image22= images[1]


        Picasso.with(this).load(image1).into(image)
        Picasso.with(this).load(images[0]).into(image_1)
        Picasso.with(this).load(image22).into(image2)
        Picasso.with(this).load(images[2]).into(image3)
        Picasso.with(this).load(images[3]).into(image4)
        Picasso.with(this).load(images[4]).into(image5)


        image_1.setOnClickListener {
            Picasso.with(this).load(images[0]).into(image)
            image_1.setBackgroundResource(R.drawable.edit_text_round_bg_outline_green)
            image2.setBackgroundResource(R.drawable.edit_text_round_bg_outline)
            image3.setBackgroundResource(R.drawable.edit_text_round_bg_outline)
            image4.setBackgroundResource(R.drawable.edit_text_round_bg_outline)
            image5.setBackgroundResource(R.drawable.edit_text_round_bg_outline)

        }
        image2.setOnClickListener {
            Picasso.with(this).load(images[1]).into(image)
            image2.setBackgroundResource(R.drawable.edit_text_round_bg_outline_green)
            image_1.setBackgroundResource(R.drawable.edit_text_round_bg_outline)
            image3.setBackgroundResource(R.drawable.edit_text_round_bg_outline)
            image4.setBackgroundResource(R.drawable.edit_text_round_bg_outline)
            image5.setBackgroundResource(R.drawable.edit_text_round_bg_outline)
        }
        image3.setOnClickListener {
            Picasso.with(this).load(images[2]).into(image)
            image3.setBackgroundResource(R.drawable.edit_text_round_bg_outline_green)
            image_1.setBackgroundResource(R.drawable.edit_text_round_bg_outline)
            image2.setBackgroundResource(R.drawable.edit_text_round_bg_outline)
            image4.setBackgroundResource(R.drawable.edit_text_round_bg_outline)
            image5.setBackgroundResource(R.drawable.edit_text_round_bg_outline)
        }
        image4.setOnClickListener {
            Picasso.with(this).load(images[3]).into(image)
            image4.setBackgroundResource(R.drawable.edit_text_round_bg_outline_green)
            image_1.setBackgroundResource(R.drawable.edit_text_round_bg_outline)
            image2.setBackgroundResource(R.drawable.edit_text_round_bg_outline)
            image3.setBackgroundResource(R.drawable.edit_text_round_bg_outline)
            image5.setBackgroundResource(R.drawable.edit_text_round_bg_outline)

        }
        image5.setOnClickListener {
            Picasso.with(this).load(images[4]).into(image)
            image5.setBackgroundResource(R.drawable.edit_text_round_bg_outline_green)
            image_1.setBackgroundResource(R.drawable.edit_text_round_bg_outline)
            image2.setBackgroundResource(R.drawable.edit_text_round_bg_outline)
            image3.setBackgroundResource(R.drawable.edit_text_round_bg_outline)
            image4.setBackgroundResource(R.drawable.edit_text_round_bg_outline)

        }



        addtocard.setOnClickListener {
            ystoreViewModels.addCart(3,products.product_id,count)
        }

        addqn.setOnClickListener {
           ++ count
            tv_qty.text=count.toString()
        }
        subqn.setOnClickListener {

            count--
            tv_qty.text=count.toString()
            if (count==0){

                count=10
            }
        }




//ratingBar.onRatingBarChangeListener.onRatingChanged()
        ratingBar.onRatingBarChangeListener =
            RatingBar.OnRatingBarChangeListener { _, rating, _ ->
                if(rating.toInt()<0){
                Toast.makeText(
                    this, "please rating  " +
                            rating, Toast.LENGTH_SHORT
                ).show()}
                else  if(rating.toInt()<1){
                    Toast.makeText(
                        this, "You are rating:${rating.toInt()} heart  " +
                                rating, Toast.LENGTH_SHORT
                    ).show()}
                else  if(rating.toInt()==3){
                    Toast.makeText(
                        this, "Thanks for rating us:${rating.toInt()} heart" +
                                rating, Toast.LENGTH_SHORT
                    ).show()}

            }
        val ratingBarValue = ratingBar.rating
        var  count=products.rating_vote+1
        var sumRating=products.rating+ratingBarValue
        var avregeRating=sumRating/3.0f
       // if()
        btn_ratingProduct.setOnClickListener{
            ManagementFeatchers().updateRating(products.product_id,sumRating,count)
            Toast.makeText(
                this,
                "Thanks For rating us : " + ratingBarValue.toString(), Toast.LENGTH_SHORT
            ).show()
        }
        avgRatingtv.text=avregeRating.toString()
        Log.d("hager","$avregeRating")
        userRating.text=count.toString()

    }

    override fun onRatingChanged(ratingBar: RatingBar?, rating: Float, fromUser: Boolean) {

    }


}