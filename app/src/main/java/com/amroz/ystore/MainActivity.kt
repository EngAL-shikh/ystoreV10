package com.amroz.ystore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth

//import com.amroz.ystore.Fragments.ReportFragment


class MainActivity : AppCompatActivity() {
    private lateinit var usersViewModel: ViewModel
    private var firebaseAuth: FirebaseAuth? = null
    private var authStateListener: FirebaseAuth.AuthStateListener? = null
    private var googleApiClient: GoogleApiClient? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        usersViewModel =
            ViewModelProviders.of(this).get(YstoreViewModels::class.java)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        if (QueryPreferences.getStoredQuery(this@MainActivity)== "admin"){
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)


        }


        Toast.makeText(this,QueryPreferences.getStoredQuery(this),Toast.LENGTH_LONG).show()





        firebaseAuth = FirebaseAuth.getInstance()
        authStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val firebaseUser = firebaseAuth.currentUser
            if (firebaseUser == null) {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        // shaerd2.getString("id",null)
        if(QueryPreferences.getStoredQuery(this)=="") {
            //navView.menu.findItem(R.id.profile_nv).isVisible=false
            navView.menu.findItem(R.id.report_nv).isVisible=false
            navView.menu.findItem(R.id.cart_nv).isVisible=false

        }else{
            //navView.menu.findItem(R.id.profile_nv).isVisible=true
            navView.menu.findItem(R.id.report_nv).isVisible=true
            navView.menu.findItem(R.id.cart_nv).isVisible=true

        }


        val navController = findNavController(R.id.nav_host_fragment)

        navView.setupWithNavController(navController)
        var admin=intent.extras?.getString("admin")

        if (admin==1.toString()){

            var intent = Intent(this,Dashboard::class.java)
            intent.putExtra("admin",admin)
            startActivity(intent)
        }else{


        }
    }


}