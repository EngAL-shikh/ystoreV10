
package com.amroz.ystore.Fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amroz.ystore.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.catogrey_list.*
import kotlinx.android.synthetic.main.fragment_catogrey.*
import kotlinx.android.synthetic.main.fragment_update_category.view.*


class Catogrey_Fragment : Fragment() {

var admin=0

    var shaerd=context?.getSharedPreferences("admin",0)

    private lateinit var catViewModel: YstoreViewModels
  lateinit var addcat:FloatingActionButton
 // lateinit var total:TextView
    var count:Int=0

    private lateinit var RecyclerView: RecyclerView
    var type=""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        catViewModel =
            ViewModelProviders.of(this).get(YstoreViewModels::class.java)

      //  admin=arguments?.getSerializable("type")as String

    }

    override fun onStart() {
        super.onStart()





    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)






        var user = Featchers()
        val LiveData = user.fetchCat()
        LiveData.observe(this, Observer {
            Log.d("test", "Response received: ${it}")
            RecyclerView.adapter = CatAdapter(it)

        })







    }

    @SuppressLint("RestrictedApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_catogrey, container, false)

        RecyclerView = view.findViewById(R.id.rec)
         addcat= view.findViewById(R.id.add_cat)
       //  total= view.findViewById(R.id.total)
        RecyclerView.layoutManager = GridLayoutManager(context,2)


        if (QueryPreferences.getStoredQuery(context!!)=="admin"){

            addcat.visibility=View.VISIBLE

        }else{
            addcat.visibility=View.GONE

        }



        addcat.setOnClickListener {

            var intent=Intent(context,AddCategoryActivity::class.java)
            startActivity(intent)


        }
        return view
    }





    // Cat Holder
    private inner class CatHolder(view: View) : RecyclerView.ViewHolder(view) {


        val cattitle = view.findViewById(R.id.title) as TextView
        val card_cat = view.findViewById(R.id.card_cat) as CardView
        val catImage= view.findViewById(R.id.image) as ImageView
        val cat_card= view.findViewById(R.id.card_cat) as CardView
        val update= view.findViewById(R.id.update) as Button




        fun bind(cat: Category) {

            cattitle.text = cat.cat_title
            Picasso.with(context).load(cat.images).into(catImage)
            cat_card.setOnClickListener {

                var intent = Intent(context,ProductByCat::class.java)
                intent.putExtra("cat_id",cat.cat_id)
                startActivity(intent)




                Log.d("btnupdate",QueryPreferences.getStoredQuery(context!!))

                if (QueryPreferences.getStoredQuery(context!!)== "admin"){





                    update.visibility=View.GONE
                }

            }







            update.setOnClickListener {


                categoryDialogUpdate(cat)
            }

            card_cat.setOnClickListener {

                var intent= Intent(context,ProductByCat::class.java)
                intent.putExtra("cat_id",cat.cat_id)
                startActivity(intent)
            }


        }




    }

    // NewsAdapter
    inner class CatAdapter(var news: List<Category>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): RecyclerView.ViewHolder {


            var view: View = layoutInflater.inflate(
                R.layout.catogrey_list,
                parent, false
            )

            return CatHolder(view)

        }


        override fun getItemCount(): Int {

            count=news.size

            return news.size

        }


        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

            val news = news[position]
            if (holder is CatHolder)
                holder.bind(news)


        }
    }


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
        view.btn_cancle.setOnClickListener {
            alertDialog.dismiss()
        }
///////////////////////////////////////////////////////////////////////////////////////////////////


    }

    companion object{
        fun newInstance(data: String):Catogrey_Fragment{
            val args=Bundle().apply {
                putSerializable("name", data)
            }
            return  Catogrey_Fragment().apply {
                arguments=args
            }
        }
    }


}



