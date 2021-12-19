package com.tuwaiq.finalproject.core.domain.repo

import android.content.Context
import com.tuwaiq.finalproject.core.data.remote.dto.PostDto
import dagger.hilt.android.qualifiers.ApplicationContext

interface PostRepo {

//    suspend fun items(items: Items)

    suspend fun savePost(@ApplicationContext context: Context ,category: String,title: String, description: String, price: String)

    suspend fun addPost(post: PostDto)


}