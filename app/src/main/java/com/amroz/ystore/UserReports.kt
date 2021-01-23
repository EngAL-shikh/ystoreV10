package com.amroz.ystore

import android.content.pm.PackageManager
import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amroz.ystore.Models.Products
import com.itextpdf.text.Document
import com.itextpdf.text.Paragraph
import com.itextpdf.text.pdf.PdfWriter
import java.io.FileOutputStream
import java.util.*
import java.util.jar.Manifest

private lateinit var productsUserViewModel:YstoreViewModels
private lateinit var recyclerView: RecyclerView
private lateinit var productTitle: TextView
private lateinit var date: TextView
private lateinit var pdf : Button
var user_id :Int=3
val STORAGE_CODE=100
class UserReports : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_reports)




        val productsByUser = Featchers().fetchProductsByUser(user_id)
        productsByUser.observe(this, Observer {
            Log.d("ENS","${it}")

            recyclerView.adapter = ProductByUserAdapter(it)})
        recyclerView= findViewById(R.id.ProByUserRecActivity)
        recyclerView.layoutManager= GridLayoutManager(this,2)
    }

    private inner class ProductsByUserHolder(view: View) : RecyclerView.ViewHolder(view) {

        val productTitle = view.findViewById(R.id.prouct_title) as TextView
        val date = view.findViewById(R.id.date) as TextView
        val pdf=view.findViewById(R.id.pdf) as Button



        fun bind(products: Products) {


            productTitle.text = products.title
            date.text = products.rating.toString()


            pdf.setOnClickListener {
if(Build.VERSION.SDK_INT > Build.VERSION_CODES.M){
    if(checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_DENIED){
        val permissions= arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        requestPermissions(permissions,STORAGE_CODE)
    }
    else {
        savePdf()
    }
} else{
    savePdf()
}
            }
        }

        }


@RequiresApi(Build.VERSION_CODES.N)
fun savePdf(){
    val mDo=Document()
    try{
        val mFileName=SimpleDateFormat("yyyMMdd_HHmmss", Locale.getDefault()).format(System.currentTimeMillis())
    val mFilepath= if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        Environment.getStorageDirectory().toString() + "/" + mFileName +".pdf"
    } else {
        TODO("VERSION.SDK_INT < R")
    }



        PdfWriter.getInstance(mDo,FileOutputStream(mFilepath))
        mDo.open()
        val productLiveData = Featchers().fetchProductsByUser(user_id) as String
        mDo.add(Paragraph(productLiveData))
        mDo.close()
        Toast.makeText(this,"file pdf download",Toast.LENGTH_LONG).show()
    }
    catch (e:Exception){
    }
}





    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            STORAGE_CODE ->{
                if(grantResults.size>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){

                }
                else{
                    Toast.makeText(this,"permission daiened",Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    private inner class ProductByUserAdapter(var product:List<Products>): RecyclerView.Adapter<ProductsByUserHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsByUserHolder {
            var view: View = layoutInflater.inflate(R.layout.userreports_ltem,parent,false)
            return ProductsByUserHolder(view)
        }

        override fun getItemCount(): Int {
            return product.size
        }

        override fun onBindViewHolder(holder: ProductsByUserHolder, position: Int) {
            val getP= product[position]
            holder.bind(getP)
        }
    }


}
