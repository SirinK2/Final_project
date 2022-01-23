package com.tuwaiq.finalproject.presentation.auth.register

import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.tuwaiq.finalproject.domain.model.User
import com.tuwaiq.finalproject.domain.use_case.RegisterUseCase
import com.tuwaiq.finalproject.domain.use_case.AddUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
        private val registerUseCase: RegisterUseCase,
        private val addUserUseCase: AddUserUseCase
        ) : ViewModel() {


        fun register(
                email: String,
                password: String
        ): Task<AuthResult> =
                registerUseCase(email, password)



        fun addUser(user: User) =  addUserUseCase(user)





}