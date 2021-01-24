package com.amroz.ystore

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

object SharedPrefAdmin {
    const val rule=""

    private  fun getSharedPreference(ctx: Context?): SharedPreferences? {
        return PreferenceManager.getDefaultSharedPreferences(ctx)
    }

    private fun  editor(context: Context, const:String, string: String){
        getSharedPreference(
            context
        )?.edit()?.putString(const,string)?.apply()
    }

    fun getRule(context: Context)= SharedPrefAdmin.getSharedPreference(
        context
    )?.getString(SharedPrefAdmin.rule,"user")

    fun setRule(context: Context, recivrule: String){
        SharedPrefAdmin.editor(
            context,
            SharedPrefAdmin.rule,
            recivrule
        )
    }

}