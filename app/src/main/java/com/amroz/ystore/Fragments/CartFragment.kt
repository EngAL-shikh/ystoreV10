package com.amroz.ystore.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amroz.ystore.Featchers
import com.amroz.ystore.Models.Cart
import com.amroz.ystore.R
import com.amroz.ystore.YstoreViewModels


class CartFragment : Fragment() {

    private lateinit var cartViewModel: ViewModel
    private lateinit var cartRecyclerView: RecyclerView
    var type=""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cartViewModel=ViewModelProviders.of(this).get(YstoreViewModels::class.java)
        type=arguments?.getString("type") as String

        }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(type == "Cart"){
            var cart=Featchers()
            var liveDta= cart.fetchCart()
            liveDta.observe(this, Observer {
                Log.d("test", "Response received: ${it}")
                cartRecyclerView.adapter= CartAdapter(it)
            })
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_cart, container, false)
        cartRecyclerView= view.findViewById(R.id.cart_recycler_view)
        cartRecyclerView.layoutManager= LinearLayoutManager(context)
        return view
    }

    companion object {
        fun newInstance(data: String): CartFragment {
            val args = Bundle().apply {
                putSerializable("type", data)
            }
            return CartFragment().apply {
                arguments = args
            }
        }
    }

    private inner class CartHolder(view: View) : RecyclerView.ViewHolder(view){
        var cart_id= view.findViewById(R.id.cart_id) as TextView
        var user_id = view.findViewById(R.id.user_id) as TextView
        var product_id= view.findViewById(R.id.product_id) as TextView
        var quantity= view.findViewById(R.id.quantity) as TextView

        fun bind(cart: Cart){
            cart_id.text= cart.cart_id.toString()
            user_id.text= cart.user_id
            product_id.text= cart.product_id.toString()
            quantity.text= cart.Quantity.toString()
        }
    }


    private inner class CartAdapter(var cart : List<Cart>) : RecyclerView.Adapter<CartHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.cart_list, parent, false)

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