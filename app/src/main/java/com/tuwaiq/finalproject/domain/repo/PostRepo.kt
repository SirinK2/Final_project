package com.tuwaiq.finalproject.domain.repo

import android.content.Context
import android.net.Uri
import androidx.lifecycle.LiveData
import com.google.firebase.storage.UploadTask
import com.tuwaiq.finalproject.data.remote.dto.PaymentDto
import com.tuwaiq.finalproject.data.remote.dto.PostDto
import com.tuwaiq.finalproject.domain.model.Payment
import com.tuwaiq.finalproject.domain.model.Post
import dagger.hilt.android.qualifiers.ApplicationContext

interface PostRepo {


    fun addPost(post: Post)

    suspend fun uploadImage(uri: List<Uri>): List<String>

    suspend fun getPost():List<PostDto>

    fun addPayment(payment: Payment)

    fun deletePost(id:String)

    suspend fun getPayments():List<Payment>






}