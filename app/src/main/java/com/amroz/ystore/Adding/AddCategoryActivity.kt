package com.amroz.ystore

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_add_category.*
import java.io.ByteArrayOutputStream
import java.io.IOException

class AddCategoryActivity : AppCompatActivity() {
    //---- for image------//
    lateinit var image: String
    lateinit var lastBitmap: Bitmap
    var a = 0
    var IMAGE_REQUST = 1

    //--------------------//
    private val ystoreViewModels: YstoreViewModels by lazy {
        ViewModelProviders.of(this).get(YstoreViewModels::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_category)
        val catTitle: EditText = findViewById(R.id.cattitle)
        val getimage: LinearLayout = findViewById(R.id.getimage)
        val add_cat: ImageButton = findViewById(R.id.add_cat)
        val smallimage: ImageView = findViewById(R.id.smalimage)
        val bt_close: ImageButton = findViewById(R.id.bt_close)







        getimage.setOnClickListener {

            showFileChooser()
        }
        add_cat.setOnClickListener {

            if (catTitle != null)
                ystoreViewModels.addCategory(catTitle.text.toString(), image)
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        bt_close.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


    //--------------------------------------start get image-----------------------------------------------------------------

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.data != null) {
            var filePath = data.data
            try {
                var bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filePath)
                lastBitmap = bitmap
                a = 1
                smalimage.setImageBitmap(bitmap)

                // choseImage.setImageBitmap(lastBitmap)
                image = getStringImage(lastBitmap)
                Log.d("image", image)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        super.onActivityResult(requestCode, resultCode, data)


    }


    private fun showFileChooser() {
        val pickImageIntent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        pickImageIntent.type = "image/*"
        pickImageIntent.putExtra("aspectX", 1)
        pickImageIntent.putExtra("aspectY", 1)
        pickImageIntent.putExtra("scale", true)
        pickImageIntent.putExtra(
            "outputFormat",
            Bitmap.CompressFormat.JPEG.toString()
        )
        startActivityForResult(pickImageIntent, IMAGE_REQUST)
    }


    fun getStringImage(bmp: Bitmap): String {
        val baos = ByteArrayOutputStream()
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val imageBytes = baos.toByteArray()
        return Base64.encodeToString(imageBytes, Base64.DEFAULT)

    }


    //------------------------------------------------------------end get images--------------------------------------

}