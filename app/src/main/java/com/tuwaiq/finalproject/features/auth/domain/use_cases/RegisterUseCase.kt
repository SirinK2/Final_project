package com.tuwaiq.finalproject.features.auth.domain.use_cases

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.tuwaiq.finalproject.features.auth.domain.repo.AuthRepo
import javax.inject.Inject


class RegisterUseCase @Inject constructor (
    private val repo: AuthRepo
) {

    operator fun invoke(username:String,email: String, password: String): Task<AuthResult> = repo.register(username,email,password)



}