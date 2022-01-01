package com.tuwaiq.finalproject.domain.repo

import android.content.Context
import android.net.Uri
import com.google.firebase.storage.UploadTask
import com.tuwaiq.finalproject.domain.model.Post
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