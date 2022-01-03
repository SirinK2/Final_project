package com.tuwaiq.finalproject.presentation.post.preview

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.tuwaiq.finalproject.domain.model.Post
import com.tuwaiq.finalproject.domain.use_case.GetPostByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PreviewViewModel @Inject constructor(private val getPostByIdUseCase: GetPostByIdUseCase) : ViewModel() {


     fun getUserById(id: String): LiveData<Post?> = liveData { emit(getPostByIdUseCase(id)) }

}