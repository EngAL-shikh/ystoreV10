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
    var LiveDataUsersInfo = MutableLiveData<Int>()


    init {
        liveDataCategory = Featchers().fetchCat()
        liveDataCart = Featchers().fetchCart()
        LiveDataReport = Featchers().fetchReport()
        LiveDataUsers = Featchers().fetchUsers()
        LiveDataProducts = Featchers().fetchProducts()


    }




}


var   userLiveData:LiveData<List<Users>> = Transformations.switchMap(LiveDataUsersInfo){id->
    Featchers().fetchUsersInfo(id)}

    fun loadUsers(id:Int){
        LiveDataUsersInfo.value=id
    }
}




