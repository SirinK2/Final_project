package com.tuwaiq.finalproject.data.repo

import android.net.Uri
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.tuwaiq.finalproject.domain.repo.UserRepo
import com.tuwaiq.finalproject.domain.model.User
import kotlinx.coroutines.tasks.await
import java.util.*

private const val TAG = "UserRepoImpl"

class UserRepoImpl: UserRepo {

    private val userCollectionRef = Firebase.firestore.collection("users")

    override fun addUser(user: User) {
        val ref = userCollectionRef.document()
        user.id = ref.id
        ref.set(user)
    }

    override suspend fun getUser():List<User> {
       return userCollectionRef.get().await().toObjects(User::class.java)
    }

    override suspend fun updateUser(id: String, name: String, bio: String, photoUrl: String) {
        userCollectionRef.document(id).update("name",name,"bio", bio, "photoUrl", photoUrl).await()

    }

    override suspend fun uploadProfilePic(uri: Uri): String {
        var url = ""
        val fileName = "${UUID.randomUUID()}.jpg"
        val imgRef = Firebase.storage.reference.child("ProfilePic/$fileName")
        val task = imgRef.putFile(uri).continueWithTask { task ->
            if (!task.isSuccessful){
                task.exception?.let { e ->
                    throw e
                }
            }
            imgRef.downloadUrl

        }.await()
        url = task.toString()
        Log.e(TAG, "uploadProfilePic: $url", )
        return url

    }


}