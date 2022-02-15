package com.tuwaiq.finalproject.presentation.auth.register


object RegisterTest {
    fun validateRegistration(userName: String, email:String, password: String, confirmPass:String): Boolean {

        when {
            userName.trim().isEmpty() -> return false
            email.trim().isEmpty() -> return false
            password.trim().isEmpty() -> return false
            password.length !in 7..15 -> return false
            password != confirmPass -> return false

        }
        return true
    }

}