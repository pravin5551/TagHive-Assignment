package com.example.taghive.data.local

import android.content.Context
import android.content.SharedPreferences
import com.example.taghive.data.Constant
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper

class PreferenceHelper constructor(context: Context) {
    private val prefFileName = "hlf-pref-file"
    private val pref: SharedPreferences
    private val mapper = ObjectMapper()

    init {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    init {
        pref = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE)
    }

    fun putString(key: String, value: String?) {
        pref.edit().putString(key, value).apply()
    }

    fun putBoolean(key: String, value: Boolean) {
        pref.edit().putBoolean(key, value).apply()
    }

    fun putInt(key: String, value: Int) {
        pref.edit().putInt(key, value).apply()
    }

    fun getBoolean(key: String, defValue: Boolean): Boolean = pref.getBoolean(key, defValue)

    fun getInt(key: String, defValue: Int): Int = pref.getInt(key, defValue)

    fun getString(key: String, defValue: String?): String? = pref.getString(key, defValue)









    fun clearSharedPref() {
        pref.edit().clear().apply()
    }

    fun clearAuthToken() {
        pref.edit().remove(Constant.AUTH_KEY).apply()
    }
}