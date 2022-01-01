package com.tuwaiq.finalproject.domain.use_case

import com.tuwaiq.finalproject.domain.model.User
import com.tuwaiq.finalproject.domain.repo.UserRepo
import javax.inject.Inject

class SaveUserUseCase @Inject constructor(private val userRepo: UserRepo) {

    suspend operator fun invoke(user: User) = userRepo.saveUser(user)

}