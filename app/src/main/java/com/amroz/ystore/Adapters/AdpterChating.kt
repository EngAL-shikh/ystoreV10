package com.amroz.ystore

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amroz.ystore.Models.Chat
import com.amroz.ystore.Models.Message
import com.amroz.ystore.R
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions


class AdpterChating(options: FirestoreRecyclerOptions<Message>?) :
    FirestoreRecyclerAdapter<Message, AdpterChating.AdpterNewsVH>(options as FirestoreRecyclerOptions<Message>){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): AdpterNewsVH {


        return  AdpterNewsVH(LayoutInflater.from(p0.context).inflate(R.layout.chat_list,p0,false))
    }

    override fun onBindViewHolder(holder: AdpterNewsVH, p1: Int, chat: Message) {



            holder?.message?.text=chat?.messageText


//        holder?.det?.text=news?.det
//        holder?.image?.text=news?.image
//        Log.d("title",news?.title.toString())
    }
    class AdpterNewsVH(itemView: View) : RecyclerView.ViewHolder(itemView) {


        var message= itemView.findViewById(R.id.message) as TextView
//        var det= itemView.findViewById(R.id.det) as TextView
//        var image= itemView.findViewById(R.id.image) as TextView
    }





}