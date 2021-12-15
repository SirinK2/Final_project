package com.tuwaiq.finalproject.presentation.profile.presentation.myProfile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tuwaiq.finalproject.core.data.firebase.User
import com.tuwaiq.finalproject.presentation.profile.domain.user_cases.SaveUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyProfileViewModel@Inject constructor(
    val saveUserUseCase: SaveUserUseCase
) : ViewModel() {

     fun saveUser(user: User) = viewModelScope.launch { saveUserUseCase(user) }


}