package com.tuwaiq.finalproject.presentation.auth.domain.use_cases

import com.tuwaiq.finalproject.presentation.auth.data.repo.AuthRepo


class RegisterUseCase (
    private val repo: AuthRepo
) {

    operator fun invoke(username:String,email: String, password: String) = repo.register(username,email,password)


}