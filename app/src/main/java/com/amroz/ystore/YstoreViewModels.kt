package com.amroz.ystore



import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amroz.ystore.Models.*

class YstoreViewModels: ViewModel() {
    /////////////////////////////////Update Category
    fun updateCategory(catId:Int,category: String):
            MutableLiveData<Response> {
        return  ManagementFeatchers().updateCategory(catId,category)
    }

    //update_user_report

    fun update_user_report(user_id:Int, user_report:Int): MutableLiveData<Response> {
        return ManagementFeatchers(). updateReportUser(user_id, user_report)
    }


    fun updateProfile(id:Int,
                      name: String,
                      email:String,
                      phone:String,
                      address:String):
            MutableLiveData<Response> {
        return  ManagementFeatchers().
        updateProfile(id,name,email,phone,address)
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
            MutableLiveData<Response> {
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
    fun updateCart(id:Int, quntity: String): MutableLiveData<Response> {
        return ManagementFeatchers().updateCart(id,quntity)
    }
/////////////////////////////////////////
    var liveDataCategory: LiveData<List<Category>>
    var liveDataCart: LiveData<List<Cart>>
    var LiveDataReport: LiveData<List<Report>>
    var LiveDataUsers: LiveData<List<Users>>
    var liveDataRatingUs: LiveData<List<RatingUs>>
    var LiveDataProducts: LiveData<List<Products>>
    var LiveDataUsersInfo = MutableLiveData<Int>()



    init {
        liveDataCategory = Featchers().fetchCat()
        liveDataCart = Featchers().fetchCart()
        LiveDataReport = Featchers().fetchReport()
        LiveDataUsers = Featchers().fetchUsers()
        LiveDataProducts = Featchers().fetchProducts()
        liveDataRatingUs= Featchers().fetchRating()
    }

    /////////////////////Rug////////////////////////////////////////////////////////////////////////////////////////////////

    fun addCategory(cat_title:String,images:String) : MutableLiveData<Response> {
        return AddFeacher().addCategory(cat_title,images)
    }
    fun addUsers(name: String, email: String,password: String,chat_id: String,
                 phone: String,address: String,image:String) : MutableLiveData<Response> {
        return AddFeacher().addUser(name, email,password,chat_id,
            phone,address,image)
    }
    fun addProduct(title:String,details:String,images:String,color:String,product_features:String,rating:Int,
                   price_y:Int,price_d:Int,user_id: Int,cat_id:Int,report_id:Int, order_data:String, data:String): MutableLiveData<Response>
    {
        return AddFeacher().addproduct(title,details,images,color,product_features,rating,
            price_y,price_d,user_id,cat_id,report_id, order_data, data)
    }
    fun addCart(user_id:Int,product_id:Int,Quantity:Int):MutableLiveData<Response> {
        return AddFeacher().addCart(user_id,product_id,Quantity)
    }


    //addReportProduct
    fun addReportProduct(id:Int,report_reason:String,product_id:Int,user_id:Int):MutableLiveData<Response> {
        return ManagementFeatchers().addReport(id,report_reason,product_id,user_id)
    }
    /////////////////rating ViewModel
    fun updateRating(id:Int, rating: Float): MutableLiveData<Response> {
        return ManagementFeatchers().updateRating(id,rating)
    }
    //////////////////addRating
    fun addRating(rating:Float,product_id:Int,user_id:Int):MutableLiveData<Response> {
        return ManagementFeatchers().addRating(rating,product_id,user_id)
    }
    /////////////////delete
    fun deleteRating(id:Int) {
        return ManagementFeatchers().deleteRating(id)
    }
}


//
//    var   userLiveData:LiveData<List<Users>> = Transformations.switchMap(LiveDataUsersInfo){id->
//        Featchers().fetchUsersInfo(id)}
//
//    fun loadUsers(id:Int){
//        LiveDataUsersInfo.value=id
//    }











