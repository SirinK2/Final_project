package com.tuwaiq.finalproject.presentation.profile.myProfile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.tuwaiq.finalproject.domain.model.Post
import com.tuwaiq.finalproject.domain.model.User
import com.tuwaiq.finalproject.domain.use_case.MyPostUseCase
import com.tuwaiq.finalproject.domain.use_case.SaveUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyProfileViewModel@Inject constructor(
    val saveUserUseCase: SaveUserUseCase,
    val getMyPostUseCase: MyPostUseCase
) : ViewModel() {

     fun saveUser(user: User) = viewModelScope.launch { saveUserUseCase(user) }

    fun myPost():LiveData<List<Post>> = liveData {
        emit(getMyPostUseCase())
    }



}