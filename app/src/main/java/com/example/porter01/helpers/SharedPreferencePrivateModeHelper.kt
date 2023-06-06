package com.example.porter01.helpers

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencePrivateModeHelper(val context: Context, sharedPreferenceName :String) {
    private val sharedPreferences : SharedPreferences = context.getSharedPreferences(sharedPreferenceName,Context.MODE_PRIVATE)
    private val sharedPreferencesEditor :SharedPreferences.Editor = sharedPreferences.edit()
    fun setData(key : String ,data : String){
        sharedPreferencesEditor.apply {
            putString(key, data)
            apply()
        }
    }
    fun getData(key : String):String?{
        return sharedPreferences.getString(key,null)
    }
}