package com.tuwaiq.finalproject.presentation.post.preview

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.tuwaiq.finalproject.domain.model.Post
import com.tuwaiq.finalproject.domain.model.User
import com.tuwaiq.finalproject.domain.use_case.GetPostByIdUseCase
import com.tuwaiq.finalproject.domain.use_case.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class PreviewViewModel @Inject constructor(
     private val getPostByIdUseCase: GetPostByIdUseCase,
     private val getUserUseCase: GetUserUseCase
     ) : ViewModel() {


     fun getPostById(id: String): LiveData<Post?> = liveData { emit(getPostByIdUseCase(id)) }

     fun getUser():LiveData<User> = liveData(Dispatchers.IO) { emit(getUserUseCase()) }

}