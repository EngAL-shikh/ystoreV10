package com.amroz.ystore



import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.amroz.ystore.Models.Report

class YstoreViewModels:ViewModel() {
    val LiveData: LiveData<List<Report>>

    init {
        LiveData = Feachers().fetchContents()


    }


open class YstoreViewModels: ViewModel() {

    val liveDataCategory: LiveData<List<Category>>
    var liveDataCart: LiveData<List<Cart>>

    init {
        liveDataCategory = Featchers().fetchCategory()
        liveDataCart = Featchers().fetchCart()

    }


}