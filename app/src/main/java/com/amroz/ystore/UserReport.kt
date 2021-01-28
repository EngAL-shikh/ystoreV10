package com.amroz.ystore

import QueryPreferences
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.amroz.ystore.Chating.ContactsActivity
import com.amroz.ystore.Models.Products
import com.amroz.ystore.Models.UserChat
import com.google.firebase.firestore.FirebaseFirestore

class UserReport : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_report)

        lateinit var nameUser: TextView
        lateinit var ed_email: TextView
        lateinit var ed_address: TextView


        var user_report: YstoreViewModels =
            ViewModelProviders.of(this).get(YstoreViewModels::class.java)
        var image: ImageView = findViewById(R.id.image)
        var message: ImageButton = findViewById(R.id.message)
        val report = findViewById<ImageButton>(R.id.bt_report)
        var image_back: ImageView = findViewById(R.id.ba)

        var users = intent.getSerializableExtra("data") as Products
        var ratingUser: ImageButton = findViewById(R.id.user_raiting)
        ed_email = findViewById(R.id.email)

        nameUser = findViewById(R.id.name)

        ed_address = findViewById(R.id.address)
        ed_address.text = users.adress
        ed_email.text = users.email
        nameUser.text = users.user_name
        // Log.d("fatma", products.email)
        // Log.d("fatma",(products.user_name))

        report.setOnClickListener {

            val builder = AlertDialog.Builder(this)

            builder.setMessage("Are you sure ")

            builder.setPositiveButton("Continue") { _, _ ->
                user_report.update_user_report(users.user_id, 0)

                Toast.makeText(
                    applicationContext,
                    "  This user has been reported",
                    Toast.LENGTH_SHORT
                ).show()
            }
            builder.setNegativeButton("Cancel") { _, _ ->

            }
            val dialog = builder.create()
            dialog.show()

        }
        image_back.setOnClickListener {

            onBackPressed()
        }

        ratingUser.setOnClickListener {
            var ratingCount = users.user_raiting + 1
            ManagementFeatchers().updateRatingUser(users.user_id, ratingCount)
            Toast.makeText(this, "you like us$ratingCount", Toast.LENGTH_SHORT).show()
        }



        message.setOnClickListener {
            addContacts(users.chat_id, users.user_name)
            Log.d("hhhhhhhhhhhh", users.chat_id)
            //  Toast.makeText(this,users.chat_id,Toast.LENGTH_LONG).show()
        }
    }

    fun addContacts(chatid: String, username: String) {


        var db: FirebaseFirestore = FirebaseFirestore.getInstance()


        db = FirebaseFirestore.getInstance()


        val user = UserChat(chatid, username)
        db.collection("contacts")
            .document(QueryPreferences.getStoredQueryChatid(this)).collection("userContacts")
            .document(chatid)
            .set(user)
            .addOnCompleteListener {

                if (it.isSuccessful) {
                    Toast.makeText(this, "added", Toast.LENGTH_LONG).show()
                    var intent = Intent(this, ContactsActivity::class.java)
                    startActivity(intent)

                } else {
                    Toast.makeText(this, "filde to add ${it.exception}", Toast.LENGTH_LONG).show()
                    Log.d("test", it.exception.toString())

                }
            }


    }
}