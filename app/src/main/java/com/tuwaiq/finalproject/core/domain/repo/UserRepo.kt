package com.tuwaiq.finalproject.core.domain.repo

import com.tuwaiq.finalproject.core.data.remote.dto.UserDto
import com.tuwaiq.finalproject.core.domain.model.User

interface UserRepo {

    suspend fun saveUser(user: User)

}