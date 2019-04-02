package com.zpi.eventon


import com.zpi.eventon.Validation.DataValidator
import junit.framework.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class DataValidatorUnitTest {

    lateinit var tested: DataValidator
    lateinit var name: String
    lateinit var email: String
    lateinit var login: String
    lateinit var password: String

    @Before
    fun setUp() {
        tested = DataValidator()
    }

    @Test
    fun wrongNameEmpty() {
        name = ""
        assertFalse(tested.isNameValid(name))
    }

    @Test
    fun wrongNameOneChar() {
        name = "a"
        assertFalse(tested.isNameValid(name))
    }

    @Test
    fun correctName() {
        name = "mon"
        assertTrue(tested.isNameValid(name))
    }


    @Test
    fun wrongEmail() {
        email = "email"
        assertFalse(tested.isEmailValid(email, false))
    }

    @Test
    fun wrongEmailEmptyPost() {
        email = "@email"
        assertFalse(tested.isEmailValid(email, false))
    }

    @Test
    fun wrongEmailEmpty(){
        email = "email.com"
        assertFalse(tested.isEmailValid(email, false))
    }

    @Test
    fun wrongEmailEmptyName() {
        email = "@email.com"
        assertFalse(tested.isEmailValid(email, false))
    }

    @Test
    fun correctEmail() {
        email = "email@email.com"
        assertTrue(tested.isEmailValid(email))
    }

    @Test
    fun wrongLogin() {
        login = ""
        assertFalse(tested.isLoginValid(login))
    }

    @Test
    fun wrongLoginOneChar() {
        login = "l"
        assertFalse(tested.isLoginValid(login))
    }

    @Test
    fun wrongLoginTwoChars() {
        login = "lo"
        assertFalse(tested.isLoginValid(login))
    }

    @Test
    fun correctLogin() {
        login = "log"
        assertTrue(tested.isLoginValid(login))
    }

    @Test
    fun wrongPasswordOneChar() {
        password = "p"
        assertFalse(tested.isPasswordValid(password, false))
    }

    @Test
    fun wrongPasswordNoNumb() {
        password = "passwordo"
        assertFalse(tested.isPasswordValid(password, false))
    }

    @Test
    fun wrongPasswordTooShort() {
        password = "passw1"
        assertFalse(tested.isPasswordValid(password, false))
    }

    @Test
    fun wrongPasswordOnlyNumbs() {
        password = "12345678"
        assertFalse(tested.isPasswordValid(password, false))
    }

    @Test
    fun correctPassword() {
        password = "password1"
        assertTrue(tested.isPasswordValid(password, false))
    }
}