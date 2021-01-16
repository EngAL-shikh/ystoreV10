package com.amroz.ystore



import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amroz.ystore.Models.Cart
import com.amroz.ystore.Models.Products
import com.amroz.ystore.Models.Report
import com.amroz.ystore.Models.Users
class YstoreViewModels: ViewModel() {
    /////////////////////////////////Update Category
    fun updateCategory(catId:Int,category: String):
            MutableLiveData<CodeResponse> {
        return  ManagementFeatchers().updateCategory(catId,category)
    }
    fun updateProfile(id:Int,
                      name: String,
                      phone:String,
                      address:String):
            MutableLiveData<CodeResponse> {
        return  ManagementFeatchers().
        updateProfile(id,name,phone,address)
    }
    /////////////////////////////////Update Product
    fun updateProduct(product_id:Int,
                      title: String,
                      details:String,
                      color:String,
                      product_features:String,
                      price_y:Int,
                      price_d:Int
    ):
            MutableLiveData<CodeResponse> {
         return ManagementFeatchers().
        updateProduct(
            product_id,
            title,
            details,
            color,
            product_features,
            price_y,
            price_d)
    }
    /////////////////////UpdateCart
    fun updateCart(id:Int, quntity: String): MutableLiveData<CodeResponse> {
        return ManagementFeatchers().updateCart(id,quntity)
    }
/////////////////////////////////////////
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


}


