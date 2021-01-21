package com.amroz.ystore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.amroz.ystore.Fragments.ChatFragment
import com.amroz.ystore.Fragments.ReportFragment


class ChatingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chating)


        val isFragmentContainerEmpty = savedInstanceState == null
        if (isFragmentContainerEmpty) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container, ChatFragment.newInstance(""))
                .commit()
        }
    }
}