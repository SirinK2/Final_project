package com.tuwaiq.finalproject.features.auth.data.repo

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.tuwaiq.finalproject.features.auth.domain.repo.AuthRepo

private const val TAG = "AuthRepoImpl"

class AuthRepoImpl: AuthRepo {

    private val auth = FirebaseAuth.getInstance()


    override fun register(username:String, email: String, password: String): Task<AuthResult> =
         auth.createUserWithEmailAndPassword(email, password)





    override fun singIn(email: String, password: String):Task<AuthResult> =
        auth.signInWithEmailAndPassword(email, password)




}