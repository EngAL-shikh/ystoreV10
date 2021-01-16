package com.amroz.ystore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProviders

class AddCategoryActivity : AppCompatActivity() {
    private val ystoreViewModels: YstoreViewModels by lazy {
        ViewModelProviders.of(this).get(YstoreViewModels::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_category)
        val catTitle:EditText=findViewById(R.id.catTitle)
        var cat_title:String=catTitle.getText().toString()
        val add_cat:Button=findViewById(R.id.add_cat)
        add_cat.setOnClickListener {
            val cat=Category(null,cat_title,"")
            if(catTitle!=null)
                ystoreViewModels.addCategory(cat_title,"")
        }
    }
}