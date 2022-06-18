package com.diego.poketinder.ui.view

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.diego.poketinder.databinding.ActivityLoginBinding
import com.diego.poketinder.viewmodel.LoginViewModel

class LoginActivity: BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginViewModel = LoginViewModel(this)
        loginViewModel.onCreate()

        loginViewModel.emptyFieldsError.observe(this){
            AlertDialog("Debe completar todos los campos solicitados para iniciar sesion")
        }
        loginViewModel.fieldsAuthenticateError.observe(this){
            AlertDialog("Email o contraseña incorrecto")
        }
        loginViewModel.goSuccessActivity.observe(this){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
    fun AlertDialog(mensaje:String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Alerta!")
        builder.setMessage(mensaje)
            .setPositiveButton("Volver",
                DialogInterface.OnClickListener{dialog, id -> })
        builder.show()
    }
    /////////////----------------------

    fun startLogin(view: View){
        //Validate input
        loginViewModel.validateInputs(binding.edtEmail.text.toString(),binding.edtPassword.text.toString())
    }
    fun registrarUsuario(view: View){
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }
}


