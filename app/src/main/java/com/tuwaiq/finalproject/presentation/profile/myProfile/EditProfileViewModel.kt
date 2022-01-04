package com.tuwaiq.finalproject.presentation.profile.myProfile

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.tuwaiq.finalproject.domain.model.User
import com.tuwaiq.finalproject.domain.use_case.UploadImgUseCase
import com.tuwaiq.finalproject.domain.use_case.UploadProfilePicUseCase
import com.tuwaiq.finalproject.domain.use_case.UserUpdateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val userUpdateUseCase: UserUpdateUseCase,
    private val uploadProfilePicUseCase: UploadProfilePicUseCase

    ) : ViewModel() {


    fun updateUser(id:String, name: String, bio: String, photoUrl: String) = viewModelScope.launch(Dispatchers.IO) {
        userUpdateUseCase(id,name, bio,photoUrl)
    }

    fun uploadImg(uri: Uri): LiveData<String> = liveData { emit(uploadProfilePicUseCase(uri)) }


}