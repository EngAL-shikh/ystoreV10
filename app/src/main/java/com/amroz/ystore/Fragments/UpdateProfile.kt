package com.amroz.ystore.Fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.amroz.ystore.Models.Users
import com.amroz.ystore.R
import com.amroz.ystore.YstoreViewModels


class UpdateProfile : AppCompatActivity() {
    lateinit var nameUser:EditText
    //lateinit var email:EditText
    lateinit var phone:EditText
    lateinit var address:EditText
    lateinit var btnUpdate:Button
    lateinit var btnCancel:Button
    lateinit var userProfile:YstoreViewModels
   // private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    lateinit var user:Users

 //  var uemail=email.text.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.update_profile_user)

       //  email=findViewById(R.id.ed_email)
        phone=findViewById(R.id.ed_phone)
        nameUser =findViewById(R.id.name)
         address=findViewById(R.id.ed_address)
        btnUpdate=findViewById(R.id.btn_update)
       btnCancel=findViewById(R.id.btn_cancel)
        var uname=nameUser.text.toString()
        var uphone=phone.text.toString()
        var uaddress=address.text.toString()
userProfile=
    ViewModelProviders.of(this).get(YstoreViewModels::class.java)
user=Users()
        nameUser.setText(user.name)
        phone.setText(user.phone)
        address.setText(user.address)
        btnUpdate.setOnClickListener {
            if (uname.isNotEmpty() && uphone.isNotEmpty() && uaddress.isNotEmpty()) {
            if(nameUser.text.toString().trim().length<3){
                Toast.makeText(this,"Name is not enaph ",Toast.LENGTH_SHORT).show()
                nameUser.setBackgroundResource(R.drawable.erorrshape)
            }else if(phone.text.toString().trim().length<9) {
                Toast.makeText(this, "Phone is wrong  ", Toast.LENGTH_SHORT).show()
                phone.setBackgroundResource(R.drawable.erorrshape)
            } else if(address.text.toString().trim().length<3){
                Toast.makeText(this,"address is not enaph ",Toast.LENGTH_SHORT).show()
                nameUser.setBackgroundResource(R.drawable.erorrshape)
            }
//            }else if(!email.text.trim().matches(emailPattern.toRegex())){
//                email.setBackgroundResource(R.drawable.erorrshape)
//                Toast.makeText(this,"Invlide email adriss",Toast.LENGTH_SHORT).show()}
             //   updateProfile(user)
                var id=user.user_id
                val response=
                    userProfile.updateProfile(id,uname,uphone,uaddress)
                response.observe(
                    this,
                    Observer { message ->
                        Toast.makeText(this, "updated", Toast.LENGTH_SHORT).show()
                    })

            }else{
                Toast.makeText(this,"That is an empty filed",Toast.LENGTH_SHORT).show()}
}
        btnCancel.setOnClickListener {
           // finish()
        }
    }

// fun updateProfile(user: Users){
////    name.setText(user.name)
////    email.setText(user.email)
////     phone.setText(user.phone)
////     address.setText(user.address)
//     var id=user.user_id
//     val response=
//userProfile.updateProfile(id,uname,uphone,uaddress)
//     response.observe(
//         this,
//         Observer { message ->
//             Toast.makeText(this, "updated", Toast.LENGTH_SHORT).show()
//         })
// }
}