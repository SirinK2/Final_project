package com.tuwaiq.finalproject.domain.use_case

import com.tuwaiq.finalproject.domain.model.User
import com.tuwaiq.finalproject.domain.repo.UserRepo
import javax.inject.Inject

class UserUpdateUseCase @Inject constructor(private val repo: UserRepo) {

    operator fun invoke(id:String,name: String, bio: String) = repo.updateUser(id,name, bio)

}