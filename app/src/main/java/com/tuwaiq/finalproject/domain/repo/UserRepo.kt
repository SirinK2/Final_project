package com.tuwaiq.finalproject.domain.repo

import com.tuwaiq.finalproject.domain.model.User

interface UserRepo {

    fun saveUser(user: User)

    suspend fun getUser():List<User>

}