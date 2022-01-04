package com.tuwaiq.finalproject.domain.use_case

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.tuwaiq.finalproject.domain.repo.AuthRepo
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val repo: AuthRepo
) {

    operator fun invoke(email:String, password:String): Task<AuthResult> = repo.signIn(email, password)

}