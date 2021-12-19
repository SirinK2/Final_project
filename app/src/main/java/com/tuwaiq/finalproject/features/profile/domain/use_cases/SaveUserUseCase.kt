package com.tuwaiq.finalproject.features.profile.domain.use_cases

import com.tuwaiq.finalproject.core.domain.repo.UserRepo
import com.tuwaiq.finalproject.core.data.remote.dto.UserDto
import javax.inject.Inject

class SaveUserUseCase @Inject constructor(private val userRepo: UserRepo) {

    suspend operator fun invoke(user: UserDto) = userRepo.saveUser(user)

}