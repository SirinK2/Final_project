package com.tuwaiq.finalproject.domain.use_case

import com.tuwaiq.finalproject.domain.model.User
import com.tuwaiq.finalproject.domain.repo.UserRepo
import javax.inject.Inject

class AddUserUseCase @Inject constructor(private val userRepo: UserRepo) {

     operator fun invoke(user: User) = userRepo.addUser(user)

}