package com.tuwaiq.finalproject.core.domain.repo

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.tuwaiq.finalproject.core.data.firebase.Post
import com.tuwaiq.finalproject.core.data.repo.PostRepo
import java.lang.Exception

private const val TAG = "PostRepoImpl"
class PostRepoImpl :PostRepo{
    private val postCollectionRef = Firebase.firestore.collection("post")

    override suspend fun savePost(post: Post) {
        try {
            postCollectionRef.add(post)
        }catch (e: Exception){
            Log.d(TAG,"from post repo impl", e)
        }
    }
}