package com.tuwaiq.finalproject.domain.repo

import com.tuwaiq.finalproject.domain.model.User

interface UserRepo {

    suspend fun saveUser(user: User)

}