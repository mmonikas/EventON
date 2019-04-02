package com.zpi.eventon.Validation

import android.support.design.widget.TextInputLayout
import android.widget.EditText
import java.util.regex.Pattern

open class DataValidator {

    private val NAME_VALIDATION_MSG = "Imię musi mieć więcej niż 2 znaki"
    private val LOGIN_VALIDATION_MSG = "Login musi mieć więcęj niż 2 znaki"
    private val EMAIL_VALIDATION_MSG = "Niepoprawny adres e-mail"
    private val PASSWORD_POLICY = "Hasło musi mieć min. 8 znaków, w tym min. 1 cyfrę"
//    * data - can be EditText or String
//    * updateUI - if true and if data is EditText, sets error to the EditText or its TextInputLayout

    fun isNameValid(data: Any, updateUI: Boolean = true): Boolean {
        val str = getText(data)
        val valid = str.trim().length > 2

        // Set error if required
        if (updateUI) {
            val error: String? = if (valid) null else NAME_VALIDATION_MSG
            setError(data, error)
        }

        return valid
    }

    fun isEmailValid(data: Any, updateUI: Boolean = true): Boolean {
        val str = getText(data)
        val valid = Pattern.compile(
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@"
                        + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|"
                        + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(str).matches()

        // Set error if required
        if (updateUI) {
            val error: String? = if (valid) null else EMAIL_VALIDATION_MSG
            setError(data, error)
        }

        return valid
    }

    fun isLoginValid(data: Any, updateUI: Boolean = true): Boolean {
        val str = getText(data)
        val valid = str.trim().length > 2

        // Set error if required
        if (updateUI) {
            val error: String? = if (valid) null else LOGIN_VALIDATION_MSG
            setError(data, error)
        }

        return valid

    }

    fun isPasswordValid(data: Any, updateUI: Boolean = true): Boolean {
        val str = getText(data)
        var valid = true

        // Password policy check
        // Password should be minimum minimum 8 characters long
        if (str.length < 8) {
            valid = false
        }
//         Password should contain at least one number
        var exp = ".*[0-9].*"
        var pattern = Pattern.compile(exp, Pattern.CASE_INSENSITIVE)
        var matcher = pattern.matcher(str)
        if (!matcher.matches()) {
            valid = false
        }

        // Password should contain at least one capital letter
//        exp = ".*[A-Z].*"
//        pattern = Pattern.compile(exp)
//        matcher = pattern.matcher(str)
//        if (!matcher.matches()) {
//            valid = false
//        }

        // Password should contain at least one small letter
        exp = ".*[a-z].*"
        pattern = Pattern.compile(exp)
        matcher = pattern.matcher(str)
        if (!matcher.matches()) {
            valid = false
        }

        // Password should contain at least one special character
        // Allowed special characters : "~!@#$%^&*()-_=+|/,."';:{}[]<>?"
//        exp = ".*[~!@#\$%\\^&*()\\-_=+\\|\\[{\\]};:'\",<.>/?].*"
//        pattern = Pattern.compile(exp)
//        matcher = pattern.matcher(str)
//        if (!matcher.matches()) {
//            valid = false
//        }

        // Set error if required
        if (updateUI) {
            val error: String? = if (valid) null else PASSWORD_POLICY
            setError(data, error)
        }

        return valid
    }

    fun isPassword2Valid(data: Any, givenPassword: Any, updateUI: Boolean = true): Boolean {
        val str = getText(data)
        val givenPswd = getText(givenPassword)
        var valid = true

        if(!str.equals(givenPswd))
            valid = false

        if (updateUI) {
            val error: String? = if (valid) null else PASSWORD_POLICY
            setError(data, error)
        }

        return valid
    }

    /**
     * Sets error on EditText or TextInputLayout of the EditText.
     * data - Should be EditText
     * error - Message to be shown as error, can be null if no error is to be set
     */
    private fun setError(data: Any, error: String?) {
        if (data is EditText) {
            if (data.parent is TextInputLayout) {
                (data.parent as TextInputLayout).error = error
            } else {
                data.error = error
            }
        }
    }

    private fun getText(data: Any): String {
        var str = ""
        if (data is EditText) {
            str = data.text.toString()
        } else if (data is String) {
            str = data
        }
        return str
    }
}