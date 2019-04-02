package com.zpi.eventon.View.LoginRegisterViews

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.zpi.eventon.Presenter.LoginRegister
import com.zpi.eventon.R
import com.zpi.eventon.Interfaces.NavigationHost
import kotlinx.android.synthetic.main.fragment_remind_password.*
import kotlinx.android.synthetic.main.fragment_remind_password.view.*

class RemindPasswordFragment : Fragment() {

    private var remind_pswd_email = ""

    private val loginRegister = LoginRegister()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_remind_password, container, false)

        view.back_to_login_button.setOnClickListener {
            val email = getFormData()
            if (loginRegister.isEmailValid(email)) {
                    loginRegister.remindPassword(email, this.context!!)
                    (activity as NavigationHost).navigateTo(LoginFragment(), false)
                }
            else
                Toast.makeText(context, R.string.invalid_email, Toast.LENGTH_LONG).show()
        }

        return view
    }

    private fun getFormData() : String {

        remind_pswd_email = remindpassword_email.text.toString()

        return remind_pswd_email
    }
}
