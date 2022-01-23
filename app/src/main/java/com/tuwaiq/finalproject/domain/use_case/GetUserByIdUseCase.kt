package com.tuwaiq.finalproject.domain.use_case

import com.tuwaiq.finalproject.domain.model.User
import com.tuwaiq.finalproject.domain.repo.PostRepo
import com.tuwaiq.finalproject.domain.repo.UserRepo
import javax.inject.Inject

class GetUserByIdUseCase @Inject constructor(private val repo: UserRepo){

    suspend operator fun invoke(id:String): User {
        var user = User()
        repo.getUser().forEach {

            if (id == it.authId)
                    user = it
        }
        return user
    }
}