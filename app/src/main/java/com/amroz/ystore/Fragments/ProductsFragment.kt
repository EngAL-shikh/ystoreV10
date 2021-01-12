package com.amroz.ystore.Fragments
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amroz.ystore.Featchers
import com.amroz.ystore.Models.Products
import com.amroz.ystore.R
import com.amroz.ystore.YstoreViewModels
import com.squareup.picasso.Picasso


class ProductsFragment : Fragment() {
    private lateinit var productsViewModel: ViewModel
    var count:Int=0

    private lateinit var RecyclerView: RecyclerView
    var type=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productsViewModel =
            ViewModelProviders.of(this).get(YstoreViewModels::class.java)
        //type=arguments?.getSerializable("type")as String
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


            var products = Featchers()
            val newsLiveData=products.fetchProducts()
            newsLiveData.observe(this, Observer {
                Log.d("test", "Response received: ${it}")
                RecyclerView.adapter = productsAdapter(it)

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
        fun newInstance(data: String): ProductsFragment {
            val args = Bundle().apply {
                putSerializable("type", data)
            }
            return ProductsFragment().apply {
                arguments = args
            }
        }
    }


    //  Holder
    private inner class UsersHolder(view: View) : RecyclerView.ViewHolder(view) {


        val title = view.findViewById(R.id.title) as TextView
        val deatils = view.findViewById(R.id.deatils) as TextView
        val price = view.findViewById(R.id.price) as TextView
        val Raitings = view.findViewById(R.id.Raitings) as TextView
        val image = view.findViewById(R.id.image) as ImageView


        fun bind(products: Products) {

            title.text = products.title
            deatils.text = products.details
            Raitings.text = products.rating.toString()
            Picasso.with(context).load(products.images).into(image)




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
}



