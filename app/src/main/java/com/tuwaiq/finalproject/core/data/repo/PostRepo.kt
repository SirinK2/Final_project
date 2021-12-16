package com.tuwaiq.finalproject.core.data.repo

import android.content.Context
import android.location.Location
import com.tuwaiq.finalproject.core.data.firebase.CurrentLocation
import com.tuwaiq.finalproject.core.data.firebase.Post
import dagger.hilt.android.qualifiers.ApplicationContext

interface PostRepo {

    suspend fun savePost(post: Post)

     suspend fun getLocation(@ApplicationContext context: Context, title: String, description: String, price: String)


}