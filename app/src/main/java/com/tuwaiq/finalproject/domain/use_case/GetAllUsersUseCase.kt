package com.tuwaiq.finalproject.domain.use_case

import com.tuwaiq.finalproject.domain.model.User
import com.tuwaiq.finalproject.domain.repo.UserRepo
import javax.inject.Inject

class GetAllUsersUseCase @Inject constructor(private val repo: UserRepo) {

    suspend operator fun invoke():List<User> = repo.getUser()



}