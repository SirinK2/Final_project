package com.tuwaiq.finalproject.core.domain.repo

import com.tuwaiq.finalproject.core.data.remote.dto.UserDto

interface UserRepo {

    suspend fun saveUser(user: UserDto)

}