package com.zpi.eventon.View.LoginRegisterViews
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zpi.eventon.Model.LoginFormData
import com.zpi.eventon.Presenter.LoginRegister
import com.zpi.eventon.R
import com.zpi.eventon.Interfaces.NavigationHost
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*


class LoginFragment : Fragment() {

    private var login = ""
    private var password = ""

    private val loginRegister = LoginRegister()

    private var isDataValid = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        view.dologin_button.setOnClickListener {

            val loginData = getFormData()
            loginRegister.login(loginData, this.context!!)
        }

        view.goregister_button.setOnClickListener {
            (activity as NavigationHost).navigateTo(RegisterFragment(), true)
        }

        view.remindpassword_button.setOnClickListener {
            (activity as NavigationHost).navigateTo(RemindPasswordFragment(), true)
        }

        return view
    }

    private fun getFormData() : LoginFormData {

        login = login_username_input.text.toString()
        password = login_password_input.text.toString()

        return LoginFormData(login, password)
    }

}
