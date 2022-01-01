package com.tuwaiq.finalproject.presentation.post.preview

import androidx.lifecycle.ViewModel
import com.tuwaiq.finalproject.domain.model.Post
import com.tuwaiq.finalproject.domain.use_case.GetPostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PreviewViewModel @Inject constructor(private val getPostUseCase: GetPostUseCase) : ViewModel() {


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