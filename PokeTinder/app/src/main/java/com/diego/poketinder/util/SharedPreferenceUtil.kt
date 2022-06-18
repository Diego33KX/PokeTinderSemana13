package com.diego.poketinder.util

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.diego.poketinder.data.model.User
import com.google.gson.Gson

class SharedPreferenceUtil {
    companion object{
        private const val SHARED_PREFERENCE_KEY = "SHARED_PREFERENCE_KEY"
        private lateinit var sharedPreference: SharedPreferences
        private const val USER = "USER"
        private const val INTRO ="INTRO"
    }
    fun setSharedPreference(context: Context){
        sharedPreference = context
            .getSharedPreferences(SHARED_PREFERENCE_KEY, Context.MODE_PRIVATE)
    }
    fun saveFacebookUser(user: User){
        val gson = Gson()
        val jsonFacebookUser = gson.toJson(user)
        sharedPreference
            .edit()
            .putString(USER, jsonFacebookUser)
            .apply()
    }
    fun getUser(): User?{
        var userFacebook: User?=null
        val jsonUserFacebook = sharedPreference.getString(USER,"")
        try{
            userFacebook = Gson().fromJson(jsonUserFacebook, User::class.java)
        }catch (e:Exception){
            Log.d("Codercool",e.message.toString())
        }
        return userFacebook
    }
    fun saveIntroShow(){
        sharedPreference
            .edit()
            .putBoolean(INTRO, true)
            .apply()
    }
    fun getIntroShow():Boolean = sharedPreference
        .getBoolean(INTRO,false)
}