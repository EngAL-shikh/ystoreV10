package com.amroz.ystore

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_add_product.*
import kotlinx.android.synthetic.main.activity_add_user.*
import java.io.ByteArrayOutputStream
class AddProductActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener  {
    lateinit var  image:String
    lateinit  var lastBitmap:Bitmap ;
    lateinit var selectCategorySv: Spinner
    lateinit var categoriesName: MutableList<String>
    lateinit var categoriesList: MutableList<Category>
    var selectedCategoryId = 0
    var a=0
    var IMAGE_REQUST=1

    lateinit var smallimage:ImageView
    lateinit var smallimage2:ImageView
    lateinit var smallimage3:ImageView
    lateinit var smallimage4:ImageView
    lateinit var smallimage5:ImageView

var  images=ArrayList<String>()
    private var context: Context? = null
    var PICK_IMAGE_MULTIPLE = 1
    lateinit var imagePath: String
    var imagesPathList: MutableList<String> = arrayListOf()
    private val ystoreViewModels: YstoreViewModels by lazy {
        ViewModelProviders.of(this).get(YstoreViewModels::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)
        categoriesList = emptyList<Category>().toMutableList()
        categoriesName = emptyList<String>().toMutableList()
        selectCategorySv=findViewById(R.id.category_spinner)
        loadCategories()
        val productTitle: EditText =findViewById(R.id.title)
        val productDetails:EditText=findViewById(R.id.details)
        val productColor:EditText=findViewById(R.id.color)
        val productFeature:EditText=findViewById(R.id.Feature)
        val productPriceY:EditText=findViewById(R.id.PriceY)
        val productPriceD:EditText=findViewById(R.id.PriceD)
        val getimage:LinearLayout=findViewById(R.id.gitimage)
         smallimage=findViewById(R.id.smallimage)
         smallimage2=findViewById(R.id.smallimage2)
         smallimage3=findViewById(R.id.smallimage3)
         smallimage4=findViewById(R.id.smallimage4)
         smallimage5=findViewById(R.id.smallimage5)

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
           // var allimages=images[0]+","+images[1]+","+images[2]+","+images[3]+","+images[4]
            ystoreViewModels.addProduct(productTitle.text.toString(),
                productDetails.text.toString(),
                images[0]+","+images[1]+","+images[2]+","+images[3],productColor.text.toString(),
                productFeature.text.toString(),
               0
                ,productPriceY.text.toString().toInt(),
                productPriceD.text.toString().toInt(),
                3,
                 selectedCategoryId ,
                2,
                "",
                "")

            var intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()

        }

    }

  /* fun onCat( view: View){


       //     val array = arrayOf(
       //         "Elctrnic", "Clothes", "Ecssorics", "Cars"
            )
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder.setTitle("Country")
            builder.setSingleChoiceItems(array, -1,
                DialogInterface.OnClickListener { dialogInterface, i ->
                    (view as EditText).setText(array[i])
                    dialogInterface.dismiss()
                })
            builder.show()


    }*/

    private fun loadCategories() {
        ystoreViewModels.liveDataCategory.observe(
            this,
            Observer {

                for (item in it) {
                    categoriesName.add(item.cat_title)
                    categoriesList.add(item)
                }
                //spinner adapter
                val dataAdapter = ArrayAdapter(
                    this,
                    R.layout.support_simple_spinner_dropdown_item,
                    categoriesName
                )

                dataAdapter.setDropDownViewResource(
                    android.R.layout.simple_spinner_dropdown_item
                )
                selectCategorySv.adapter = dataAdapter
            }
        )
    }

    //--------------------------------------start get image-----------------------------------------------------------------

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//
//            if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
//                var filePath = data.getData();
//                try {
//                    var   bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath)
//                    lastBitmap = bitmap;
//                    smallimage.setImageBitmap(bitmap)
//                    a=1
//                    // choseImage.setImageBitmap(lastBitmap)
//                    image = getStringImage(lastBitmap);
//
//                    Log.d("image",image)
//                } catch (e: IOException) {
//                    e.printStackTrace();
//                }
//            }
//
//            super.onActivityResult(requestCode, resultCode, data)
//
//
//
//
//
//    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && requestCode == 1){

            // if multiple images are selected
            if (data?.getClipData() != null) {
                var count = data.clipData!!.itemCount

                for (i in 0..count - 1) {
                    var imageUri: Uri = data.clipData!!.getItemAt(i).uri

                    var   bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri)

                    image = getStringImage(bitmap);
                    images.add(image)



                }
                smallimage.setImageBitmap(MediaStore.Images.Media.getBitmap(getContentResolver(), data.clipData!!.getItemAt(0).uri))
                smallimage2.setImageBitmap(MediaStore.Images.Media.getBitmap(getContentResolver(), data.clipData!!.getItemAt(1).uri))
                smallimage3.setImageBitmap(MediaStore.Images.Media.getBitmap(getContentResolver(), data.clipData!!.getItemAt(2).uri))
                smallimage4.setImageBitmap(MediaStore.Images.Media.getBitmap(getContentResolver(), data.clipData!!.getItemAt(3).uri))
                smallimage5.setImageBitmap(MediaStore.Images.Media.getBitmap(getContentResolver(), data.clipData!!.getItemAt(4).uri))
//                Log.d("imageUri 1 ::::",images[0].toString())
//                Log.d("imageUri 2 ::::",images[1].toString())
//                Log.d("imageUri 3 ::::",images[2].toString())

            } else if (data?.getData() != null) {
                // if single image is selected

                var imageUri: Uri = data.data!!
                //   iv_image.setImageURI(imageUri) Here you can assign the picked image uri to your imageview

            }
        }
    }


    private fun showFileChooser() {
//        val pickImageIntent = Intent(Intent.ACTION_PICK,
//            MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//        pickImageIntent.type = "image/*"
//        pickImageIntent.putExtra("aspectX", 1)
//        pickImageIntent.putExtra("aspectY", 1)
//        pickImageIntent.putExtra("scale", true)
//        pickImageIntent.putExtra("outputFormat",
//            Bitmap.CompressFormat.JPEG.toString())
//        startActivityForResult(pickImageIntent,IMAGE_REQUST)

        if (Build.VERSION.SDK_INT < 19) {
            var intent = Intent()
            intent.type = "image/*"
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(
                Intent.createChooser(intent, "Select Picture")
                , PICK_IMAGE_MULTIPLE
            )
        } else {
            var intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.type = "image/*"
            startActivityForResult(intent, PICK_IMAGE_MULTIPLE);
        }
    }



    fun getStringImage(bmp: Bitmap): String {
        val baos = ByteArrayOutputStream()
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val imageBytes = baos.toByteArray()
        return Base64.encodeToString(imageBytes, Base64.DEFAULT)

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        selectCategorySv.prompt =
            getString(R.string.spiner_nothing_selected_message)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val item = parent?.get(position).toString()
        selectCategorySv.prompt = item
        selectedCategoryId = categoriesList[position].cat_id!!
    }

    //------------------------------------------------------------end get images--------------------------------------
}