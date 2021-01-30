package com.amroz.ystore

import android.content.Context
import android.content.pm.PackageManager

import java.text.SimpleDateFormat
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
import com.itextpdf.kernel.geom.PageSize
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.layout.element.Table
import com.itextpdf.layout.property.TextAlignment
import com.itextpdf.layout.property.UnitValue
import com.itextpdf.layout.Document





import java.io.FileOutputStream
import java.util.*
import java.util.jar.Manifest
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.layout.element.Cell
import com.itextpdf.layout.element.Paragraph

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
        val pdf=findViewById(R.id.pdf) as Button



        val productsByUser = Featchers().fetchProductsByUser(user_id)
        productsByUser.observe(this, Observer {
            Log.d("ENS","${it}")

            recyclerView.adapter = ProductByUserAdapter(it)})
        recyclerView= findViewById(R.id.ProByUserRecActivity)
        recyclerView.layoutManager= GridLayoutManager(this,3)

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

    private inner class ProductsByUserHolder(view: View) : RecyclerView.ViewHolder(view) {
        val  title = view.findViewById(R.id.titleProduct) as TextView
        val date = view.findViewById(R.id.date) as TextView
        val type = view.findViewById(R.id.type) as TextView




        fun bind(products: Products) {

            title.text = products.title
            type.text = products.type.toString()
            date.text = products.date.toString()

        }

    }





    fun savePdf(){
        val mFileName= SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(System.currentTimeMillis())
        val mFilepath=    Environment.getExternalStorageDirectory().toString() + "/" + mFileName +".pdf"


        val pdfDocument = PdfDocument(PdfWriter(mFilepath))

        val document = Document(pdfDocument, PageSize.A4)

        document.getPageEffectiveArea(PageSize.A4)

        val table = Table(UnitValue.createPercentArray(floatArrayOf(8f, 23f, 15f))).useAllAvailableWidth()

//Add Header Cells
        table.addHeaderCell(Cell().add(Paragraph("Product").setTextAlignment(TextAlignment.CENTER)))
        table.addHeaderCell(Cell().add(Paragraph("date").setTextAlignment(TextAlignment.CENTER)))
        table.addHeaderCell(Cell().add(Paragraph("type").setTextAlignment(TextAlignment.CENTER)))

        val productsByUser = Featchers().fetchProductsByUser(user_id)
        productsByUser.observe(this, Observer{
            for (entry in  it) {
                var title:String=entry.title
                table.addCell(Cell().add(Paragraph(title).setTextAlignment(TextAlignment.CENTER)))
                table.addCell(Cell().add(Paragraph(entry.date.toString()).setTextAlignment(TextAlignment.CENTER)))
                table.addCell(Cell().add(Paragraph(entry.type).setTextAlignment(TextAlignment.RIGHT)))
//
//           val textWithoutSpace1 = Paragraph("the title is $title")
//       textWithoutSpace1.setMargins(10f, 10f, 10f, 10f)
//       document.add(textWithoutSpace1)
//             var price:String=entry.price_d.toString()
//              val textWithoutSpace2 = Paragraph("the price is$price")
//               textWithoutSpace2.setMargins(10f, 10f, 10f, 10f)
//              document.add(textWithoutSpace2)
//             var type:String=entry.type.toString()
//               val textWithoutSpace3 = Paragraph("the type is$type")
//               textWithoutSpace3.setMargins(10f, 10f, 10f, 10f)
//              document.add(textWithoutSpace3)

            }})

        document.add(table)
        Toast.makeText(this," $mFileName +\".pdf\\nis saved to\\n\"+ $mFilepath",Toast.LENGTH_LONG).show()
        document.close()
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
