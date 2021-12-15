package com.tuwaiq.finalproject.presentation.profile.domain.user_cases

import com.tuwaiq.finalproject.core.data.repo.UserRepo
import com.tuwaiq.finalproject.core.data.firebase.User

class SaveUserUseCase(private val userRepo: UserRepo) {

    suspend operator fun invoke(user: User) = userRepo.saveUser(user)

}