package com.amroz.ystore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

//import com.amroz.ystore.Fragments.ReportFragment

import com.amroz.ystore.Fragments.Catogrey_Fragment


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val isFragmentContainerEmpty = savedInstanceState == null
        if (isFragmentContainerEmpty) {
            supportFragmentManager
                .beginTransaction()

               //.add(R.id.fragmentContainer, ReportFragment.newInstance("report"))

                .add(R.id.fragmentContainer, Catogrey_Fragment.newInstance("Category"))

                .commit()
        }
    }

















    //
}