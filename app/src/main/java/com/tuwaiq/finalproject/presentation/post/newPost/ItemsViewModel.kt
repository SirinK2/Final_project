package com.tuwaiq.finalproject.presentation.post.newPost


import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tuwaiq.finalproject.domain.use_case.AddPostUseCase
import com.tuwaiq.finalproject.domain.use_case.UploadImgUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class ItemsViewModel @Inject constructor(
    private val addPostUseCase: AddPostUseCase,
    private val uploadImgUseCase: UploadImgUseCase
) :ViewModel() {

    private lateinit var uploadTask:String


//     fun savePost(post: Post) = viewModelScope.launch(Dispatchers.IO) { savePostUseCase(post) }


    fun addPost(context: Context, category: String, title: String, description: String, price: String) =
        viewModelScope.launch (Dispatchers.IO){ addPostUseCase(context,category, title, description, price) }


    fun uploadImg(uri: Uri) {

        viewModelScope.launch(Dispatchers.IO) {
            val upload = uploadImgUseCase(uri)
            if (upload.task.isSuccessful){
                uploadTask = upload.storage.downloadUrl.await().toString()

            }
        }


    }

}