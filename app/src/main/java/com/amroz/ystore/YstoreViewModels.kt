package com.amroz.ystore



import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
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
    val productByCatLiveData = MutableLiveData<Int>()

/// init
    init {
        liveDataCategory = Featchers().fetchCat()
        liveDataCart = Featchers().fetchCart()
        LiveDataReport = Featchers().fetchReport()
        LiveDataUsers = Featchers().fetchUsers()
        LiveDataProducts = Featchers().fetchProducts()


    }
    var productItemLiveData: LiveData<List<Products>> =
        Transformations.switchMap(productByCatLiveData) { id ->
            Featchers().fetchProductsByCat(id)
        }

    fun loadProductsByCategory(id: Int) {
        productByCatLiveData.value= id
    }


}


