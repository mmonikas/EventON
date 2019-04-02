package com.zpi.eventon.Presenter

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.widget.EditText
import android.widget.Toast
import com.zpi.eventon.*
import com.zpi.eventon.Model.LoginFormData
import com.zpi.eventon.Model.RegistrationFormData
import com.zpi.eventon.REST.UserRegistrationRest
import com.zpi.eventon.Validation.DataValidator
import com.zpi.eventon.View.LoginRegisterViews.LoginFragment
import com.zpi.eventon.View.HomeViews.MainViewActivity
import com.zpi.eventon.Interfaces.NavigationHost
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginRegister {

    private val dataValidator = DataValidator()
    private var restService = UserRegistrationRest("default")

    fun register(data: RegistrationFormData, context: Context){
        val callMessage = restService.registrationRequest(data)
        handleRequest(callMessage, context)
    }

    fun login(data: LoginFormData, context: Context){
        val callMessage = restService.loginRequest(data)
        handleLoginRequest(callMessage, context)
    }


    fun remindPassword(data: String, context: Context){
        val callMessage = restService.remindPassword(data)
        handleRequest(callMessage, context)
    }

    private fun handleRequest(callMessage: Call<String>, context: Context) {
        callMessage.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>,
                                    response: Response<String>
            ) {
                if (response.code() == 200)
                {
                    (context as NavigationHost).navigateTo(LoginFragment(), false)
                }
                else
                {
                    Toast.makeText(context, R.string.register_result_failure, Toast.LENGTH_LONG).show()
                    println(response.errorBody())
                }
            }

            override fun onFailure(call: Call<String>,
                                   t: Throwable) {
                Toast.makeText(context, R.string.register_result_failure, Toast.LENGTH_LONG).show()
            }
        })

    }

    private fun handleLoginRequest(callMessage: Call<String>, context: Context){
        callMessage.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {

                if (response.code() == 200)
                {
                    val sharedPref: SharedPreferences =
                        context!!.getSharedPreferences("auth_token", Context.MODE_PRIVATE)
                    val editor: SharedPreferences.Editor = sharedPref.edit()
                    editor.putString("auth_token", response.body().toString())


                    val intent = Intent (context, MainViewActivity::class.java)
                    context.startActivity(intent)
                }
                else
                {   Toast.makeText(context, R.string.loginerror, Toast.LENGTH_LONG).show()
                    println(response.errorBody())
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {

            }
        })
    }

    fun isRegistrationInputDataValid(register_name: EditText, register_email: EditText, register_login: EditText,
                                             register_password: EditText, register_password2: EditText) : Boolean {
        return dataValidator.isNameValid(register_name) && dataValidator.isEmailValid(register_email)
                && dataValidator.isLoginValid(register_login) && dataValidator.isPasswordValid(register_password)
                && dataValidator.isPassword2Valid(register_password2, register_password)
    }

    fun isEmailValid(email: String) : Boolean {
        return dataValidator.isEmailValid(email)
    }


}