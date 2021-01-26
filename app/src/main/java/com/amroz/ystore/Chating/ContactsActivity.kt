package com.amroz.ystore.Chating

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.*
import com.amroz.ystore.Models.UserChat
import com.amroz.ystore.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main_chat.*
import kotlinx.android.synthetic.main.contact_list.*

class ContactsActivity : AppCompatActivity() {
    lateinit var list_viw:ListView
   // lateinit var text:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_chat)
        list_viw=findViewById(R.id.list_viw)
       // supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        title = "Contacts"

        val firebaseUser = FirebaseAuth.getInstance().currentUser
        if (firebaseUser != null) {
            val fromUid = firebaseUser.uid
            val rootRef = FirebaseFirestore.getInstance()
            val uidRef = rootRef.collection("users").document(fromUid)
            uidRef.get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val document = task.result
                    if (document!!.exists()) {
                        val fromUser = document?.toObject(UserChat::class.java)
                        Toast.makeText(this,fromUser.toString(),Toast.LENGTH_LONG).show()
                      //  text.text=fromUser.toString()+"||"+fromUid.toString()


                        val userContactsRef = rootRef.collection("contacts")
                            .document(fromUid).collection("userContacts")
                        userContactsRef.get().addOnCompleteListener{ t ->
                            if (t.isSuccessful) {
                                val listOfToUserNames = ArrayList<String>()
                                val listOfToUsers = ArrayList<UserChat>()
                                val listOfRooms = ArrayList<String>()
                                for (d in t.result!!) {
                                    val toUser = d.toObject(UserChat::class.java)
                                    listOfToUserNames.add(toUser.userName)
                                    listOfToUsers.add(toUser)
                                  //  listOfRooms.add(d.id)
                                    Toast.makeText(this,toUser.userName,Toast.LENGTH_LONG).show()
                                }



                                val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listOfToUserNames)
                                list_viw.adapter = arrayAdapter
                                list_viw.onItemClickListener = AdapterView.OnItemClickListener{ _, _, position, _ ->
                                    val intent = Intent(this, ChatActivity::class.java)
                                    intent.putExtra("fromUser", fromUser)
                                    intent.putExtra("toUser", listOfToUsers[position])
                                    intent.putExtra("roomId", "noRoomId")
                                    startActivity(intent)
                                    finish()
                                }
                            }else{
                                Toast.makeText(this,"no data",Toast.LENGTH_LONG).show()

                            }
                        }
                    }else{
                        Toast.makeText(this,"error",Toast.LENGTH_LONG).show()
                    }
                }
            }
        }else{
            Toast.makeText(this,"no users",Toast.LENGTH_LONG).show()
        }
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            else -> super.onOptionsItemSelected(menuItem)
        }
    }

}