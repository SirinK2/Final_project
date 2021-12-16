package com.tuwaiq.finalproject.features.auth.domain.use_cases

import com.tuwaiq.finalproject.features.auth.data.repo.AuthRepo
import javax.inject.Inject

class SingInUseCase @Inject constructor(
    private val repo: AuthRepo
) {

    operator fun invoke(email:String, password:String) = repo.singIn(email, password)

}