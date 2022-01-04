package com.tuwaiq.finalproject.presentation.auth.signIn

import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.tuwaiq.finalproject.domain.use_case.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
     private val singInUseCase: SignInUseCase
): ViewModel() {

    fun signIn(email:String, password: String): Task<AuthResult> = singInUseCase(email,password)


}