package com.amroz.ystore.Fragments

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.QuickViewConstants
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.amroz.ystore.*
import com.amroz.ystore.Chating.MainChatActivity
import com.amroz.ystore.Models.Products
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_catogrey.*
import kotlinx.android.synthetic.main.fragment_products.*


class ProductsFragment : Fragment() {
    var searchlist=ArrayList<Products>()
    var posts=ArrayList<Products>()
    private val ystoreViewModels: YstoreViewModels by lazy {
        ViewModelProviders.of(this).get(YstoreViewModels::class.java)
    }
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




    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var search= view.findViewById(R.id.searchfromT) as SearchView

            var products = Featchers()
            val newsLiveData=products.fetchProducts()
            newsLiveData.observe(this@ProductsFragment, Observer {
                Log.d("test", "Response received: ${it}")
                RecyclerView.adapter = productsAdapter(it)
                search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextChange(txtsearch: String?): Boolean {

                        searchlist.clear()
                        for (i in it){
                            if (i.title.contains(txtsearch.toString())){
                                searchlist.add(i)
                            } }
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
        val sittings= view.findViewById(R.id.sittings) as ImageButton
        val nave_view2= view.findViewById(R.id.nav_view2) as NavigationView
        val close_nav= view.findViewById(R.id.btn_close_nav) as ImageView
        val login= view.findViewById(R.id.login) as LinearLayout
        val chating= view.findViewById(R.id.chating) as LinearLayout
        val dashboard= view.findViewById(R.id.dashbourd) as LinearLayout
        RecyclerView.layoutManager = GridLayoutManager(context,2)
        val imagenav= view.findViewById(R.id.imagenav) as ImageView
        val username_nav= view.findViewById(R.id.username_nav) as TextView
        val Myfavorite= view.findViewById(R.id.Myfavorite) as LinearLayout

        sittings.setOnClickListener {
            nave_view2.visibility=View.VISIBLE
            YoYo.with(Techniques.BounceInLeft)
                .duration(2000)
                .playOn(nave_view2)

        }
        close_nav.setOnClickListener {
            nave_view2.visibility=View.GONE
            YoYo.with(Techniques.BounceInRight)
                .duration(2000)
                .playOn(nave_view2)
        }

        dashboard.setOnClickListener {
            var shaerd=context?.getSharedPreferences("dashbourd",0)
            var edit=shaerd?.edit()
            edit?.putString("dashbourd","22")
            edit?.commit()
            var intent=Intent(context,Dashboard::class.java)
            startActivity(intent)

        }

        if (QueryPreferences.getStoredQuery(context!!)=="user" ||QueryPreferences.getStoredQuery(context!!)=="admin"){
            login.visibility=View.GONE

            Picasso.get().load(QueryPreferences.getStoredQueryUserimage(context!!)).into(imagenav)
            username_nav.text=QueryPreferences.getStoredQueryUsername(context!!)
        }else
        {
            chating.visibility=View.GONE
            Myfavorite.visibility=View.GONE
            login.visibility=View.VISIBLE
            dashboard.visibility=View.GONE

        }
        login.setOnClickListener {
            var intent=Intent(context,LoginActivity::class.java)
            startActivity(intent)
        }
        chating.setOnClickListener {
            var intent=Intent(context,MainChatActivity::class.java)
            startActivity(intent)
        }
        Myfavorite.setOnClickListener {
            var intent=Intent(context,FavoriteActivity::class.java)
            startActivity(intent)
        }
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
        val Raitings = view.findViewById(R.id.Raitings) as ImageView
        val image = view.findViewById(R.id.image) as ImageView
        val card= view.findViewById(R.id.ProductCard) as CardView
        val remov_fevort= view.findViewById(R.id.remove_fivort_Raiting) as ImageView
        val add_fevort= view.findViewById(R.id.add_fivort) as ImageView
        val bt_add_to_cart= view.findViewById(R.id.bt_add_to_cart) as ImageView




        fun bind(products: Products) {

            var images=  products.images.split(",").toTypedArray()
            productsItem=products
            title.text = products.title
            deatils.text = products.details
            //Raitings.text=products.rating_vote.toString()
            price.text="$ "+products.price_d.toString()
            Picasso.get().load(images[0]).into(image)

//            card.setOnClickListener {
//                Toast.makeText(context,"Hello",Toast.LENGTH_LONG).show()
//                callbacks?.onProductsSelected(productsItem.cat_id)
//            }

            Log.d("btnupdate",QueryPreferences.getStoredQuery(context!!))

            if (QueryPreferences.getStoredQuery(context!!)== "admin"){

                dashbourd.visibility=View.VISIBLE
            }else{
                dashbourd.visibility=View.GONE

            }



            bt_add_to_cart.setOnClickListener {
                var id= QueryPreferences.getStoredQueryUserid(context!!).toInt()
                ystoreViewModels.addCart(id!!,products.product_id,1)

               // Toast.makeText(this,"Product added to your Cart",Toast.LENGTH_LONG).show()
                Log.d("cartadd",QueryPreferences.getStoredQueryUserid(context!!).toString())
            }
            image.setOnClickListener {

                var intent= Intent(context,MoreDetails::class.java)
                intent.putExtra("data",products)
                startActivity(intent)
            }

            add_fevort.setOnClickListener {
                remov_fevort.visibility=View.VISIBLE
                add_fevort.visibility=View.GONE

                    //Toast.makeText(this,"Product added to your Cart",Toast.LENGTH_LONG).show()
                  //  Log.d("cartadd",QueryPreferences.getStoredQueryUserid(context!!).toString())

                YoYo.with(Techniques.BounceInDown)
                    .duration(2000)
                    .playOn(remov_fevort)

            }
            remov_fevort.setOnClickListener {


                if (QueryPreferences.getStoredQuery(context!!)=="user" ||QueryPreferences.getStoredQuery(context!!)=="admin"){
                    var id= QueryPreferences.getStoredQueryUserid(context!!).toInt()
                    ystoreViewModels.addFovarite(id!!,products.product_id)
                    remov_fevort.visibility=View.GONE
                    add_fevort.visibility=View.VISIBLE
                    YoYo.with(Techniques.BounceIn)
                        .duration(2000)
                        .playOn(add_fevort)
                }else{

                    var intent = Intent(context, LoginActivity::class.java)
                    startActivity(intent)
                }
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



