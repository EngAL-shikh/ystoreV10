package com.amroz.ystore

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import com.amroz.ystore.Models.Users
import com.scottyab.showhidepasswordedittext.ShowHidePasswordEditText
import android.widget.Toast.makeText
import androidx.appcompat.app.AppCompatActivity
import com.amroz.ystore.Chating.ChatActivity
import com.amroz.ystore.Chating.ContactsActivity
import com.amroz.ystore.Chating.MainChatActivity
import com.amroz.ystore.Models.UserChat
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import render.animations.Bounce
import render.animations.Render
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private var firebaseAuth: FirebaseAuth? = null
    private var authStateListener: FirebaseAuth.AuthStateListener? = null
    private var googleApiClient: GoogleApiClient? = null
    private var rootRef: FirebaseFirestore? = null
    val render = Render(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var loginbyphone:ImageView=findViewById(R.id.login_by_phone)
        var login:ImageView=findViewById(R.id.login)
        var username:EditText=findViewById(R.id.username)
        var password: ShowHidePasswordEditText = findViewById(R.id.password)
        var loginbyemail:ImageView=findViewById(R.id.login_by_email)
        var linearLoginbyemail:LinearLayout=findViewById(R.id.linear_login_by_email)
        var linearLoginbyphone:LinearLayout=findViewById(R.id.linear_login_by_phone)

// Create Render Class


// Set Animation


//        login.setOnClickListener {
//
////            var intent = Intent(this,MainActivity::class.java)
////            intent.putExtra("admin",username.text.toString())
////            startActivity(intent)
//
//
//        }


        loginbyphone.setOnClickListener {
            linearLoginbyphone.visibility=View.VISIBLE
            linearLoginbyemail.visibility=View.GONE
            loginbyphone.visibility=View.GONE
            loginbyemail.visibility=View.VISIBLE
            render.setAnimation(Bounce().InDown(loginbyemail))
            render.start()
            YoYo.with(Techniques.BounceIn)
                .duration(2000)
                .playOn(findViewById(R.id.linear_login_by_phone))
        }

        loginbyemail.setOnClickListener {

            linearLoginbyphone.visibility=View.GONE
            linearLoginbyemail.visibility=View.VISIBLE
            loginbyphone.visibility=View.VISIBLE
            loginbyemail.visibility=View.GONE
            YoYo.with(Techniques.BounceIn)
                .duration(2000)
                .playOn(findViewById(R.id.linear_login_by_email))
        }




        ////////////////////////////////////////////////////////////////////

        rootRef = FirebaseFirestore.getInstance()

        login.setOnClickListener { signIn() }

        firebaseAuth = FirebaseAuth.getInstance()
        authStateListener = FirebaseAuth.AuthStateListener { auth ->
            val firebaseUser = auth.currentUser
            if (firebaseUser != null) {
                val intent = Intent(this, MainChatActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleApiClient = GoogleApiClient.Builder(applicationContext)
            .enableAutoManage(this) { makeText(this, "You got a GoogleApiClient Error!",
                Toast.LENGTH_SHORT
            ).show() }
            .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
            .build()




    }


    private fun signIn() {
        val signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient)
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val googleSignInAccount = task.getResult(ApiException::class.java)
                firebaseSignInWithGoogle(googleSignInAccount!!)
            } catch (e: ApiException) {
                makeText(this, "Google sign in failed!", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun firebaseSignInWithGoogle(googleSignInAccount: GoogleSignInAccount) {
        val authCredential = GoogleAuthProvider.getCredential(googleSignInAccount.idToken, null)
        firebaseAuth!!.signInWithCredential(authCredential).addOnCompleteListener(this) {
            val firebaseUser = firebaseAuth!!.currentUser
            if (firebaseUser != null) {
                createUserIfNotExists(firebaseUser)
            }
        }
    }

    private fun createUserIfNotExists(firebaseUser: FirebaseUser) {
        val uid = firebaseUser.uid
        val userName = firebaseUser.displayName
        val user = UserChat(uid, userName!!)

        val uidRef = rootRef!!.collection("users").document(uid)
        uidRef.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val document = task.result
                if (!document!!.exists()) {
                    uidRef.set(user)
                }
            }
        }
    }

    public override fun onStart() {
        super.onStart()
        firebaseAuth!!.addAuthStateListener(authStateListener!!)
    }

    public override fun onStop() {
        super.onStop()
        if (firebaseAuth != null) {
            firebaseAuth!!.removeAuthStateListener(authStateListener!!)
        }
    }

    companion object {
        //private val TAG = "LoginActivity"
        private const val RC_SIGN_IN = 123
    }
}