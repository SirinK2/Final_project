package com.tuwaiq.finalproject.features.post.presentation.newPost


import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tuwaiq.finalproject.core.data.firebase.Post
import com.tuwaiq.finalproject.features.post.domain.use_cases.GetLocationUseCase
import com.tuwaiq.finalproject.features.post.domain.use_cases.SavePostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemsViewModel @Inject constructor(
    val savePostUseCase: SavePostUseCase,
    val getLocationUseCase: GetLocationUseCase,
) :ViewModel() {



    suspend fun savePost(post: Post) = viewModelScope.launch(Dispatchers.IO) { savePostUseCase(post) }


    fun getLocation(context: Context, title: String, description: String, price: String) =
        viewModelScope.launch (Dispatchers.IO){ getLocationUseCase(context, title, description, price) }













}