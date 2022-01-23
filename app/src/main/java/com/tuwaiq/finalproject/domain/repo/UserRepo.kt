package com.tuwaiq.finalproject.domain.repo

import android.net.Uri
import com.tuwaiq.finalproject.domain.model.User

interface UserRepo {

    fun addUser(user: User)

    suspend fun getUser():List<User>

    suspend fun updateUser(id:String, name: String, bio: String,photoUrl: String)

    suspend fun uploadProfilePic(uri: Uri):String

}