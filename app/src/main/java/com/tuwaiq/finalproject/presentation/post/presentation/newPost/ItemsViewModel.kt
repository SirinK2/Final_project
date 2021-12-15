package com.tuwaiq.finalproject.presentation.post.presentation.newPost

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tuwaiq.finalproject.core.data.firebase.Post
import com.tuwaiq.finalproject.core.data.repo.PostRepo
import com.tuwaiq.finalproject.presentation.post.domain.use_cases.SavePostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemsViewModel @Inject constructor(
    val savePostUseCase: SavePostUseCase
) : ViewModel() {

    fun savePost(post: Post) = viewModelScope.launch(Dispatchers.IO) { savePostUseCase(post) }



}