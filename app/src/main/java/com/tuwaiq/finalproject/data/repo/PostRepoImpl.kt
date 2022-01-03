package com.tuwaiq.finalproject.data.repo

import android.net.Uri
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.tuwaiq.finalproject.data.remote.dto.PostDto
import com.tuwaiq.finalproject.domain.model.Post
import com.tuwaiq.finalproject.domain.repo.PostRepo
import com.tuwaiq.finalproject.util.Constant.postCollectionRef
import kotlinx.coroutines.tasks.await
import java.util.*

private const val TAG = "PostRepoImpl"

class PostRepoImpl : PostRepo {


    override fun addPost(post: Post) {
        val ref = postCollectionRef.document()
        post.id = ref.id
        ref.set(post)
    }

    
    override suspend fun getPost(): List<PostDto> =
        postCollectionRef.get().await().toObjects(PostDto::class.java)




    override suspend fun uploadImage(uri: List<Uri>): List<String>{
        val uriList: MutableList<String> = mutableListOf()
        uri.forEach {
            val fileName = "${UUID.randomUUID()}.jpg"
            val imgRef = Firebase.storage.reference.child("images/$fileName")

            val uriTask= imgRef.putFile(it).continueWithTask { task ->
                if (!task.isSuccessful){
                    task.exception?.let { e ->
                        throw e
                    }
                }
                imgRef.downloadUrl

            }.await()
            uriList += uriTask.toString()

        }
        return uriList
    }


}