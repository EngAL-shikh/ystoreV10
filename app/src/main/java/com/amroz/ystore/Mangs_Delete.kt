package com.amroz.ystore

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class Mangs_Delete : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mangs__delete)

        var butcat = findViewById<Button>(R.id.del_cat)
        var butpro = findViewById<Button>(R.id.del_pro)
        var butuser = findViewById<Button>(R.id.del_user)
        var butcart = findViewById<Button>(R.id.del_cart)
        var butrep = findViewById<Button>(R.id.del_report)
        butrep.setOnClickListener {

            delet_report()
        }

        butcat.setOnClickListener {
            delet_catogrey()
        }

        butpro.setOnClickListener {
            delet_product()
        }

        butuser.setOnClickListener {

            delet_user()
        }
        butcart.setOnClickListener {
            delet_cart()

        }
    }

    fun delet_catogrey() {
        val builder = AlertDialog.Builder(this)
        //  builder.setTitle("AlertDialog")
        builder.setMessage("Are you sure ")

        // add the buttons

        // add the buttons
        builder.setPositiveButton("Continue") { _, _ ->
            var del_cat = ManagementFeatchers()
            del_cat.deleteCategory(1)
        }
        builder.setNegativeButton("Cancel") { _, _ ->

        }
        val dialog = builder.create()
        dialog.show()


    }

    fun delet_product() {

        val builder = AlertDialog.Builder(this)
        //  builder.setTitle("AlertDialog")
        builder.setMessage("Are you sure ")

        // add the buttons

        // add the buttons
        builder.setPositiveButton("Continue") { _, _ ->
            var del_cat = ManagementFeatchers()
            del_cat.deleteProduct(3)
        }
        builder.setNegativeButton("Cancel") { _, _ ->

        }
        val dialog = builder.create()
        dialog.show()


    }

    fun delet_user() {

        val builder = AlertDialog.Builder(this)
        //  builder.setTitle("AlertDialog")
        builder.setMessage("Are you sure ")

        // add the buttons

        // add the buttons
        builder.setPositiveButton("Continue") { _, _ ->
            var del_cat = ManagementFeatchers()
            del_cat.deleteUser(2)

        }
        builder.setNegativeButton("Cancel") { _, _ ->

        }
        val dialog = builder.create()
        dialog.show()


    }

    fun delet_cart() {

        val builder = AlertDialog.Builder(this)
        //  builder.setTitle("AlertDialog")
        builder.setMessage("Are you sure ")

        // add the buttons

        // add the buttons
        builder.setPositiveButton("Continue") { _, _ ->
            var del_cart = ManagementFeatchers()
            del_cart.deleteCart(1)

        }
        builder.setNegativeButton("Cancel") { _, _ ->

        }
        val dialog = builder.create()
        dialog.show()


    }

    fun delet_report() {
        val builder = AlertDialog.Builder(this)
        //  builder.setTitle("AlertDialog")
        builder.setMessage("Are you sure ")

        // add the buttons

        // add the buttons
        builder.setPositiveButton("Continue") { _, _ ->
            var del_rep = ManagementFeatchers()
            del_rep.deletereports(1)

        }
        builder.setNegativeButton("Cancel") { _, _ ->

        }
        val dialog = builder.create()
        dialog.show()


    }

}