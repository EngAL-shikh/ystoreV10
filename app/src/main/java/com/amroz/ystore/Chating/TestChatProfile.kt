package com.amroz.ystore.Chating

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.amroz.ystore.Models.UserChat
import com.amroz.ystore.R
import com.google.firebase.firestore.FirebaseFirestore

class TestChatProfile : AppCompatActivity() {
    private var db:FirebaseFirestore= FirebaseFirestore.getInstance()
    //    val query = rootRef!!.collection("messages").document(roomId).collection("roomMessages").orderBy("sentAt", Query.Direction.ASCENDING)
  //  private   var collectionRefrence =db.collection("messages").document("pIeeKxlndpJgphMjyLYB").collection("roomMessages")
     //   .orderBy("sentAt", Query.Direction.ASCENDING)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_chat_profile)
        var addcontact:TextView=findViewById(R.id.addcontact)

        addcontact.setOnClickListener {
            addContacts()
        }
    }



    fun addContacts(){





        db= FirebaseFirestore.getInstance()




        val user = UserChat("123", "userName"!!)
        db.collection("contacts")
            .document("ANdhyn4pyPMdeH8vB7JEbUETpRA3").collection("userContacts")
            .add(user)
            .addOnCompleteListener{

                if (it.isSuccessful){
                    //Toast.makeText(context,"added", Toast.LENGTH_LONG).show()

                }else{
                    Toast.makeText(this,"filde to add ${it.exception}", Toast.LENGTH_LONG).show()
                    Log.d("test",it.exception.toString())

                }
            }


    }
}