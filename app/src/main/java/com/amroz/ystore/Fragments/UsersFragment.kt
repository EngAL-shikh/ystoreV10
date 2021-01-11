package com.amroz.ystore.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amroz.ystore.Featchers
import com.amroz.ystore.Models.Users
import com.amroz.ystore.R
import com.amroz.ystore.YstoreViewModels


class UsersFragment : Fragment() {
    private lateinit var userViewModel: ViewModel
    private lateinit var userRecyclerView: RecyclerView
    var type=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userViewModel=ViewModelProviders.of(this).get(YstoreViewModels::class.java)
        type=arguments?.getSerializable("type") as String

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (type == "Users"){

            var user= Featchers()
            var liveData= user.fetchUsers()
            liveData.observe(this, Observer {
                Log.d("test", "Response received: ${it}")
                userRecyclerView.adapter= UsersAdapter(it)
            })
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_users, container, false)
        userRecyclerView=view.findViewById(R.id.users_recycler_view)
        userRecyclerView.layoutManager = LinearLayoutManager(context)
        return view
    }

    companion object {
        fun newInstance(data: String): UsersFragment {
            val args = Bundle().apply {
                putSerializable("type", data)
            }
            return UsersFragment().apply {
                arguments = args
            }
        }
    }

    private inner class UsersHolder(view: View) : RecyclerView.ViewHolder(view){

        val user_id= view.findViewById(R.id.user_id) as TextView
        val name= view.findViewById(R.id.name) as TextView
        val email=view.findViewById(R.id.email) as TextView
        val phone=view.findViewById(R.id.phone) as TextView
        val address= view.findViewById(R.id.address) as TextView
        val user_report= view.findViewById(R.id.user_report) as TextView

        fun bind(user:Users){
            user_id.text= user.user_id.toString()
            name.text= user.name
            email.text= user.email
            phone.text= user.phone
            address.text= user.address
            user_report.text= user.user_report.toString()
        }
    }

    private inner class UsersAdapter(var user : List<Users>) : RecyclerView.Adapter<UsersHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.users_list, parent, false)

            return UsersHolder(view)

        }

        override fun getItemCount(): Int {
            return user.size
        }



        override fun onBindViewHolder(holder: UsersHolder, position: Int) {
            val user= user[position]
            holder.bind(user)
        }
    }
}