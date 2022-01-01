package com.tuwaiq.finalproject.features.auth.domain.repo

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

interface AuthRepo {


    fun register(username:String,email:String, password: String): Task<AuthResult>

    fun singIn(email: String,password: String):Task<AuthResult>


}