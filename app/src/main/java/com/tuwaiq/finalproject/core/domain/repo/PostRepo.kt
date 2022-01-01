package com.tuwaiq.finalproject.core.domain.repo

import android.content.Context
import android.location.Location
import android.net.Uri
import androidx.lifecycle.LiveData
import com.google.firebase.storage.UploadTask
import com.tuwaiq.finalproject.core.data.remote.dto.PostDto
import com.tuwaiq.finalproject.core.domain.model.Post
import dagger.hilt.android.qualifiers.ApplicationContext

interface PostRepo {


    suspend fun savePost(@ApplicationContext context: Context ,
                         category: String,
                         title: String,
                         description: String,
                         price: String)

    suspend fun addPost(post: Post)


    suspend fun uploadImage(uri: Uri): UploadTask.TaskSnapshot


    suspend fun getPost(@ApplicationContext context: Context,dist: Float):List<Post>





}