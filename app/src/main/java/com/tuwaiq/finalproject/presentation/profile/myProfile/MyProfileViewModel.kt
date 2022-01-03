package com.tuwaiq.finalproject.presentation.profile.myProfile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.tuwaiq.finalproject.domain.model.Post
import com.tuwaiq.finalproject.domain.model.User
import com.tuwaiq.finalproject.domain.use_case.GetUserUseCase
import com.tuwaiq.finalproject.domain.use_case.MyPostUseCase
import com.tuwaiq.finalproject.domain.use_case.SaveUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "MyProfileViewModel"
@HiltViewModel
class MyProfileViewModel@Inject constructor(
    val getUserUseCase: GetUserUseCase,
    val getMyPostUseCase: MyPostUseCase
) : ViewModel() {


    fun getUser():LiveData<User> = liveData{
        emit(getUserUseCase())
    }


    fun myPost():LiveData<List<Post>> = liveData {
        emit(getMyPostUseCase())
    }



}