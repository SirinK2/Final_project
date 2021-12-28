package com.tuwaiq.finalproject.features.post.presentation.preview

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.tuwaiq.finalproject.core.domain.model.Post
import com.tuwaiq.finalproject.core.domain.use_case.GetPostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class PreviewViewModel @Inject constructor(private val getPostUseCase: GetPostUseCase) : ViewModel() {

    fun getPost(context: Context, dist: Float): LiveData<List<Post>> = liveData(Dispatchers.IO) {
        emit(getPostUseCase(context, dist))
    }

    var post: Post? = null
        set(post) {
            field = post
        }

    val title: String?
        get() = post?.title

    val description: String?
        get() = post?.description

    val price: String?
        get() = post?.price



}