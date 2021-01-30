package com.amroz.ystore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amroz.ystore.Models.Products
import com.squareup.picasso.Picasso

class FavoriteActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)


        val productsByUser = Featchers().fetchFaveort(QueryPreferences.getStoredQueryUserid(this).toInt())
        productsByUser.observe(this, Observer {
            Log.d("ENS", "${it}")

            recyclerView.adapter = FaviorteAdapter(it)
        })
        recyclerView = findViewById(R.id.rec)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

    }




    private inner class FaviorteUserHolder(view: View) : RecyclerView.ViewHolder(view) {
        private  lateinit var productsItem:Products
        val title = view.findViewById(R.id.title) as TextView
        val deatils = view.findViewById(R.id.deatils) as TextView
        val price = view.findViewById(R.id.price) as TextView
        val Raitings = view.findViewById(R.id.Raitings) as ImageView
        val image = view.findViewById(R.id.image) as ImageView
        val card= view.findViewById(R.id.ProductCard) as CardView
        val remov_fevort= view.findViewById(R.id.remove_fivort_Raiting) as ImageView
        val add_fevort= view.findViewById(R.id.add_fivort) as ImageView


        fun bind(products: Products) {


            var images=  products.images.split(",").toTypedArray()
            productsItem=products
            title.text = products.title
            deatils.text = products.details
           // Raitings.text=products.rating_vote.toString()
            price.text="$ "+products.price_d.toString()
            Picasso.with(this@FavoriteActivity).load(images[0]).into(image)



            remov_fevort.visibility=View.GONE
            add_fevort.visibility=View.VISIBLE
        }



    }

    private inner class FaviorteAdapter(var product: List<Products>) :
        RecyclerView.Adapter<FaviorteUserHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FaviorteUserHolder {
            var view: View = layoutInflater.inflate(R.layout.products_list, parent, false)
            return FaviorteUserHolder(view)
        }

        override fun getItemCount(): Int {
            return product.size
        }

        override fun onBindViewHolder(holder: FaviorteUserHolder, position: Int) {
            val getP = product[position]
            holder.bind(getP)
        }
    }
}