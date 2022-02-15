package com.tuwaiq.finalproject.presentation.auth.register


import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RegisterFragmentTest {


    @Test
    fun validateUsernameIsEmptyReturnFalse(){
        val result =  RegisterTest.validateRegistration(
            "",
            "seren@gmail.com",
            "1234567",
            "1234567"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun validateEmailIsEmptyReturnFalse(){
        val result =  RegisterTest.validateRegistration(
            "Sereen",
            "",
            "1234567",
            "1234567"
        )
        assertThat(result).isFalse()
    }


    @Test
    fun validatePasswordIsMatchedReturnFalse() {
       val result =  RegisterTest.validateRegistration(
            "Sereen",
            "sereen@gmail.com",
            "1234587",
            "1234567"
        )

        assertThat(result).isFalse()

    }



}