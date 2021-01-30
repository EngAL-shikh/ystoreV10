package com.amroz.ystore

import QueryPreferences
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.amroz.ystore.Fragments.Catogrey_Fragment
import com.amroz.ystore.Fragments.ProductsFragment
import com.amroz.ystore.Fragments.ReportFragment
import com.amroz.ystore.Fragments.UsersFragment
import com.squareup.picasso.Picasso

class Dashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        var cat: CardView = findViewById(R.id.card_cat)
        var card_users: CardView = findViewById(R.id.card_users)
        var card_products: CardView = findViewById(R.id.card_products)
        var card_reports: CardView = findViewById(R.id.card_reports)
        var contact: CardView = findViewById(R.id.conactDashboard)
        var name: TextView = findViewById(R.id.user_name)
        var image: ImageView = findViewById(R.id.image)
        var address: TextView = findViewById(R.id.address)
        var logout: TextView = findViewById(R.id.logout)



        name.text = QueryPreferences.getStoredQueryUsername(this)
        address.text = QueryPreferences.getStoredQueryUseraddress(this)
        Picasso.get().load(QueryPreferences.getStoredQueryUserimage(this)).into(image)


        var admin = intent.extras?.getString("admin")
        cat.setOnClickListener {

            contact.visibility = View.GONE
            val isFragmentContainerEmpty = savedInstanceState == null
            if (isFragmentContainerEmpty) {
                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.container, Catogrey_Fragment.newInstance("admin"))
                    .commit()
            }

        }
        card_reports.setOnClickListener {

            contact.visibility = View.GONE
            val isFragmentContainerEmpty = savedInstanceState == null
            if (isFragmentContainerEmpty) {
                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.container, ReportFragment.newInstance())
                    .commit()
            }
//
        }
        card_users.setOnClickListener {

            contact.visibility = View.GONE
            val isFragmentContainerEmpty = savedInstanceState == null
            if (isFragmentContainerEmpty) {
                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.container, UsersFragment.newInstance())
                    .commit()
            }

        }
        card_products.setOnClickListener {

            contact.visibility = View.GONE
            val isFragmentContainerEmpty = savedInstanceState == null
            if (isFragmentContainerEmpty) {
                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.container, ProductsFragment.newInstance())
                    .commit()
            }

        }

        logout.setOnClickListener {
//            var shared=getSharedPreferences("admin",0)
//            var edit=shared?.edit()
//            edit?.putString("rule","0")
//            edit?.commit()
            var intent = Intent(this, LoginActivity::class.java)
            QueryPreferences.setStoredQuery(this, "")
            startActivity(intent)

        }


    }

    override fun onBackPressed() {
        super.onBackPressed()
        var intent = Intent(this, Dashboard::class.java)
        startActivity(intent)
        finish()
    }


}