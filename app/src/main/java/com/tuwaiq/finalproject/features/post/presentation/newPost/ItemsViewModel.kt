package com.tuwaiq.finalproject.features.post.presentation.newPost


import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tuwaiq.finalproject.core.data.remote.dto.PostDto
import com.tuwaiq.finalproject.core.domain.model.Post
import com.tuwaiq.finalproject.core.domain.use_case.AddPostUseCase
import com.tuwaiq.finalproject.core.domain.use_case.UploadImgUseCase
import com.tuwaiq.finalproject.features.post.domain.use_cases.SavePostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemsViewModel @Inject constructor(
    private val addPostUseCase: AddPostUseCase,
    private val uploadImgUseCase: UploadImgUseCase
) :ViewModel() {



//     fun savePost(post: Post) = viewModelScope.launch(Dispatchers.IO) { savePostUseCase(post) }


    fun addPost(context: Context, category: String, title: String, description: String, price: String) =
        viewModelScope.launch (Dispatchers.IO){ addPostUseCase(context,category, title, description, price) }


//    fun uploadImg() = viewModelScope.launch(Dispatchers.IO) { uploadImgUseCase() }

}