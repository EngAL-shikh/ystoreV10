package com.amroz.ystore

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amroz.ystore.Models.Products
import com.squareup.picasso.Picasso

private lateinit var productsViewModel: YstoreViewModels
private lateinit var recyclerView: RecyclerView


class ProductByCat : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_by_cat)

        var cat_id = intent.getIntExtra("cat_id", 0)

        productsViewModel = ViewModelProviders.of(this).get(YstoreViewModels::class.java)

        var productsByCat = Featchers()
        val productLiveData = productsByCat.fetchProductsByCat(cat_id)
        productLiveData.observe(this, Observer {
            Log.d("ENS", "${it}")

            recyclerView.adapter = ProductByCatAdapter(it)
        })
        recyclerView = findViewById(R.id.ProByCatRecActivity)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
    }

    private inner class ProductsByCatHolder(view: View) : RecyclerView.ViewHolder(view) {

        val title = view.findViewById(R.id.title) as TextView
        val deatils = view.findViewById(R.id.deatils) as TextView
        val price = view.findViewById(R.id.price) as TextView
        val Raitings = view.findViewById(R.id.Raitings) as TextView
        val image = view.findViewById(R.id.image) as ImageView
        val productCardView = view.findViewById(R.id.ProductCard) as CardView


        fun bind(products: Products) {
            var images = products.images.split(",").toTypedArray()
            title.text = products.title
            deatils.text = products.details
            Raitings.text = products.rating_vote.toString()
            price.text = "$ " + products.price_d
            Log.d("rett", Raitings.text.toString())
            Picasso.get().load(images[0]).into(image)
///////////////////////////
            productCardView.setOnClickListener {
                var intent = Intent(this@ProductByCat, MoreDetails::class.java)
                intent.putExtra("data", products)
                startActivity(intent)
            }


        }

    }

    private inner class ProductByCatAdapter(var product: List<Products>) :
        RecyclerView.Adapter<ProductsByCatHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsByCatHolder {
            var view: View = layoutInflater.inflate(R.layout.products_list, parent, false)
            return ProductsByCatHolder(view)
        }

        override fun getItemCount(): Int {
            return product.size
        }

        override fun onBindViewHolder(holder: ProductsByCatHolder, position: Int) {
            val getP = product[position]
            holder.bind(getP)
        }
    }


}