package com.amroz.ystore.Fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amroz.ystore.Featchers
import com.amroz.ystore.Models.Products
import com.amroz.ystore.MoreDetails
import com.amroz.ystore.R
import com.amroz.ystore.YstoreViewModels
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_catogrey.*
import kotlinx.android.synthetic.main.fragment_products.*


class ProductsFragment : Fragment() {
    var searchlist=ArrayList<Products>()
    var posts=ArrayList<Products>()
//    interface Callbacks {
//        fun onProductsSelected(catId: Int)
//    }

//    private var callbacks: Callbacks? = null

    private lateinit var productsViewModel: ViewModel
    var count:Int=0

    private lateinit var RecyclerView: RecyclerView
    var type=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productsViewModel =
            ViewModelProviders.of(this).get(YstoreViewModels::class.java)

       // type=arguments?.getSerializable("type")as String

    }

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        callbacks = context as Callbacks?
//    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var search= view.findViewById(R.id.searchfromT) as SearchView

            var products = Featchers()
            val newsLiveData=products.fetchProducts()
            newsLiveData.observe(this, Observer {
                Log.d("test", "Response received: ${it}")
                RecyclerView.adapter = productsAdapter(it)
                search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextChange(txtsearch: String?): Boolean {

                        searchlist.clear()
                        for (i in it){

                            if (i.title.contains(txtsearch.toString())){
                                searchlist.add(i)
                            }

                        }
                        RecyclerView.layoutManager=GridLayoutManager(context,2)
                        RecyclerView.adapter = productsAdapter(searchlist)













                        return true

                    }


                    override fun onQueryTextSubmit(p0: String?): Boolean {






                        return true
                    }
                })

            })






    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_products, container, false)

        RecyclerView = view.findViewById(R.id.products_rec)
        RecyclerView.layoutManager = GridLayoutManager(context,2)
        return view
    }

    companion object {
        fun newInstance() = ProductsFragment()
    }


    //  Holder
    private inner class UsersHolder(view: View) : RecyclerView.ViewHolder(view) {

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
            Picasso.with(context).load(images[0]).into(image)

//            card.setOnClickListener {
//                Toast.makeText(context,"Hello",Toast.LENGTH_LONG).show()
//                callbacks?.onProductsSelected(productsItem.cat_id)
//            }


            image.setOnClickListener {

                var intent= Intent(context,MoreDetails::class.java)
                intent.putExtra("data",products)
                startActivity(intent)
            }


        }




    }

    // Adapter
    inner class productsAdapter(var products: List<Products>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): RecyclerView.ViewHolder {


            var view: View = layoutInflater.inflate(
                R.layout.products_list,
                parent, false
            )

            return UsersHolder(view)

        }


        override fun getItemCount(): Int {

            count=products.size
            return products.size

        }


        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

            val news = products[position]
            if (holder is UsersHolder)
                holder.bind(news)


        }
    }

//    override fun onDetach() {
//        super.onDetach()
//        callbacks = null
//    }

}



