package com.amroz.ystore



import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amroz.ystore.Models.Cart
import com.amroz.ystore.Models.Products
import com.amroz.ystore.Models.Report
import com.amroz.ystore.Models.Users


open class YstoreViewModels: ViewModel() {

    var liveDataCategory: LiveData<List<Category>>
    var liveDataCart: LiveData<List<Cart>>
    var LiveDataReport: LiveData<List<Report>>
    var LiveDataUsers: LiveData<List<Users>>
    var LiveDataProducts: LiveData<List<Products>>

/// init
    init {
        liveDataCategory = Featchers().fetchCat()
        liveDataCart = Featchers().fetchCart()
        LiveDataReport = Featchers().fetchReport()
        LiveDataUsers = Featchers().fetchUsers()
        LiveDataProducts = Featchers().fetchProducts()

    }
    fun addCategory(cat_title:String,images:String) : MutableLiveData<CodeRedponse> {
        return AddFeacher().addCategory(cat_title,images)
    }
    fun addProduct(title:String,details:String,images:String,color:String,product_features:String,rating:Int,
                   price_y:Double,price_d:Double,user_id: Int,cat_id:Int,report_id:Int, order_data:String, data:String): MutableLiveData<CodeRedponse>
    {
        return AddFeacher().addproduct(title,details,images,color,product_features,rating,
            price_y,price_d,user_id,cat_id,report_id, order_data, data)
    }
    fun addCart(user_id:Int,product_id:Int,Quantity:Int):MutableLiveData<CodeRedponse> {
    return AddFeacher().addCart(user_id,product_id,Quantity)
    }
    }


