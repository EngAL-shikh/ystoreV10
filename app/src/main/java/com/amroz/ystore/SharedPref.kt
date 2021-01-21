package com.amroz.ystore

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

object SharedPref {
    const val EMAIL= "email"
    const val UID="uid"

    private  fun getSharedPreference(ctx: Context?): SharedPreferences? {
        return PreferenceManager.getDefaultSharedPreferences(ctx)
    }

    private fun  editor(context: Context, const:String, string: String){
        getSharedPreference(
            context
        )?.edit()?.putString(const,string)?.apply()
    }

    fun getEmail(context: Context)= getSharedPreference(
        context
    )?.getString(EMAIL,"")

    fun setEmail(context: Context, email: String){
        editor(
            context,
            EMAIL,
            email
        )
    }

    fun setUid(context: Context, uid:String){
        editor(
            context,
            UID,
            uid
        )
    }

    fun getUid(context: Context) = getSharedPreference(
        context
    )?.getString(UID,"")

}