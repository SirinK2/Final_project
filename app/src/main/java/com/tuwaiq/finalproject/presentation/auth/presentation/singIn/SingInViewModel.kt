package com.tuwaiq.finalproject.presentation.auth.presentation.singIn

import androidx.lifecycle.ViewModel
import com.tuwaiq.finalproject.presentation.auth.domain.use_cases.SingInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SingInViewModel @Inject constructor(
     private val singInUseCase: SingInUseCase
): ViewModel() {

    fun singIn(email:String, password: String) = singInUseCase(email,password)


}