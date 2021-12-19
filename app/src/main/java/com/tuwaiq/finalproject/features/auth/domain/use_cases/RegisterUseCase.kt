package com.tuwaiq.finalproject.features.auth.domain.use_cases

import com.tuwaiq.finalproject.features.auth.domain.repo.AuthRepo
import javax.inject.Inject


class RegisterUseCase @Inject constructor (
    private val repo: AuthRepo
) {

    operator fun invoke(username:String,email: String, password: String) = repo.register(username,email,password)


}