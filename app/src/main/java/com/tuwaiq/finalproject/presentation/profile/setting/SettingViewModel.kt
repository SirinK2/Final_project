package com.tuwaiq.finalproject.presentation.profile.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tuwaiq.finalproject.domain.model.User
import com.tuwaiq.finalproject.domain.use_case.AddUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    val addUserUseCase: AddUserUseCase
) : ViewModel() {


    suspend fun saveUser(user: User) = viewModelScope.launch { addUserUseCase(user) }




}