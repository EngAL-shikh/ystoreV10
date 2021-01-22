package com.amroz.ystore.Adding

import android.content.ContentResolver
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.amroz.ystore.MainActivity
import com.amroz.ystore.R
import com.amroz.ystore.YstoreViewModels
import kotlinx.android.synthetic.main.activity_add_product.*
import java.io.ByteArrayOutputStream
import java.io.IOException


class AddProductFragment : Fragment() {
    var app=AppCompatActivity()
    lateinit var  image:String
    lateinit  var lastBitmap: Bitmap
    var a=0
    var IMAGE_REQUST=1
    private val ystoreViewModels: YstoreViewModels by lazy {
        ViewModelProviders.of(this).get(YstoreViewModels::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_product, container, false)
        val productTitle: EditText = view.findViewById(R.id.title)
        val productDetails: EditText =view.findViewById(R.id.details)
        val productColor: EditText =view.findViewById(R.id.color)
        val productFeature: EditText =view.findViewById(R.id.Feature)
        val productPriceY: EditText =view.findViewById(R.id.PriceY)
        val productPriceD: EditText =view.findViewById(R.id.PriceD)
        val getimage: LinearLayout =view.findViewById(R.id.gitimage)
        val smallimage: ImageView =view.findViewById(R.id.smallimage)
        val bt_close: ImageButton =view.findViewById(R.id.bt_close)

        val add: ImageView =view.findViewById(R.id.add)

        getimage.setOnClickListener {
            showFileChooser()
        }

        bt_close.setOnClickListener {
            bt_close.setOnClickListener {
                var intent= Intent(context, MainActivity::class.java)
                startActivity(intent)
                app.finish()
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

            var intent= Intent(context, MainActivity::class.java)
            startActivity(intent)
            app.finish()

        }


        return view
    }
    fun onCat( view: View){


        val array = arrayOf(
            "Elctrnic", "Clothes", "Ecssorics", "Cars"
        )




        val builder: AlertDialog.Builder = AlertDialog.Builder(context!!)
        builder.setTitle("Country")
        builder.setSingleChoiceItems(array, -1,
            DialogInterface.OnClickListener { dialogInterface, i ->
                (view as EditText).setText(array[i])
                dialogInterface.dismiss()
            })
        builder.show()


    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        if (requestCode == 1 && resultCode == AppCompatActivity.RESULT_OK && data != null && data.getData() != null) {
            var filePath = data.getData();
            var resolver:ContentResolver?= activity?.contentResolver
            try {
                var   bitmap = MediaStore.Images.Media.getBitmap(resolver, filePath)
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


}