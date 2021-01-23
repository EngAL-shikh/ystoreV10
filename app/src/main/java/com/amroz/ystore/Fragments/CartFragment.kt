package com.amroz.ystore.Fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amroz.ystore.Featchers
import com.amroz.ystore.Models.Cart
import com.amroz.ystore.Models.Products
import com.amroz.ystore.MoreDetails
import com.amroz.ystore.R
import com.amroz.ystore.YstoreViewModels
import com.squareup.picasso.Picasso


class CartFragment : Fragment() {

    private val cartViewModel: YstoreViewModels by lazy {
        ViewModelProviders.of(this).get(YstoreViewModels::class.java)
    }
    private lateinit var cartRecyclerView: RecyclerView
    var type=""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


//        type=arguments?.getString("type") as String


        }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cartViewModel.fetchCart(3).observe(viewLifecycleOwner, Observer {
            it?.let {
                cartRecyclerView.adapter= CartAdapter(it)
            }
        })




    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_cart, container, false)
        cartRecyclerView= view.findViewById(R.id.cart_recycler_view)
        cartRecyclerView.layoutManager= GridLayoutManager(context,2)
        return view
    }

//    companion object {
//        fun newInstance(data: String): CartFragment {
//            val args = Bundle().apply {
//                putSerializable("type", data)
//            }
//            return CartFragment().apply {
//                arguments = args
//            }
//        }
//    }

    private inner class CartHolder(view: View) : RecyclerView.ViewHolder(view){
        private  lateinit var productsItem:Products

        val title = view.findViewById(R.id.title) as TextView
        val deatils = view.findViewById(R.id.deatils) as TextView
        val price = view.findViewById(R.id.price) as TextView
        val Raitings = view.findViewById(R.id.Raitings) as TextView
        val image = view.findViewById(R.id.image) as ImageView
        val card= view.findViewById(R.id.ProductCard) as CardView


        fun bind(products: Products) {
            var images=  products.images.split(",").toTypedArray()
            productsItem=products
            title.text = products.title
            deatils.text = products.details
            Raitings.text = products.rating.toString()
            price.text=products.price_d.toString()
            Picasso.with(context).load(images[0]).into(image)

//            card.setOnClickListener {
//                Toast.makeText(context,"Hello",Toast.LENGTH_LONG).show()
//                callbacks?.onProductsSelected(productsItem.cat_id)
//            }


            image.setOnClickListener {

                var intent= Intent(context, MoreDetails::class.java)
                intent.putExtra("data",products)
                startActivity(intent)
            }


        }
    }


    private inner class CartAdapter(var cart : List<Products>) : RecyclerView.Adapter<CartHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartHolder {
            val view =


                LayoutInflater.from(parent.context).inflate(R.layout.products_list, parent, false)


            return CartHolder(view)

        }

        override fun getItemCount(): Int {
           return cart.size
        }

        override fun onBindViewHolder(holder: CartHolder, position: Int) {
            val cart = cart[position]
            holder.bind(cart)

        }

    }


}