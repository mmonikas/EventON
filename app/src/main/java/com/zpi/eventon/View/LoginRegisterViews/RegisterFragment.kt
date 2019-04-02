package com.zpi.eventon.View.LoginRegisterViews

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.zpi.eventon.Model.RegistrationFormData
import com.zpi.eventon.Presenter.LoginRegister
import com.zpi.eventon.R
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_register.view.*


class RegisterFragment : Fragment() {

    private var name = ""
    private var email = ""
    private var login = ""
    private var password = ""

    private val loginRegister = LoginRegister()
    private lateinit var registerData : RegistrationFormData

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)

        view.doregister_button.setOnClickListener {

            registerData = getFormData()

            if(loginRegister.isRegistrationInputDataValid(register_name, register_email,
                    register_login, register_password, register_password2)) {

                loginRegister.register(registerData, this.context!!)
            }
            else
                Toast.makeText(context, R.string.error_register_data, Toast.LENGTH_LONG).show()
        }

        return view
    }

    private fun getFormData() : RegistrationFormData {

        name = register_name.text.toString()
        email = register_email.text.toString()
        login = register_login.text.toString()
        password = register_password.text.toString()

        return RegistrationFormData(name, email, login, password)
    }
}
