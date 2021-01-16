package com.amroz.ystore.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amroz.ystore.Category
import com.amroz.ystore.Featchers
import com.amroz.ystore.R
import com.amroz.ystore.YstoreViewModels
import kotlinx.android.synthetic.main.fragment_update_category.view.*


class Catogrey_Fragment : Fragment() {
    private lateinit var catViewModel: YstoreViewModels
    var count:Int=0

    private lateinit var RecyclerView: RecyclerView
    var type=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        catViewModel =
            ViewModelProviders.of(this).get(YstoreViewModels::class.java)
        type=arguments?.getSerializable("type")as String
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (type == "Category") {


            var user = Featchers()
            val LiveData = user.fetchCat()
            LiveData.observe(this, Observer {
                Log.d("test", "Response received: ${it}")
                RecyclerView.adapter = UserAdapter(it)

            })
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_catogrey, container, false)

        RecyclerView = view.findViewById(R.id.rec)

        RecyclerView.layoutManager = LinearLayoutManager(context)
        return view
    }

    companion object {
        fun newInstance(data: String): Catogrey_Fragment {
            val args = Bundle().apply {
                putSerializable("type", data)
            }
            return Catogrey_Fragment().apply {
                arguments = args
            }
        }

    }



    // News Holder
    private inner class UsersHolder(view: View) : RecyclerView.ViewHolder(view) {


        val cattitle = view.findViewById(R.id.cattitle) as TextView
        val sub_cat_title = view.findViewById(R.id.sub_cat_title) as TextView
        /////////////////////////////////////////////////////UpdateCatDialog/////////////////////////
        val button = view.findViewById(R.id.update) as Button
        fun categoryDialogUpdate(cat: Category){
            val alertBuilder = AlertDialog.Builder(requireContext())
            val view = layoutInflater.inflate(R.layout.fragment_update_category, null)
            alertBuilder.setView(view)
            val alertDialog = alertBuilder.create()
            alertDialog.show()
           view.ed_update_category.setText(cat.cat_title)
            view.btn_update.setOnClickListener {
                 var updateEditText =view.ed_update_category.text.toString()
                if(updateEditText.isNotEmpty()) {
                    Log.d("hajar",updateEditText )
                    val response = catViewModel.updateCategory(
                        cat.cat_id,
                        view.ed_update_category.text.toString()
                    )
                 response.observe(
                        viewLifecycleOwner,
                        Observer { message ->

                            Toast.makeText(requireContext(), "updated", Toast.LENGTH_SHORT).show()
                        }
                    )
                    alertDialog.dismiss()
                }else{
                    view.ed_update_category.setBackgroundResource(R.drawable.erorrshape)
                    Toast.makeText(requireContext(), " one filed empty filed require", Toast.LENGTH_SHORT).show()
                }
            }
            view.btn_cancel.setOnClickListener {
                alertDialog.dismiss()
            }
///////////////////////////////////////////////////////////////////////////////////////////////////
        }

        fun bind(cat: Category) {

            cattitle.text = cat.cat_id.toString()
            sub_cat_title.text = cat.cat_title
            button.setOnClickListener {
                categoryDialogUpdate(cat)
            }
        }


    }

    // NewsAdapter
    inner class UserAdapter(var news: List<Category>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): RecyclerView.ViewHolder {
            var view: View = layoutInflater.inflate(
                R.layout.catogrey_list,
                parent, false
            )

            return UsersHolder(view)

        }


        override fun getItemCount(): Int {
            count=news.size
            return news.size

        }
        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

            val news = news[position]
            if (holder is UsersHolder)
                holder.bind(news)


        }
    }
}



