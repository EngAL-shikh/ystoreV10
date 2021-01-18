package com.amroz.ystore

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_add_category.*
import kotlinx.android.synthetic.main.activity_add_product.*
import java.io.ByteArrayOutputStream
import java.io.IOException


class AddProductActivity : AppCompatActivity() {
    lateinit var  image:String
    lateinit  var lastBitmap:Bitmap ;
    var a=0
    var IMAGE_REQUST=1

    private val ystoreViewModels: YstoreViewModels by lazy {
        ViewModelProviders.of(this).get(YstoreViewModels::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)
        val productTitle: EditText =findViewById(R.id.title)
        val productDetails:EditText=findViewById(R.id.details)
        val productColor:EditText=findViewById(R.id.color)
        val productFeature:EditText=findViewById(R.id.Feature)
        val productPriceY:EditText=findViewById(R.id.PriceY)
        val productPriceD:EditText=findViewById(R.id.PriceD)
        val getimage:LinearLayout=findViewById(R.id.gitimage)
        val smallimage:ImageView=findViewById(R.id.smallimage)
        val bt_close:ImageButton=findViewById(R.id.bt_close)
     //   val cat:EditText=findViewById(R.id.PriceD)
       val add:ImageView=findViewById(R.id.add)


        getimage.setOnClickListener {
            showFileChooser()
        }

        bt_close.setOnClickListener {
            bt_close.setOnClickListener {
                var intent= Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }


        add.setOnClickListener {
            ystoreViewModels.addProduct(productTitle.text.toString(),
                productDetails.text.toString(),
                image,productColor.text.toString(),
                productFeature.text.toString(),
               0
                ,productPriceY.text.toString().toInt(),
                productPriceD.text.toString().toInt(),
                3,
               2,
                2,
                "",
                "")

            var intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()

        }

    }

    fun onCat( view: View){


            val array = arrayOf(
                "Elctrnic", "Clothes", "Ecssorics", "Cars"
            )




            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder.setTitle("Country")
            builder.setSingleChoiceItems(array, -1,
                DialogInterface.OnClickListener { dialogInterface, i ->
                    (view as EditText).setText(array[i])
                    dialogInterface.dismiss()
                })
            builder.show()


    }



    //--------------------------------------start get image-----------------------------------------------------------------

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


            if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
                var filePath = data.getData();
                try {
                    var   bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath)
                    lastBitmap = bitmap;
                    smallimage.setImageBitmap(bitmap)
                    a=1
                    // choseImage.setImageBitmap(lastBitmap)
                    image = getStringImage(lastBitmap);

                    Log.d("image",image)
                } catch (e: IOException) {
                    e.printStackTrace();
                }
            }

            super.onActivityResult(requestCode, resultCode, data)





    }


    private fun showFileChooser() {
        val pickImageIntent = Intent(Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        pickImageIntent.type = "image/*"
        pickImageIntent.putExtra("aspectX", 1)
        pickImageIntent.putExtra("aspectY", 1)
        pickImageIntent.putExtra("scale", true)
        pickImageIntent.putExtra("outputFormat",
            Bitmap.CompressFormat.JPEG.toString())
        startActivityForResult(pickImageIntent,IMAGE_REQUST)
    }



    fun getStringImage(bmp: Bitmap): String {
        val baos = ByteArrayOutputStream()
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val imageBytes = baos.toByteArray()
        return Base64.encodeToString(imageBytes, Base64.DEFAULT)

    }

    //------------------------------------------------------------end get images--------------------------------------
}