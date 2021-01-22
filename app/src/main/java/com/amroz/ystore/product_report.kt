package com.amroz.ystore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.amroz.ystore.Models.Products

class product_report : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val ystoreViewModels: YstoreViewModels by lazy {
            ViewModelProviders.of(this).get(YstoreViewModels::class.java)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_report)



       var reson_report=""
        var report1: TextView =findViewById(R.id.report1)
        var report2: TextView =findViewById(R.id.report2)
        var report3: TextView =findViewById(R.id.report3)
        var report4: TextView =findViewById(R.id.report4)
        var save:Button=findViewById(R.id.save)
var cancel:Button=findViewById(R.id.cancle)

        var products=intent.getSerializableExtra("data") as Products

        report1.setOnClickListener {
            reson_report= report1.text as String
        }
        report2.setOnClickListener {
            reson_report= report2.text as String
        }
        report3.setOnClickListener {
            reson_report= report3.text as String
        }
        report4.setOnClickListener {
            reson_report= report4.text as String
        }

    save.setOnClickListener {
        Log.d("repo", reson_report)
        Log.d("repo",products.product_id.toString() )
        Log.d("repo", products.user_id.toString())

        ystoreViewModels.addReportProduct(0,reson_report,products.product_id,products.user_id)
        Toast.makeText(applicationContext, "  This user has been reported", Toast.LENGTH_SHORT).show()
    }

        cancel.setOnClickListener {
            var intent= Intent(this,MoreDetails::class.java)
            startActivity(intent)
        }

    }
}