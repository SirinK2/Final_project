package com.tuwaiq.finalproject.presentation.profile.myProfile

import androidx.lifecycle.ViewModel
import com.tuwaiq.finalproject.domain.model.User
import com.tuwaiq.finalproject.domain.use_case.UserUpdateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(val userUpdateUseCase: UserUpdateUseCase) : ViewModel() {


    fun updateUser(id:String, name: String, bio: String) = userUpdateUseCase(id,name, bio)

}