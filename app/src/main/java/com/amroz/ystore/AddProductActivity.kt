package com.amroz.ystore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProviders
import com.amroz.ystore.Models.Products

class AddProductActivity : AppCompatActivity() {
    private val ystoreViewModels: YstoreViewModels by lazy {
        ViewModelProviders.of(this).get(YstoreViewModels::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)
        val productTitle: EditText =findViewById(R.id.proTitle)
        var productTitletext=productTitle.getText().toString()
        val productDetails:EditText=findViewById(R.id.proDetails)
        var productDetailstext=productDetails.getText().toString()
        val productImage: Button =findViewById(R.id.proImage)
        val productColor:EditText=findViewById(R.id.proColor)
        var productColortext=productColor.getText().toString()
        val productDate:EditText=findViewById(R.id.proDate)
        var productDatetext=productDate.getText().toString()
        val productFeature:EditText=findViewById(R.id.proFeature)
        var productFeaturetext=productFeature.getText().toString()
        val productRating:EditText=findViewById(R.id.proRating)
        var productRatingtext: Int =productRating.getText().toString().toInt()
        val productPriceY:EditText=findViewById(R.id.proPrice1)
        var productPriceYtext=productPriceY.getText().toString().toDouble()
        val productPriceD:EditText=findViewById(R.id.proPrice2)
        var productPriceDtext=productPriceD.getText().toString().toDouble()
        val productCategory:EditText=findViewById(R.id.procat)
        var productCategorytext=productCategory.getText().toString().toInt()
        val productReport:EditText=findViewById(R.id.proReport)
        var productReporttext=productReport.getText().toString().toInt()
        val add:Button=findViewById(R.id.add_product)
       // var product= Products(null,productTitletext,productDetailstext,productColortext,"",productDatetext,productFeaturetext,productRatingtext,
      //  productPriceYtext,productPriceDtext,0,productCategorytext,productReporttext)
        add.setOnClickListener {
            ystoreViewModels.addProduct(productTitletext,productDetailstext,"",productColortext,productFeaturetext, productRatingtext
                ,productPriceYtext,productPriceDtext,1,productCategorytext,productReporttext,"","")

        }

    }
}