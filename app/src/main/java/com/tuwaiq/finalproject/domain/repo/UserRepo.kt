package com.tuwaiq.finalproject.domain.repo

import android.net.Uri
import com.tuwaiq.finalproject.domain.model.User

interface UserRepo {

    fun saveUser(user: User)

    suspend fun getUser():List<User>

    fun updateUser(id:String, name: String, bio: String,photoUrl: String)

    suspend fun uploadProfilePic(uri: Uri):String

}