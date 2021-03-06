package com.amroz.ystore.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amroz.ystore.Featchers
import com.amroz.ystore.Models.Report
import com.amroz.ystore.R
import com.amroz.ystore.YstoreViewModels
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_report.*


class ReportFragment : Fragment() {
    //////////////////////////product_status//////////////////////////
    private val ystoreViewModels: YstoreViewModels by lazy {
        ViewModelProviders.of(this).get(YstoreViewModels::class.java)
    }
    //////////////////////////product_status//////////////////////////
    private lateinit var title: TextView
    private lateinit var details: TextView
    private lateinit var image: ImageView
    private lateinit var close: ImageView
    private lateinit var ProByRpoCard: CardView
    private lateinit var p_status: Button
    private lateinit var enable_btn: Button
   // var p_pause=0
    var  p_st:Int=0
    var  p_id:Int=0

    private lateinit var RecyclerView: RecyclerView
    private lateinit var reportViewModel: ViewModel
    var type = ""
    var searchlist = ArrayList<Report>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        reportViewModel = ViewModelProviders.of(this).get(YstoreViewModels::class.java)

        //  type=arguments?.getSerializable("type")as String
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var search = view.findViewById(R.id.searchfromT) as SearchView


        var report = Featchers()
        val LiveData = report.fetchReport()
        LiveData.observe(this, Observer {
            Log.d("test", "Response received: ${it}")
            RecyclerView.adapter = ReportAdapter(it)
            search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(txtsearch: String?): Boolean {

                    searchlist.clear()
                    for (i in it) {
                        if (i.author.contains(txtsearch.toString())) {
                            searchlist.add(i)
                        }
                    }
                    RecyclerView.layoutManager = LinearLayoutManager(context)
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
        title = view.findViewById(R.id.ProByRpoTitle) as TextView
        details = view.findViewById(R.id.ProByRpoDeatils) as TextView
        image = view.findViewById(R.id.ProByRpoImage) as ImageView
        close = view.findViewById(R.id.ProByRepoClose) as ImageView
        ProByRpoCard = view.findViewById(R.id.ProByRpoCard) as CardView
        p_status = view.findViewById(R.id.pause_btn) as Button
        enable_btn = view.findViewById(R.id.enable_btn) as Button

        return view


    }

    companion object {
        fun newInstance() = ReportFragment()
    }


    // Holder
    private inner class UsersHolder(view: View) : RecyclerView.ViewHolder(view) {


        private lateinit var reportDetailsReport: Report
        val report_id = view.findViewById(R.id.report_id) as TextView
        val report_reason = view.findViewById(R.id.reason) as TextView
        val product_name = view.findViewById(R.id.prouct_name) as TextView
        //val cardReport= view.findViewById(R.id.CardReport) as CardView



        fun bind(reports: Report) {
            reportDetailsReport = reports
            report_id.text = reports.report_id.toString()
            report_reason.text = reports.report_reason
            product_name.text = reports.author
            product_name.setOnClickListener {
                var pro = Featchers()
                var liveData = pro.fetchProductsByReportID(reports.report_id, reports.product_id)
                liveData.observe(this@ReportFragment, Observer {
                    var images = it[0].images.split(",").toTypedArray()
                    var splitImage = images[0]
                    title.text = it[0].title
                    details.text = it[0].details
                    Picasso.get().load(splitImage).into(image)
                    //////////////////////////product_status//////////////////////////
                        p_st=it[0].product_status
                        p_id=it[0].product_id
                    if(p_st==1){

                        enable_btn.visibility=View.VISIBLE
                        p_status.visibility=View.GONE
                    }
                    else{
                        enable_btn.visibility=View.GONE
                        p_status.visibility=View.VISIBLE
                    }

                    //////////////////////////product_status//////////////////////////

                })
                ProByRpoCard.visibility = View.VISIBLE
                YoYo.with(Techniques.FadeInUp)
                    .duration(2000)
                    .playOn(ProByRpoCard)


            }


            p_status.setOnClickListener {
                Log.d("801",p_st.toString())

                     p_status.visibility=View.GONE
                    enable_btn.visibility=View.VISIBLE
                    ystoreViewModels.productStatus(p_id, 1)
                    // p_status.text="Enable"
                    //p_status.setBackgroundResource(R.color.green_900)





            }
            enable_btn.setOnClickListener {
                Log.d("801",p_st.toString())

                enable_btn.visibility=View.GONE
                p_status.visibility=View.VISIBLE
                ystoreViewModels.productStatus(p_id, 0)
                // p_status.text="Enable"
                //p_status.setBackgroundResource(R.color.green_900)





            }
            close.setOnClickListener {
                ProByRpoCard.visibility = View.GONE

            }

//        override fun onClick(v: View?) {
//            var pro= Featchers()
//            var liveData= pro.fetchProductsByReportID(reportDetailsReport.report_id,reportDetailsReport.product_id)
//            liveData.observe(this@ReportFragment, Observer {
//                var images=  it[0].images.split(",").toTypedArray()
//                var splitImage=images[0]
//
//
//            })
//        }


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


