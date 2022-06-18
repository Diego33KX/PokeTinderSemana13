package com.diego.poketinder.viewmodel
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.diego.poketinder.data.model.User
import com.diego.poketinder.util.SharedPreferenceUtil

class RegisterViewModel (private val context: Context):ViewModel() {
    private lateinit var sharedPreferenceUtil: SharedPreferenceUtil
    val goRegisterActivity = MutableLiveData<Boolean>()
    val emptyFieldsError = MutableLiveData<Boolean>()
    val errorRegisterActivity = MutableLiveData<Boolean>()
    val errorCountPassword = MutableLiveData<Boolean>()

    fun onCreate(){
        sharedPreferenceUtil = SharedPreferenceUtil().also { it.setSharedPreference(context) }
    }
    fun validateInputs(name:String,email: String,password:String,passwordConfirm:String){
        if(name.isEmpty() || email.isEmpty() || password.isEmpty()){
            emptyFieldsError.postValue(true)
        }else if(password.length<6){
            errorCountPassword.postValue(true)
        } else{
            if (password.equals(passwordConfirm)){
               registerInputsInSharedPreference(name,email,password)
            }else{
                errorRegisterActivity.postValue(true)
            }
        }
    }
    private fun registerInputsInSharedPreference(name: String, email: String, password: String){
        val user = User(
            "1",
            name,
            email,
            password)
        sharedPreferenceUtil.saveFacebookUser(user)
        goRegisterActivity.postValue(true)
    }
}