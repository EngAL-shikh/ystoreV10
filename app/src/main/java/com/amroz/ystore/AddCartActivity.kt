package com.amroz.ystore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProviders
import com.amroz.ystore.Models.Cart

class AddCartActivity : AppCompatActivity() {
    private val ystoreViewModels: YstoreViewModels by lazy {
        ViewModelProviders.of(this).get(YstoreViewModels::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_cart)
        val proId: EditText =findViewById(R.id.pro_id)
        val quantity:EditText=findViewById(R.id.quantity_id)
        val add:Button=findViewById(R.id.add_cart)
        var proIdInt:Int=proId.getText().toString().toInt()
        var quantityInt:Int=quantity.getText().toString().toInt()
        add.setOnClickListener {

            if(proIdInt!=null && quantityInt!=null)
                ystoreViewModels.addCart(1,proIdInt,quantityInt)
        }
    }
}