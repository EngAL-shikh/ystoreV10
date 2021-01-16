package com.amroz.ystore.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amroz.ystore.Featchers
import com.amroz.ystore.Models.Products
import com.amroz.ystore.Models.Users
import com.amroz.ystore.R
import com.amroz.ystore.YstoreViewModels
import com.squareup.picasso.Picasso


class ProfileFragment : Fragment() {


    private lateinit var usersViewModel: ViewModel
    var count:Int=0

    private lateinit var RecyclerView: RecyclerView
    private lateinit var ProRecyclerView: RecyclerView
    var type=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        usersViewModel =
            ViewModelProviders.of(this).get(YstoreViewModels::class.java)

        // type=arguments?.getSerializable("type")as String

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var products = Featchers()
        val newsLiveData=products.fetchProducts()
        newsLiveData.observe(this, Observer {
            Log.d("test", "Response received: ${it}")
            RecyclerView.adapter = ProductAdapter(it)

        })


        val usersLiveData=products.fetchUsersInfo(2)
        usersLiveData.observe(this, Observer {
            Log.d("test", "Response received: ${it}")
            ProRecyclerView.adapter = ProfileAdapter(it)

        })



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_profile, container, false)

        RecyclerView = view.findViewById(R.id.profile_rec)
        ProRecyclerView = view.findViewById(R.id.profile_rec)
        RecyclerView.layoutManager = GridLayoutManager(context,2)
        ProRecyclerView = view.findViewById(R.id.product_rec)
        ProRecyclerView.layoutManager = GridLayoutManager(context,1)
        return view
    }




    //  Holder users
    private inner class profileHolder(view: View) : RecyclerView.ViewHolder(view) {



        val name = view.findViewById(R.id.name) as TextView
        val email = view.findViewById(R.id.email) as TextView
        val phone = view.findViewById(R.id.phone) as ImageButton
        val message = view.findViewById(R.id.message) as ImageButton
        val user_raiting = view.findViewById(R.id.user_raiting) as ImageButton
        val address = view.findViewById(R.id.address) as TextView
        val iamge = view.findViewById(R.id.image) as ImageView


        fun bind(users: Users) {

            name.text = users.name
            email.text = users.email
//            phone.text = users.rating.toString()
//            message.text = users.rating.toString()
//            user_raiting.text = users.rating.toString()
            address.text = users.address.toString()
            Picasso.with(context).load(users.user_image).into(iamge)




        }








}
    //userAdapter
    inner class ProfileAdapter(var users: List<Users>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            var view: View



            view = layoutInflater.inflate(
                R.layout.profile_list,
                parent, false
            )

            return profileHolder(view)

        }


        override fun getItemCount(): Int {

            return users.size

        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

            val users=users[position]
            if(holder is profileHolder)
                holder.bind(users)


        }
    }



    private  inner class ProductHolder(view: View) : RecyclerView.ViewHolder(view){


        val title = view.findViewById(R.id.title) as TextView
        val deatils = view.findViewById(R.id.deatils) as TextView
        val price = view.findViewById(R.id.price) as TextView
        val Raitings = view.findViewById(R.id.Raitings) as TextView
        val image = view.findViewById(R.id.image) as ImageView



        fun bind2(products: Products){


            title.text = products.title
            deatils.text = products.details
            Raitings.text = products.rating.toString()
            Picasso.with(context).load(products.images).into(image)


        }

    }



    // Adapter
    inner class ProductAdapter(var products: List<Products>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): RecyclerView.ViewHolder {




                    val view = layoutInflater.inflate(
                        R.layout.products_list,
                        parent, false
                    )

                   return ProductHolder(view)













        }


        override fun getItemCount(): Int {


            count=products.size
            return products.size

        }




        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {



            val products = products[position]
            if (holder is ProductHolder){
                holder.bind2(products)




        }
    }





}
}

    //adapter 2

