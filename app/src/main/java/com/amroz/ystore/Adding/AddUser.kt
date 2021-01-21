package com.amroz.ystore.Adding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.amroz.ystore.AddFeacher
import com.amroz.ystore.Chating.MainChatActivity
import com.amroz.ystore.R
import com.amroz.ystore.SharedPref


class AddUser : AppCompatActivity() {
    private lateinit var addName: EditText
    private lateinit var addEmail:TextView
    private lateinit var addPassword:EditText
    private lateinit var addChatID:TextView
    private lateinit var addPhone:EditText
    private lateinit var addAddress:EditText
    private lateinit var singUp:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)

        singUp=findViewById(R.id.signUpBtn)
        addName=findViewById(R.id.name)
        addEmail=findViewById(R.id.email)
        addPassword=findViewById(R.id.password)
        addChatID=findViewById(R.id.chat_id)
        addPhone=findViewById(R.id.phone)
        addAddress=findViewById(R.id.address)
        addEmail.text= SharedPref.getEmail(this)
        addChatID.text= SharedPref.getUid(this)

        singUp.setOnClickListener {
            var user= AddFeacher()
            user.addUser(addName.text.toString(),SharedPref.getEmail(this).toString(),addPassword.text.toString(),
                            SharedPref.getUid(this).toString(),addPhone.text.toString(),addAddress.text.toString())

            val intent = Intent(this, MainChatActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}