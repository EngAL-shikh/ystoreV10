package com.amroz.ystore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.amroz.ystore.Models.Products


class UpdateProduct : AppCompatActivity() {


    lateinit var titleProduct:EditText
    lateinit var details: EditText
    lateinit var color: EditText
    lateinit var product_features: EditText
    lateinit var price_y: EditText
    lateinit var price_d: EditText
    lateinit var btnUpdate: Button
    lateinit var btnCancel: Button
    lateinit var productViewModel: YstoreViewModels
    lateinit var product: Products

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.update_product)
        titleProduct =findViewById(R.id.ed_title)
        details=findViewById(R.id.ed_details)
        color=findViewById(R.id.ed_color)
        product_features=findViewById(R.id.ed_product_features)
        price_d=findViewById(R.id.ed_price_d)
        price_y=findViewById(R.id.ed_price_y)
        btnUpdate=findViewById(R.id.btn_update)
        btnCancel=findViewById(R.id.btn_cancel)

        productViewModel=
            ViewModelProviders.of(this).get(YstoreViewModels::class.java)
        product=Products()

        details.setText(product.details)
        color.setText(product.color)
        product_features.setText(product.product_features)
        price_d.setText(product.price_d.toString())
        price_y.setText(product.price_y.toString())
        titleProduct.setText(product.title)
        val pdetails=details.text.toString()
        val ptitleProduct=titleProduct.text.toString()
        val pcolor=color.text.toString()
        val pproduct_featuress=product_features.text.toString()
        val pprice_y=price_y.text.toString()
        val pprice_d=price_d.text.toString()
        btnUpdate.setOnClickListener {
            if (ptitleProduct.isNotEmpty() && pdetails.isNotEmpty() &&pcolor.isNotEmpty()&& pproduct_featuress.isNotEmpty()
                && pprice_d.isNotEmpty() && pprice_y.isNotEmpty()) {
Log.d("prod",pdetails)
                var id=product.product_id
                val response=
                    productViewModel.updateProduct(id,ptitleProduct,
                        pdetails,pcolor,
                        pproduct_featuress,pprice_y.toInt(),pprice_d.toInt())
                response.observe(
                    this,
                    Observer { message ->
                        Toast.makeText(this, "updated", Toast.LENGTH_SHORT).show()
                    })
            }else{
                Toast.makeText(this, "empty", Toast.LENGTH_SHORT).show()
          }

        btnCancel.setOnClickListener {
           // finish()
        }

    }
//    fun updateProduct(products: Products){
//        var id=product.product_id
//        val response=
//            productViewModel.updateProduct(id,ptitleProduct,pdetails,pproduct_featuress,pcolor,pprice_y.toInt(),pprice_d.toInt())
//        response.observe(
//            this,
//            Observer { message ->
//                Toast.makeText(this, "updated", Toast.LENGTH_SHORT).show()
//            })
//    }
}}