package com.amroz.ystore.Fragments


import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.amroz.ystore.MainActivity
import com.amroz.ystore.Models.Users
import com.amroz.ystore.R
import com.amroz.ystore.YstoreViewModels
import kotlinx.android.synthetic.main.update_profile_user.*


class UpdateProfile : AppCompatActivity() {
    lateinit var nameUser:EditText
    lateinit var ed_email:EditText
    lateinit var phone:EditText
    lateinit var ed_address:EditText
    lateinit var btnUpdate:TextView
    lateinit var logout:LinearLayout
    lateinit var userProfile:YstoreViewModels
    lateinit var update:LinearLayout
    lateinit var pass:LinearLayout
    lateinit var cardpass:CardView

   // private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"


 //  var uemail=email.text.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.update_profile_user)
        var users=intent.getSerializableExtra("data") as Users


         ed_email=findViewById(R.id.ed_email)
        ed_email.setText(users.email)
       // phone=findViewById(R.id.ed_phone)
        nameUser =findViewById(R.id.ed_name)
         ed_address=findViewById(R.id.ed_address)
       logout=findViewById(R.id.logout)
       update=findViewById(R.id.linear_update)
        pass=findViewById(R.id.pass)
        
///////////////////////////////////////////////////

        val currentpass: EditText = findViewById(R.id.cpass)
        val newpass: EditText = findViewById(R.id.npass)
        val confirmpass: EditText = findViewById(R.id.rpass)
        val buttRest: Button = findViewById(R.id.reset)
        userProfile= ViewModelProviders.of(this).get(YstoreViewModels::class.java)

        cardpass.visibility=View.GONE

        /////////////////////////////////////////////////////////

        var uname=nameUser.text.toString()
        var uphone=77507987492358
        var uaddress=ed_address.getText().toString()
        var email=ed_email.getText().toString()

userProfile=
    ViewModelProviders.of(this).get(YstoreViewModels::class.java)

        nameUser.setText(users.name)
     //   phone.setText(users.phone)
        ed_address.setText(users.address)



        update.setOnClickListener {
//            if (uname.isNotEmpty() && uphone.isNotEmpty() && uaddress.isNotEmpty()) {
            if(nameUser.text.toString().trim().length<3){
                Toast.makeText(this,"Name is not enaph ",Toast.LENGTH_SHORT).show()
                nameUser.setBackgroundResource(R.drawable.erorrshape)
            }else if(ed_address.text.toString().trim().length<3) {
                Toast.makeText(this, "Phone is wrong  ", Toast.LENGTH_SHORT).show()
                ed_address.setBackgroundResource(R.drawable.erorrshape)
            } else if(ed_email.text.toString().trim().length<3){
                Toast.makeText(this,"email unvlalide ",Toast.LENGTH_SHORT).show()
                nameUser.setBackgroundResource(R.drawable.erorrshape)
            }else{

                val response=
                    userProfile.updateProfile(users.user_id,nameUser.text.toString(),ed_email.text.toString(),uphone.toString(),ed_address.text.toString())
                    var intent=Intent(this,MainActivity::class.java)
                             startActivity(intent)
                             finish()
                response.observe(
                    this,
                    Observer { message ->
                        Toast.makeText(this, "updated", Toast.LENGTH_SHORT).show()
                    })
            }
//            }else if(!email.text.trim().matches(emailPattern.toRegex())){
//                email.setBackgroundResource(R.drawable.erorrshape)
//                Toast.makeText(this,"Invlide email adriss",Toast.LENGTH_SHORT).show()}
             //   updateProfile(user)
               // var id=users.user_id


//            }else{
//                Toast.makeText(this,"That is an empty filed",Toast.LENGTH_SHORT).show()}
}

        logout.setOnClickListener{
            onBackPressed()
        }


        //ResetPassword

        pass.setOnClickListener{
            cardpass.visibility=View.VISIBLE
        }

        reset.setOnClickListener {

                if (cpass.text.toString() == users.password.toString()) {
                    if (npass.text.toString().trim().length > 5) {
                        if (npass.text.toString() == rpass.text.toString()) {
                            userProfile.ResetPassword(users.user_id, rpass.text.toString())
                            Toast.makeText(this, "Password successfully Rest  ", Toast.LENGTH_LONG)
                                .show();

                        } else {

                            Toast.makeText(this, "New Password is not the same ", Toast.LENGTH_LONG)
                                .show();
                        }
                    }
                    else { Toast.makeText(this, "password is not enaph ", Toast.LENGTH_SHORT).show()}
                }

            else {
                    Toast.makeText(
                        this,
                        "Password is not  successfully Rest ,, please try again ",
                        Toast.LENGTH_LONG
                    ).show();
                }
            }

        }
    }
