package com.tuwaiq.finalproject.presentation.profile.myProfile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.tuwaiq.finalproject.domain.model.Post
import com.tuwaiq.finalproject.domain.model.User
import com.tuwaiq.finalproject.domain.use_case.GetCurrentUserUseCase
import com.tuwaiq.finalproject.domain.use_case.MyPostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

private const val TAG = "MyProfileViewModel"
@HiltViewModel
class MyProfileViewModel@Inject constructor(
    val getCurrentUserUseCase: GetCurrentUserUseCase,
    val getMyPostUseCase: MyPostUseCase
) : ViewModel() {


    fun getUser():LiveData<User> = liveData{
        emit(getCurrentUserUseCase())
    }


    fun myPost():LiveData<List<Post>> = liveData {
        emit(getMyPostUseCase())
    }



}