package com.amroz.ystore.Adding

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.amroz.ystore.*
import com.amroz.ystore.Chating.MainChatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_add_category.*
import kotlinx.android.synthetic.main.activity_add_product.*
import kotlinx.android.synthetic.main.activity_add_user.*
import kotlinx.android.synthetic.main.activity_login.*
import java.io.ByteArrayOutputStream
import java.io.IOException


class AddUser : AppCompatActivity() {
    //---- for image------//
    lateinit var  image:String
    lateinit  var lastBitmap:Bitmap ;
    var a=0
    var IMAGE_REQUST=1
    //--------------------//

    private lateinit var addName: EditText
    private lateinit var addEmail:TextView
    private lateinit var smallimage:ImageView
    private lateinit var addPassword:EditText
  //  private lateinit var addChatID:TextView
    private lateinit var addPhone:EditText
    private lateinit var addAddress:EditText
    private lateinit var passwordConf:EditText
    private lateinit var singUp:ImageButton
    private lateinit var bt_close:ImageButton
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)


        val getimage:LinearLayout=findViewById(R.id.gitimage)
        smallimage =findViewById(R.id.smalimage)
        singUp=findViewById(R.id.signUpBtn)
        bt_close=findViewById(R.id.bt_close)
        addName=findViewById(R.id.name)
        addEmail=findViewById(R.id.Email)
        addPassword=findViewById(R.id.password_sg)
       // addChatID=findViewById(R.id.chat_id)
        addPhone=findViewById(R.id.phone)
        addAddress=findViewById(R.id.address)
        passwordConf=findViewById(R.id.passwordConf)
        addEmail.text= QueryPreferences.getStoredQueryEmail(this)
//        var progress:ProgressBar=findViewById(R.id.progress)
        //addChatID.text= SharedPref.getUid(this)

        singUp.setOnClickListener {
            if (addName.text.isEmpty() ||
                addEmail.text.isEmpty() ||
                addPassword.text.isEmpty()||
                passwordConf.text.isEmpty()||
                addPhone.text.isEmpty()||
                addAddress.text.isEmpty()) {
                Toast.makeText(this, "It cannot be empty  ", Toast.LENGTH_SHORT).show()
            } else{
                if(addName.text.toString().trim().length<3){
                    Toast.makeText(this,"Name is Empty ",Toast.LENGTH_SHORT).show()
                    addName.setBackgroundResource(R.drawable.erorrshape)
                }else if(addPassword.text.toString().trim().length<6){
                    Toast.makeText(this,"Low password  ",Toast.LENGTH_SHORT).show()
                    addPassword.setBackgroundResource(R.drawable.erorrshape)
                }else if (addPassword.text.toString()!=passwordConf.text.toString()){
                    Toast.makeText(this,"The password confirm is not the same ",Toast.LENGTH_SHORT).show()
                    addPassword.setBackgroundResource(R.drawable.erorrshape)
                    passwordConf.setBackgroundResource(R.drawable.erorrshape)
                }else if(!addEmail.text.trim().matches(emailPattern.toRegex())) {
                    addEmail.setBackgroundResource(R.drawable.erorrshape)
                    Toast.makeText(this, "Invalide email address", Toast.LENGTH_SHORT).show()
                }
                else if(addPhone.text.toString().trim().length<9) {
                    Toast.makeText(this, " phone Number is not correct ", Toast.LENGTH_SHORT).show()
                    addName.setBackgroundResource(R.drawable.erorrshape)

                }else {
                    var user = YstoreViewModels()

                    var LiveData = user.addUsers(
                        addName.text.toString(),
                        QueryPreferences.getStoredQueryEmail(this).toString(),
                        addPassword.text.toString(),
                        QueryPreferences.getStoredQueryChatid(this).toString(),
                        addPhone.text.toString(),
                        addAddress.text.toString(),
                        image
                    )

                    //  getuserid()

                    LiveData.observe(this, Observer {

                        QueryPreferences.setStoredQueryUserid(this, it[0].user_id.toString())
                        QueryPreferences.setStoredQuery(this, it[0].rule)
                        QueryPreferences.setStoredQueryUserimage(this, it[0].user_image)
                        QueryPreferences.setStoredQueryUsername(this, it[0].name)
                        QueryPreferences.setStoredQueryUseraddress(this, it[0].address)
                        QueryPreferences.setStoredQueryChatid(this, it[0].chat_id)
                        val intent = Intent(this, MainActivity::class.java)
                        // intent.putExtra("userrule",)
                        startActivity(intent)
                    })


                    // cheackadmin()
                    // Thread.sleep(4000)
                }}


        }

        getimage.setOnClickListener {

            showFileChooser()
        }
        bt_close.setOnClickListener {
            var auth = FirebaseAuth.getInstance()
            auth?.signOut()
            var i =Intent(this, LoginActivity::class.java)
            startActivity(i)
            finish()
        }

    }


    //--------------------------------------start get image-----------------------------------------------------------------

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            var filePath = data.getData();
            try {
                var   bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath)
                lastBitmap = bitmap;
                a=1
                smallimage.setImageBitmap(bitmap)

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


    fun getuserid(){
        var usersid = Featchers()
        val newsLiveData=usersid.fetchUsersInfoBYemail(QueryPreferences.getStoredQueryEmail(this).toString())
        newsLiveData.observe(this,
            Observer {


           // Log.d("useridtestin", "Response received: ${it[0].user_id}")
               // Log.d("useridtestin", "Response received: ${it[0].user_id}")
                // SharedPref.setid(this@LoginActivity,it[0].user_id.toString())
//                var shaerd=getSharedPreferences("userid",0)
//                var edit=shaerd.edit()
//                edit.putString("id",it[0].user_id.toString())
//                edit.commit()

              //  Thread.sleep(4000)
              //  QueryPreferences.setStoredQuery(this, it[0].rule)
//               QueryPreferences.setStoredQueryUserid(this, it[0].user_id.toString())
//                QueryPreferences.setStoredQuery(this, it[0].rule)
//                QueryPreferences.setStoredQueryUserimage(this, it[0].user_image)
//                QueryPreferences.setStoredQueryUsername(this, it[0].name)
//                QueryPreferences.setStoredQueryUseraddress(this, it[0].address)
//                QueryPreferences.setStoredQueryChatid(this, it[0].chat_id)



        })
    }


}