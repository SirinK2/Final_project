package com.tuwaiq.finalproject.features.auth.presentation.singIn

import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.tuwaiq.finalproject.features.auth.domain.use_cases.SingInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SingInViewModel @Inject constructor(
     private val singInUseCase: SingInUseCase
): ViewModel() {

    fun singIn(email:String, password: String): Task<AuthResult> = singInUseCase(email,password)


}