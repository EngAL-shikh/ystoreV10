package com.amroz.ystore.Fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amroz.ystore.AddProductActivity
import com.amroz.ystore.Featchers
import com.amroz.ystore.Models.Products

import com.amroz.ystore.Models.Report
import com.amroz.ystore.R
import com.amroz.ystore.YstoreViewModels


class ReportFragment : Fragment() {
    private lateinit var RecyclerView: RecyclerView
    private lateinit var reportViewModel: ViewModel
    var type=""
    var searchlist=ArrayList<Report>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        reportViewModel = ViewModelProviders.of(this).get(YstoreViewModels::class.java)
      //  type=arguments?.getSerializable("type")as String
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var search= view.findViewById(R.id.searchfromT) as SearchView





            var report = Featchers()
            val LiveData = report.fetchReport()
            LiveData.observe(this, Observer {
                Log.d("test", "Response received: ${it}")
                RecyclerView.adapter = ReportAdapter(it)
                search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextChange(txtsearch: String?): Boolean {

                        searchlist.clear()
                        for (i in it){
                            if (i.author.contains(txtsearch.toString())){
                                searchlist.add(i)
                            } }
                        RecyclerView.layoutManager=LinearLayoutManager(context)
                        RecyclerView.adapter = ReportAdapter(searchlist)














                        return true

                    }


                    override fun onQueryTextSubmit(p0: String?): Boolean {






                        return true
                    }
                })
            })

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_report, container, false)
        RecyclerView = view.findViewById(R.id.report_recycler_view)
        RecyclerView.layoutManager = LinearLayoutManager(context)
        return view


    }

    companion object {
        fun newInstance() = ReportFragment()
    }


    // Holder
    private inner class UsersHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)

        }
        private lateinit var reportDetailsReport: Report
        val report_id = view.findViewById(R.id.report_id) as TextView
        val report_reason = view.findViewById(R.id.reason) as TextView
        val product_name = view.findViewById(R.id.prouct_name) as TextView



        fun bind(reports: Report) {
            reportDetailsReport=reports
            report_id.text = reports.report_id.toString()
            report_reason.text =reports.report_reason
            product_name.text =reports.author


        }

        override fun onClick(v: View?) {
            reportDetailsReport.report_id
        }


    }

    // Adapter
    inner class ReportAdapter(var reports: List<Report>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): RecyclerView.ViewHolder {
            var view: View



            view = layoutInflater.inflate(
                R.layout.report_ltem,
                parent, false
            )

            return UsersHolder(view)

        }




        override fun getItemCount(): Int {


            return reports.size

        }


        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

            val report = reports[position]
            if (holder is UsersHolder)
                holder.bind(report)


        }
    }
            }
