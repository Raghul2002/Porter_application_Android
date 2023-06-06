package com.example.porter01.utility

import java.util.regex.Matcher
import java.util.regex.Pattern

object TextValidation {
     private fun validate(regex: String, string: String?): Boolean {
        val p: Pattern = Pattern.compile(regex)
        if (string == null) {
            return false
        }
        val m: Matcher = p.matcher(string)
        return m.matches()
    }
    fun validateFirstName(string: String?): Boolean {
        val regex = "[a-zA-Z\\s]{3,40}$"
        return validate(regex, string)
    }
    fun validateLastName(string: String?): Boolean {
        val regex = "[a-zA-Z\\s]{1,40}$"
        return validate(regex, string)
    }
    fun validatePhoneNo(number: String?): Boolean {
        val regex = "[0-9]{10}$"
        return validate(regex, number)
    }
    fun validateEmail(email: String?): Boolean {
        val regex = "[a-z0-9]+@[a-z]+\\.[a-z]{2,3}$"
        return validate(regex, email)
    }
    fun validatePassword(password: String?): Boolean {
        val regex =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#\\$%\\^&\\*])[a-zA-Z\\d!@#\\$%\\^&\\*]{8,}$"
        return validate(regex, password)
    }
    fun validateUserName(userName: String?): Boolean {
        val regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{5,}$"
        return validate(regex, userName)
    }
}