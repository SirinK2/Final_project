package com.tuwaiq.finalproject.presentation.auth.presentation.register

import androidx.lifecycle.ViewModel
import com.tuwaiq.finalproject.presentation.auth.domain.use_cases.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor (
        private val registerUseCase: RegisterUseCase
        ) : ViewModel() {


        fun register(username:String,email: String, password: String) = registerUseCase(username,email, password)





}