package com.diego.poketinder.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.diego.poketinder.data.model.User
import com.diego.poketinder.util.SharedPreferenceUtil

class LoginViewModel(private val context: Context):ViewModel() {
    private lateinit var sharedPreferenceUtil: SharedPreferenceUtil

    val emptyFieldsError = MutableLiveData<Boolean>()
    val fieldsAuthenticateError = MutableLiveData<Boolean>()
    val goSuccessActivity = MutableLiveData<Boolean>()

    fun onCreate(){
        sharedPreferenceUtil = SharedPreferenceUtil().also { it.setSharedPreference(context) }
    }
    fun validateInputs(email:String, password:String){
        val user: User? = sharedPreferenceUtil.getUser()
        if(email.isEmpty() || password.isEmpty()){
            emptyFieldsError.postValue(true)
        }else{
            if(email.equals(user?.email) && password.equals(user?.password)){
                goSuccessActivity.postValue(true)
            }else{
                fieldsAuthenticateError.postValue(true)
            }
        }

    }
}