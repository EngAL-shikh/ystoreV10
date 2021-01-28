package com.amroz.ystore.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amroz.ystore.AdpterChating
import com.amroz.ystore.Models.Chat
import com.amroz.ystore.Models.Message
import com.amroz.ystore.R
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query


class ChatFragment : Fragment() {

    private var db: FirebaseFirestore = FirebaseFirestore.getInstance()

    //    val query = rootRef!!.collection("messages").document(roomId).collection("roomMessages").orderBy("sentAt", Query.Direction.ASCENDING)
    private var collectionRefrence =
        db.collection("messages").document("pIeeKxlndpJgphMjyLYB").collection("roomMessages")
            .orderBy("sentAt", Query.Direction.ASCENDING)

    var adpaperChating: AdpterChating? = null
    lateinit var messagetext: EditText
    lateinit var send: ImageView
    lateinit var rec: RecyclerView


    companion object {
        fun newInstance(data: String): ChatFragment {
            val args = Bundle().apply {
                putSerializable("name", data)
            }
            return ChatFragment().apply {
                arguments = args
            }
        }
    }

    var contact: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contact = arguments?.getSerializable("name") as String


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_chat, container, false)
        messagetext = view.findViewById(R.id.messagetext) as EditText
        send = view.findViewById(R.id.btn_send) as ImageView
//        image=view.findViewById(R.id.image)as EditText
//        save=view.findViewById(R.id.save)as Button
        rec = view.findViewById(R.id.recyclerView) as RecyclerView


        send.setOnClickListener {
            addToNews()
            messagetext.setText("")

        }









        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        feachMessage()


    }


    fun addToNews() {


        db = FirebaseFirestore.getInstance()


        var news = Chat(1, messagetext.text.toString(), 3)
        db.collection("Chat")
            .add(news)
            .addOnCompleteListener {

                if (it.isSuccessful) {
                    Toast.makeText(context, "added", Toast.LENGTH_LONG).show()

                } else {
                    Toast.makeText(context, "filde to add ${it.exception}", Toast.LENGTH_LONG)
                        .show()
                    Log.d("test", it.exception.toString())

                }
            }


    }

    fun feachMessage() {

//
        val query: Query = collectionRefrence
        val firestoreRecyclerOptions = FirestoreRecyclerOptions.Builder<Message>()
            .setQuery(query, Message::class.java)
            .build()


        var l = LinearLayoutManager(context)
        adpaperChating = AdpterChating(firestoreRecyclerOptions)
        rec.layoutManager = LinearLayoutManager(context)




        rec.adapter = adpaperChating
        Log.d("adaptermeesage", adpaperChating.toString())


    }


    override fun onStart() {
        super.onStart()
        if (contact == "logout") {

        } else {
            adpaperChating!!.startListening()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (contact == "logout") {

        } else {
            adpaperChating!!.stopListening()
        }
    }


//    fun redFromFireStore(){
//        db= FirebaseFirestore.getInstance()
//        val newsList = mutableListOf<News>();
//        db.collection("News").get().addOnCompleteListener{
//            val newsList = mutableListOf<News>();
//
//
//            if (it.isSuccessful) {
//                val newsList = mutableListOf<News>();
//                for (news in it.result!!) {
//                    Log.w("TAG", "done", it.exception)
//                    newsList.add(News.newsJSON(news.id,news.data))
//                }
//                //updateUI(newsList);
//            } else {
//                Log.w("TAG", "filde", it.exception)
//            }
//        }
//
//    }
}