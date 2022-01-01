package com.tuwaiq.finalproject.features.profile.presentation.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tuwaiq.finalproject.core.data.remote.dto.UserDto
import com.tuwaiq.finalproject.core.domain.model.User
import com.tuwaiq.finalproject.features.profile.domain.use_cases.SaveUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    val saveUserUseCase: SaveUserUseCase
) : ViewModel() {


    suspend fun saveUser(user: User) = viewModelScope.launch { saveUserUseCase(user) }


}