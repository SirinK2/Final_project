package com.tuwaiq.finalproject.core.domain.repo

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.tuwaiq.finalproject.core.data.repo.UserRepo
import com.tuwaiq.finalproject.core.data.firebase.User
import kotlinx.coroutines.tasks.await
import java.lang.Exception

private const val TAG = "UserRepoImpl"

class UserRepoImpl:UserRepo {

    private val userCollectionRef = Firebase.firestore.collection("users")

    override suspend fun saveUser(user: User) {
        try {
            userCollectionRef.add(user).await()
            Log.d(TAG," it's added to firestore")

        } catch (e: Exception){
            Log.d(TAG,"HIIiiiiiiii" , e)
        }


    }
}