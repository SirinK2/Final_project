package com.tuwaiq.finalproject.presentation.post.newPost


import android.content.Context
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.tuwaiq.finalproject.domain.use_case.AddPostUseCase
import com.tuwaiq.finalproject.domain.use_case.UploadImgUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "ItemsViewModel"
@HiltViewModel
class ItemsViewModel @Inject constructor(
    private val addPostUseCase: AddPostUseCase,
    private val uploadImgUseCase: UploadImgUseCase
) :ViewModel() {



    fun addPost(context: Context, category: String, title: String, description: String, price: String,photoUrl: List<String>) =
        viewModelScope.launch (Dispatchers.IO){ addPostUseCase(context,category, title, description, price,photoUrl) }


    fun uploadImg(uri: List<Uri>): LiveData<List<String>> = liveData { emit(uploadImgUseCase(uri)) }


}