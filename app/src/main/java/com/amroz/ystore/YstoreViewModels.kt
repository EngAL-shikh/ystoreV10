package com.amroz.ystore



import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel



open class YstoreViewModels: ViewModel() {










































    ///////////////////////////////////////////// add ////////////////////////////////
    fun addCategory(cat_title:String,images:String) : MutableLiveData<CodeResponse> {
        return ManagementFeatchers().addCategory(cat_title,images)
    }
    fun addProduct(title:String,details:String,images:String,color:String,product_features:String,rating:Int,
                   price_y:Double,price_d:Double,user_id: Int,cat_id:Int,report_id:Int, order_data:String, data:String): MutableLiveData<CodeResponse>
    {
        return ManagementFeatchers().addproduct(title,details,images,color,product_features,rating,
            price_y,price_d,user_id,cat_id,report_id, order_data, data)
    }
    fun addCart(user_id:Int,product_id:Int,Quantity:Int):MutableLiveData<CodeResponse> {
    return ManagementFeatchers().addCart(user_id,product_id,Quantity)
    }
    }


