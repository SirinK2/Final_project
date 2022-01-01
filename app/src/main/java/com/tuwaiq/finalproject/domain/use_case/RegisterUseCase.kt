package com.tuwaiq.finalproject.domain.use_case

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.tuwaiq.finalproject.domain.repo.AuthRepo
import javax.inject.Inject


class RegisterUseCase @Inject constructor (
    private val repo: AuthRepo
) {

    operator fun invoke(username:String,email: String, password: String): Task<AuthResult> = repo.register(username,email,password)



}