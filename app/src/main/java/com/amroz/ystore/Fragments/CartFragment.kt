package com.amroz.ystore.Fragments

import QueryPreferences
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amroz.ystore.Models.Products
import com.amroz.ystore.MoreDetails
import com.amroz.ystore.Payment.CheckoutActivity
import com.amroz.ystore.R
import com.amroz.ystore.YstoreViewModels
import com.squareup.picasso.Picasso


class CartFragment : Fragment() {
    var count = 1
    var totalprice = 0
    private val cartViewModel: YstoreViewModels by lazy {
        ViewModelProviders.of(this).get(YstoreViewModels::class.java)
    }
    private lateinit var cartRecyclerView: RecyclerView
    private lateinit var checkout: AppCompatButton
    private lateinit var total: TextView
    var type = ""


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//
//        var shaerd=context?.getSharedPreferences("userid",0)
//        var id= shaerd?.getString("id",null)?.toInt()

        cartViewModel.fetchCart(QueryPreferences.getStoredQueryUserid(context!!).toString().toInt())
            .observe(viewLifecycleOwner, Observer {
                it?.let {
                    cartRecyclerView.adapter = CartAdapter(it)
                }
            })


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_cart, container, false)
        cartRecyclerView = view.findViewById(R.id.cart_recycler_view)
        cartRecyclerView.layoutManager = LinearLayoutManager(context)
        checkout = view.findViewById(R.id.checkout)
        total = view.findViewById(R.id.total)

        total.text = totalprice.toString()

        checkout.setOnClickListener {

            var intent = Intent(context, CheckoutActivity::class.java)
            startActivity(intent)
        }
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

    private inner class CartHolder(view: View) : RecyclerView.ViewHolder(view) {
        private lateinit var productsItem: Products

        val title = view.findViewById(R.id.title) as TextView

        // val deatils = view.findViewById(R.id.deatils) as TextView
        val price = view.findViewById(R.id.price) as TextView

        //val Raitings = view.findViewById(R.id.Raitings) as TextView
        val image = view.findViewById(R.id.image) as ImageView
        val addqn = view.findViewById(R.id.addqn) as ImageButton
        val subqn = view.findViewById(R.id.subqn) as ImageButton
        val tv_qty = view.findViewById(R.id.tv_qty) as TextView
        val card_cart = view.findViewById(R.id.card_cart) as LinearLayout
        //val card= view.findViewById(R.id.ProductCard) as CardView


        fun bind(products: Products) {
            var images = products.images.split(",").toTypedArray()
            productsItem = products
            title.text = products.title
            // quntity.text = products.rating
            // deatils.text = products.details
            // Raitings.text = products.rating.toString()
            price.text = "$ " + products.price_d.toString()

            Picasso.with(context).load(images[0]).into(image)

            for (i in 0..products.price_d) {

                totalprice += products.price_d
            }

//            card.setOnClickListener {
//                Toast.makeText(context,"Hello",Toast.LENGTH_LONG).show()
//                callbacks?.onProductsSelected(productsItem.cat_id)
//            }


            card_cart.setOnClickListener {

                var intent = Intent(context, MoreDetails::class.java)
                intent.putExtra("data", products)
                startActivity(intent)
            }
            addqn.setOnClickListener {
                ++count
                tv_qty.text = count.toString()
            }
            subqn.setOnClickListener {

                count--
                tv_qty.text = count.toString()
                if (count == 0) {

                    count = 10
                }
            }

        }
    }


    private inner class CartAdapter(var cart: List<Products>) : RecyclerView.Adapter<CartHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartHolder {
            val view =


                LayoutInflater.from(parent.context).inflate(R.layout.cartlist, parent, false)


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