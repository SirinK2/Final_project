package com.tuwaiq.finalproject.domain.repo

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

interface AuthRepo {


    fun register(email:String, password: String): Task<AuthResult>

    fun signIn(email: String, password: String):Task<AuthResult>


}