package com.tuwaiq.finalproject.presentation.auth.domain.use_cases

import com.tuwaiq.finalproject.presentation.auth.data.repo.AuthRepo

class SingInUseCase(
    private val repo: AuthRepo
) {

    operator fun invoke(email:String, password:String) = repo.singIn(email, password)

}