package com.tuwaiq.finalproject.core.data.repo

import com.tuwaiq.finalproject.core.data.firebase.User

interface UserRepo {

    suspend fun saveUser(user: User)

}