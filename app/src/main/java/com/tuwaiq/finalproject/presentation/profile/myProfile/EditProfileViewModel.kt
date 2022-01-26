package com.tuwaiq.finalproject.presentation.profile.myProfile

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.tuwaiq.finalproject.domain.model.User
import com.tuwaiq.finalproject.domain.use_case.GetCurrentUserUseCase
import com.tuwaiq.finalproject.domain.use_case.UploadProfilePicUseCase
import com.tuwaiq.finalproject.domain.use_case.UserUpdateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val userUpdateUseCase: UserUpdateUseCase,
    private val uploadProfilePicUseCase: UploadProfilePicUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase

    ) : ViewModel() {


    fun updateUser(
        id:String,
        name: String,
        bio: String,
        photoUrl: String
    ) =
        viewModelScope.launch(Dispatchers.IO) {
        userUpdateUseCase(id,name, bio,photoUrl)
    }

    fun uploadImg(uri: Uri): LiveData<String> =
        liveData(Dispatchers.IO) { emit(uploadProfilePicUseCase(uri)) }



    fun getUserInfo():LiveData<User> =
        liveData(Dispatchers.IO) { emit(getCurrentUserUseCase()) }


}