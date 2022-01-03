package com.tuwaiq.finalproject.presentation.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.tuwaiq.finalproject.domain.model.User
import com.tuwaiq.finalproject.domain.use_case.RegisterUseCase
import com.tuwaiq.finalproject.domain.use_case.SaveUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor (
        private val registerUseCase: RegisterUseCase,
        private val saveUserUseCase: SaveUserUseCase
        ) : ViewModel() {


        fun register(email: String, password: String): Task<AuthResult> = registerUseCase(email, password)

        fun saveUser(user: User) =  saveUserUseCase(user)





}