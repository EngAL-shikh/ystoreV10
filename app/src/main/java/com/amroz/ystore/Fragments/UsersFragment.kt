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
    ///////////user_status////////////////
    private val ystoreViewModels: YstoreViewModels by lazy {
        ViewModelProviders.of(this).get(YstoreViewModels::class.java)
    }
    ///////////user_status////////////////
    var type=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userViewModel=ViewModelProviders.of(this).get(YstoreViewModels::class.java)

      //  type=arguments?.getSerializable("type") as String


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


            var user= Featchers()
            var liveData= user.fetchUsers()
            liveData.observe(this, Observer {
                Log.d("test", "Response received: ${it}")
                userRecyclerView.adapter= UsersAdapter(it)
            })

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
        fun newInstance() = UsersFragment()
    }

    private inner class UsersHolder(view: View) : RecyclerView.ViewHolder(view){

        val user_id= view.findViewById(R.id.user_id) as TextView
        val name= view.findViewById(R.id.name) as TextView
//        val email=view.findViewById(R.id.email) as TextView
//        val phone=view.findViewById(R.id.phone) as TextView
//        val address= view.findViewById(R.id.address) as TextView
//        val user_report= view.findViewById(R.id.user_report) as TextView
      val userstatus= view.findViewById(R.id.userstatus) as TextView

        ///////////user_status////////////////
        fun bind(user:Users){

            user_id.text= user.user_id.toString()
            name.text= user.name
            var user_active:Int
            if(user.user_status==0) userstatus.text="Disactive"
            else  userstatus.text="Active"


            userstatus.setOnClickListener {
                if(user.user_status==0)
                {
                    user_active=1
                    userstatus.text="Active"

                }
                else{
                    user_active=0
                    userstatus.text="Disactive"
                }
                ystoreViewModels.userStatus(user.user_id, user_active)
                Log.d("done", user_active.toString())

                //Toast.makeText(this, "User status has changed   ", Toast.LENGTH_SHORT).show()
            }
            //////////////////////user_status///////////////////////////

//        fun bind(user:Users){
//            user_id.text= user.user_id.toString()
//            name.text= user.name
////            email.text= user.email
////            phone.text= user.phone
////            address.text= user.address
////            user_report.text= user.user_report.toString()
//        }
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