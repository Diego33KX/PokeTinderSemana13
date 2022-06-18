package com.diego.poketinder.ui.view

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.diego.poketinder.databinding.ActivityRegisterBinding
import com.diego.poketinder.viewmodel.RegisterViewModel

class RegisterActivity : BaseActivity<ActivityRegisterBinding>(ActivityRegisterBinding::inflate){
    private lateinit var registerViewModel: RegisterViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerViewModel = RegisterViewModel(this)
        registerViewModel.onCreate()

        registerViewModel.emptyFieldsError.observe(this){
            AlertDialog("Necesita completar todos los campos solicitados para registrarse")
        }

        registerViewModel.errorRegisterActivity.observe(this){
            AlertDialog("Las constraseñas no coinciden")
        }
        registerViewModel.errorCountPassword.observe(this){
            AlertDialog("La contraseña debe contener almenos 6 caracteres")
        }
        registerViewModel.goRegisterActivity.observe(this){
            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
        }
    }
    fun registerUser(view: View){
        //SE REALIZAN LAS VALIDACIONES
        registerViewModel.validateInputs(binding.edtUserName.text.toString(),binding.edtEmail.text.toString(),
            binding.edtPassword.text.toString(),binding.edtPassword2.text.toString())
    }
    fun loginUser(view: View){
        val intent = Intent(applicationContext, LoginActivity::class.java)
        startActivity(intent)
    }
    //SE IMPLEMENTA UNA FUNCION QUE MOSTRARÁ UN ALERTDIALOG DEPENDIENDO DE LA VALIDACIÓN
    fun AlertDialog(mensaje:String){
        val builder = androidx.appcompat.app.AlertDialog.Builder(this)
        builder.setTitle("Alerta!")
        builder.setMessage(mensaje)
            .setPositiveButton("Volver",
                DialogInterface.OnClickListener{ dialog, id -> })
        builder.show()
    }
}