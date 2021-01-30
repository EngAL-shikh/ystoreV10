package com.amroz.ystore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.amroz.ystore.Models.Products
import com.amroz.ystore.Models.RatingUs
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_more_details.*
import kotlinx.android.synthetic.main.catogrey_list.*
import java.math.RoundingMode

class MoreDetails : AppCompatActivity(), RatingBar.OnRatingBarChangeListener{
    var count:Int=0
    private val ystoreViewModels: YstoreViewModels by lazy {
        ViewModelProviders.of(this).get(YstoreViewModels::class.java)
    }
    lateinit var ratingUs:List<RatingUs>

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
        var btn_ratingProduct:TextView=findViewById(R.id.btn_ratingProduct)
        var products=intent.getSerializableExtra("data") as Products
        var avgRatingtv:TextView=findViewById(R.id.avrage_rating_tv)
        var userRating:TextView=findViewById(R.id.user_rating_tv)
        var ratingBar:RatingBar=findViewById(R.id.ratingProduct)
        var ratingBar2:RatingBar=findViewById(R.id.ratingShow_rb)
        var card_report:CardView=findViewById(R.id.card_report)

        // report -------------------------------------------------------------------------------
        var reson_report = ""
        var report1: TextView = findViewById(R.id.report1)
        var report2: TextView = findViewById(R.id.report2)
        var report3: TextView = findViewById(R.id.report3)
        var report4: TextView = findViewById(R.id.report4)
        var save: Button = findViewById(R.id.save)
        var cancel: Button = findViewById(R.id.cancle)



        report1.setOnClickListener {
            reson_report = report1.text as String
            report1.setBackgroundResource(R.drawable.edit_text_round_bg_outline_green)
            report2.setBackgroundResource(R.drawable.edit_text_round_bg_outline)
            report3.setBackgroundResource(R.drawable.edit_text_round_bg_outline)
            report4.setBackgroundResource(R.drawable.edit_text_round_bg_outline)
        }
        report2.setOnClickListener {
            reson_report = report2.text as String
            report2.setBackgroundResource(R.drawable.edit_text_round_bg_outline_green)
            report1.setBackgroundResource(R.drawable.edit_text_round_bg_outline)
            report3.setBackgroundResource(R.drawable.edit_text_round_bg_outline)
            report4.setBackgroundResource(R.drawable.edit_text_round_bg_outline)
        }
        report3.setOnClickListener {
            reson_report = report3.text as String
            report3.setBackgroundResource(R.drawable.edit_text_round_bg_outline_green)
            report2.setBackgroundResource(R.drawable.edit_text_round_bg_outline)
            report1.setBackgroundResource(R.drawable.edit_text_round_bg_outline)
            report4.setBackgroundResource(R.drawable.edit_text_round_bg_outline)
        }
        report4.setOnClickListener {
            reson_report = report4.text as String
            report4.setBackgroundResource(R.drawable.edit_text_round_bg_outline_green)
            report2.setBackgroundResource(R.drawable.edit_text_round_bg_outline)
            report3.setBackgroundResource(R.drawable.edit_text_round_bg_outline)
            report1.setBackgroundResource(R.drawable.edit_text_round_bg_outline)
        }

        save.setOnClickListener {
            Log.d("repo", reson_report)
            Log.d("repo", products.product_id.toString())
            Log.d("repo", products.user_id.toString())

            ystoreViewModels.addReportProduct(
                0,
                reson_report,
                products.user_id,
                products.product_id
            )
            card_report.visibility= View.GONE
        }

        cancel.setOnClickListener {
            card_report.visibility= View.GONE
        }
        //-----------------------------------------------------------------------------------------------------------------------------------------



        title.text=products.title
        price.text="$ "+products.price_d
        details.text=products.details
        details.text=products.details
        orederdate.text="Oreder Date : after 10 days frome this date "+products.order_date
        owner.text="sold by:"+products.user_name


        report.setOnClickListener {

            card_report.visibility= View.VISIBLE
            YoYo.with(Techniques.FadeInUp)
                .duration(2000)
                .playOn(card_report)

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
            var id= QueryPreferences.getStoredQueryUserid(this!!).toInt()
            ystoreViewModels.addCart(id!!,products.product_id,count)
            Toast.makeText(this,"Product added to your Cart",Toast.LENGTH_LONG).show()
            Log.d("cartadd",QueryPreferences.getStoredQueryUserid(this!!).toString())
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
/////////////////////////////Add rating

        var total=0.0f
        var avarage=0.0f
        var ratingBarValue = 0.0f
//ratingBar.onRatingBarChangeListener.onRatingChanged()
//        var  count=products.rating_vote+1
//        var sumRating=0.0f
//        var avregeRating=0.0f
//        ratingBar.onRatingBarChangeListener =
//            RatingBar.OnRatingBarChangeListener { _, rating, _ ->
//                if(rating.toInt()<0){
//                Toast.makeText(
//                    this, "please rating  " +
//                            rating, Toast.LENGTH_SHORT
//                ).show()}
//                else  if(rating.toInt()<1){
//                    Toast.makeText(
//                        this, "You are rating:${rating.toInt()} heart  " +
//                                rating, Toast.LENGTH_SHORT
//                    ).show()}
//                else  if(rating.toInt()==3){
//                    Toast.makeText(
//                        this, "Thanks for rating us:${rating.toInt()} heart" +
//                                rating, Toast.LENGTH_SHORT
//                    ).show()}
//            }
        btn_ratingProduct.setOnClickListener{

         var  countuser=products.rating_vote+1
            ystoreViewModels.addRating(ratingBar.rating,products.product_id,products.user_id)
            ManagementFeatchers().updateRatingVote(products.product_id,products.rating_vote+1)
            Toast.makeText(
                this,
                "Thanks For rating us : " + countuser, Toast.LENGTH_SHORT
            ).show()
        }
 ystoreViewModels.liveDataRatingUs.observe(
            this, Observer{ rating ->
               this.ratingUs=rating
                Log.d("rating", "$rating")
                for (i in rating ) {
                    if (products.product_id == i.product_id) {
                        total += i.ratingNum
                        Log.d("total1", "$total")
                    }
                }
                    avarage=total/3.0f
              var avg= avarage/products.rating_vote.toFloat()
              var avg2=avg

               Log.d("avarage", "$avg")

  
                avgRatingtv.text=avg2.toString()
                userRating.text=products.rating_vote.toString()
                ratingBar2.rating=avg
                ratingBar2.setIsIndicator(true)
                })
    }

    override fun onRatingChanged(ratingBar: RatingBar?,
                                 rating: Float,
                                 fromUser: Boolean) {

    }



}