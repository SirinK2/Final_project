package com.tuwaiq.finalproject.util

import android.content.Context
import androidx.preference.PreferenceManager
private const val IS_CHECKED = "is checked"
private const val IS_CHOOSE_LANGUAGE = "choose language"
private const val LANGUAGE_CODE = "lang code"
object SharedPref {




    fun saveNotificationState(context: Context,isChecked:Boolean ){
        PreferenceManager.getDefaultSharedPreferences(context)
            .edit()
            .putBoolean(IS_CHECKED,isChecked)
            .apply()
    }

    fun getNotificationState(context: Context):Boolean{
        return PreferenceManager.getDefaultSharedPreferences(context)
            .getBoolean(IS_CHECKED,false)

    }




    fun setLanguageState(context: Context,isChecked: Boolean, langCode:String){
        PreferenceManager.getDefaultSharedPreferences(context)
            .edit()
            .putBoolean(IS_CHOOSE_LANGUAGE,isChecked)
            .putString(LANGUAGE_CODE,langCode)
            .apply()

    }

    fun getLangCode(context: Context, defValue: String):String{
        return PreferenceManager.getDefaultSharedPreferences(context)
            .getString(LANGUAGE_CODE,defValue)!!
    }

    fun getLanguageState(context: Context):Boolean{
        return PreferenceManager.getDefaultSharedPreferences(context)
            .getBoolean(IS_CHOOSE_LANGUAGE,true)





    }





}