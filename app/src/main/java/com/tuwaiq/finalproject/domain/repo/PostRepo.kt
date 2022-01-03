package com.tuwaiq.finalproject.domain.repo

import android.content.Context
import android.net.Uri
import androidx.lifecycle.LiveData
import com.google.firebase.storage.UploadTask
import com.tuwaiq.finalproject.domain.model.Post
import dagger.hilt.android.qualifiers.ApplicationContext

interface PostRepo {


    suspend fun savePost(@ApplicationContext context: Context ,
                         category: String,
                         title: String,
                         description: String,
                         price: String,
                         photoUrl: List<String>)

    suspend fun addPost(post: Post)


    suspend fun uploadImage(uri: List<Uri>): List<String>


    suspend fun getPost(@ApplicationContext context: Context,dist: Float):List<Post>

    suspend fun getMyPost():List<Post>

    suspend fun getPostById(id: String): Post





}