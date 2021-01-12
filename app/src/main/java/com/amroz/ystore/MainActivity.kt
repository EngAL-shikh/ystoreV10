package com.amroz.ystore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
//import com.amroz.ystore.Fragments.ReportFragment


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val isFragmentContainerEmpty = savedInstanceState == null
//        if (isFragmentContainerEmpty) {
//            supportFragmentManager
//                .beginTransaction()
//
//

//                .add(R.id.fragmentContainer, UsersFragment.newInstance("Users"))
//
//                .commit()
//        }

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)

        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.products_nv, R.id.cats_nv, R.id.report_nv))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }

















    //
