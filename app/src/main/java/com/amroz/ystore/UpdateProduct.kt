package com.amroz.ystore

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.amroz.ystore.Models.Products


class UpdateProduct : AppCompatActivity() {


    lateinit var titleProduct: EditText
    lateinit var details: EditText
    lateinit var color: EditText
    lateinit var product_features: EditText
    lateinit var price_y: EditText
    lateinit var price_d: EditText
    lateinit var btnUpdate: Button
    lateinit var productViewModel: YstoreViewModels
    lateinit var product: Products

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.update_product)
        titleProduct = findViewById(R.id.ed_title)
        details = findViewById(R.id.ed_details)
        color = findViewById(R.id.ed_color)
        product_features = findViewById(R.id.ed_product_features)
        price_d = findViewById(R.id.ed_price_d)
        price_y = findViewById(R.id.ed_price_y)
        btnUpdate = findViewById(R.id.btn_update)


        productViewModel =
            ViewModelProviders.of(this).get(YstoreViewModels::class.java)
        product = Products()


        var product = intent.getSerializableExtra("data") as Products

        details.setText(product.details)
        color.setText(product.color)
        product_features.setText(product.product_features)
        price_d.setText(product.price_d.toString())
        price_y.setText(product.price_y.toString())
        titleProduct.setText(product.title)
        val pdetails = details.text.toString()

        btnUpdate.setOnClickListener {
//            if (ptitleProduct.isNotEmpty() && pdetails.isNotEmpty() &&pcolor.isNotEmpty()&& pproduct_featuress.isNotEmpty()
//                && pprice_d.isNotEmpty() && pprice_y.isNotEmpty()) {
            Log.d("prod", pdetails)
            var id = product.product_id
            val response =
                productViewModel.updateProduct(
                    product.product_id,
                    titleProduct.text.toString(),
                    details.text.toString(),
                    color.text.toString(),
                    product_features.text.toString(),
                    price_y.text.toString().toInt(),
                    price_d.text.toString().toInt()
                )

            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

            response.observe(
                this,
                Observer { message ->
                    Toast.makeText(this, "updated", Toast.LENGTH_SHORT).show()
                })
        }
//            }else{
//                Toast.makeText(this, "empty", Toast.LENGTH_SHORT).show()
//          }
//

//
//    }


    }

}