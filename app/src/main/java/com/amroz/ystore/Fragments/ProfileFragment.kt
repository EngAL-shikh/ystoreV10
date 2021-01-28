package com.amroz.ystore.Fragments

import QueryPreferences
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amroz.ystore.*
import com.amroz.ystore.Chating.ContactsActivity
import com.amroz.ystore.Chating.MainChatActivity
import com.amroz.ystore.Models.Products
import com.amroz.ystore.Models.UserChat
import com.amroz.ystore.Models.Users
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso


class ProfileFragment : Fragment() {

    var idd: Int = 0
    private lateinit var usersViewModel: ViewModel
    var count: Int = 0
    private var adapter: ProductAdapter? = ProductAdapter(emptyList())
    private lateinit var RecyclerView: RecyclerView
    private lateinit var ProRecyclerView: RecyclerView
    private var firebaseAuth: FirebaseAuth? = null
    private var authStateListener: FirebaseAuth.AuthStateListener? = null
    private var googleApiClient: GoogleApiClient? = null
    var type = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        usersViewModel =
            ViewModelProviders.of(this).get(YstoreViewModels::class.java)

        // type=arguments?.getSerializable("type")as String

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var products = Featchers()
        if (QueryPreferences.getStoredQuery(context!!) != "") {


            val newsLiveData = products.fetchProductsByUser(
                QueryPreferences.getStoredQueryUserid(context!!).toInt()
            )
            newsLiveData.observe(this, Observer {
                Log.d("test", "Response received: ${it}")
                RecyclerView.adapter = ProductAdapter(it)

            })
        }

//        var shaerd=context?.getSharedPreferences("userid",0)
//       var id= shaerd?.getString("id",null)?.toInt()
        if (QueryPreferences.getStoredQuery(context!!) != "") {
            val usersLiveData = products.fetchUsersInfo(
                QueryPreferences.getStoredQueryUserid(context!!).toString().toInt()
            )
            Log.d("diiiiii", id.toString())
            usersLiveData.observe(this, Observer {
                Log.d("test", "Response received: ${it}")
                ProRecyclerView.adapter = ProfileAdapter(it)

            })
        } else {
            val intent = Intent(context, LoginActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_profile, container, false)

        RecyclerView = view.findViewById(R.id.profile_rec)
        ProRecyclerView = view.findViewById(R.id.profile_rec)
        RecyclerView.layoutManager = GridLayoutManager(context, 1)
        ProRecyclerView = view.findViewById(R.id.product_rec)
        ProRecyclerView.layoutManager = GridLayoutManager(context, 1)
        return view
    }


    //  Holder users
    private inner class profileHolder(view: View) : RecyclerView.ViewHolder(view) {


        val name = view.findViewById(R.id.name) as TextView
        val logout = view.findViewById(R.id.logout) as ImageButton
        val email = view.findViewById(R.id.email) as TextView
        val phone = view.findViewById(R.id.phone) as ImageButton
        val message = view.findViewById(R.id.message) as ImageButton
        val user_raiting = view.findViewById(R.id.user_raiting) as ImageButton
        val address = view.findViewById(R.id.address) as TextView
        val iamge = view.findViewById(R.id.image) as ImageView
        val edite = view.findViewById(R.id.bt_edite) as ImageView
        val Your_Reports = view.findViewById(R.id.Your_Reports) as ImageButton


        fun bind(users: Users) {

            name.text = users.name
            email.text = users.email
//            phone.text = users.rating.toString()
//            message.text = users.rating.toString()
//            user_raiting.text = users.rating.toString()
            address.text = users.address.toString()
            Picasso.with(context).load(users.user_image).into(iamge)


            edite.setOnClickListener {

                var intent = Intent(context, UpdateProfile::class.java)
                intent.putExtra("data", users)
                startActivity(intent)
            }

            Your_Reports.setOnClickListener {

                var intent = Intent(context, UserReports::class.java)
                startActivity(intent)
            }
            logout.setOnClickListener {
                signOut()

//                var intent=Intent(context,LoginActivity::class.java)
//                startActivity(intent)
//                activity?.finish()
            }

            message.setOnClickListener {

                // addContacts(users.chat_id,users.name)
                var intent = Intent(context, MainChatActivity::class.java)
                startActivity(intent)

            }


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

            val users = users[position]
            if (holder is profileHolder)
                holder.bind(users)


        }
    }


    private inner class ProductHolder(view: View) : RecyclerView.ViewHolder(view) {


        val title = view.findViewById(R.id.title) as TextView
        val deatils = view.findViewById(R.id.details) as TextView
        val price = view.findViewById(R.id.price) as TextView

        //  val Raitings = view.findViewById(R.id.Raitings) as TextView
        val image = view.findViewById(R.id.image) as ImageView
        val card_my_product = view.findViewById(R.id.card_my_product) as CardView
        val delete = view.findViewById(R.id.delete) as Button


        fun bind2(products: Products) {

            var images = products.images.split(",").toTypedArray()
            title.text = products.title
            deatils.text = products.details
            // Raitings.text = products.rating.toString()
            price.text = "$" + products.price_d.toString()

            Picasso.with(context).load(images[0]).into(image)





            card_my_product.setOnClickListener {

                //  delet_product(products.product_id)
                var intent = Intent(context, UpdateProduct::class.java)
                intent.putExtra("data", products)
                startActivity(intent)
                onCreateAnimation(3000, true, R.anim.nav_default_pop_exit_anim)


            }


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
                R.layout.myproduct_list,
                parent, false
            )

            return ProductHolder(view)


        }


        override fun getItemCount(): Int {


            count = products.size
            return products.size

        }


        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


            val products = products[position]
            if (holder is ProductHolder) {
                holder.bind2(products)

                holder.delete.setOnClickListener {
                    delet_product(products.product_id)
                    notifyDataSetChanged()
                    notifyItemChanged(products.product_id)
                }


            }
        }


    }

    fun delet_product(id: Int) {

        val builder = AlertDialog.Builder(context)
        //  builder.setTitle("AlertDialog")
        builder.setMessage("Are you sure ")

        // add the buttons

        // add the buttons
        builder.setPositiveButton("Continue") { _, _ ->
            var del_cat = ManagementFeatchers()
            del_cat.deleteProduct(id)


        }
        builder.setNegativeButton("Cancel") { _, _ ->

        }
        val dialog = builder.create()
        dialog.show()


    }

    private fun signOut() {
        var auth = FirebaseAuth.getInstance()
        auth?.signOut()
        QueryPreferences.setStoredQuery(context!!, "")
//        if (googleApiClient!!.isConnected) {
//            googleApiClient!!.disconnect()
//        }

        // firebaseAuth!!.removeAuthStateListener(this.authStateListener!!)

//        var shared= context?.getSharedPreferences("admin",0)
//        var edit=shared?.edit()
//        edit?.putString("rule","0")
//        edit?.commit()
        // QueryPreferences.setStoredQuery(context!!,"")
        var i = Intent(context, LoginActivity::class.java)
        startActivity(i)
    }


//    fun updateUI(product: Products) {
//
//        adapter = ProductAdapter(product)
//        product_rec.adapter = adapter
//    }

    fun addContacts(chatid: String, username: String) {


        var db: FirebaseFirestore = FirebaseFirestore.getInstance()


        db = FirebaseFirestore.getInstance()


        val user = UserChat(chatid, username)
        db.collection("contacts")
            .document(QueryPreferences.getStoredQueryChatid(context!!)).collection("userContacts")
            .document(chatid)
            .set(user)
            .addOnCompleteListener {

                if (it.isSuccessful) {
                    Toast.makeText(context, "added", Toast.LENGTH_LONG).show()
                    var intent = Intent(context, ContactsActivity::class.java)
                    startActivity(intent)

                } else {
                    Toast.makeText(context, "filde to add ${it.exception}", Toast.LENGTH_LONG)
                        .show()
                    Log.d("test", it.exception.toString())

                }
            }


    }


}



