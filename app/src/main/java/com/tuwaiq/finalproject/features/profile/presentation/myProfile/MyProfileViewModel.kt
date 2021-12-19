package com.tuwaiq.finalproject.features.profile.presentation.myProfile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tuwaiq.finalproject.core.data.remote.dto.UserDto
import com.tuwaiq.finalproject.features.profile.domain.use_cases.SaveUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyProfileViewModel@Inject constructor(
    val saveUserUseCase: SaveUserUseCase
) : ViewModel() {

     fun saveUser(user: UserDto) = viewModelScope.launch { saveUserUseCase(user) }


}