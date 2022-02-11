package com.tuwaiq.finalproject.data.repo

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.ActionCodeSettings
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.tuwaiq.finalproject.domain.repo.AuthRepo

private const val TAG = "AuthRepoImpl"

class AuthRepoImpl: AuthRepo {

    private val auth = FirebaseAuth.getInstance()


    override fun register( email: String, password: String): Task<AuthResult> =
         auth.createUserWithEmailAndPassword(email, password)


    fun reSendPassword(email: String){
        auth.sendPasswordResetEmail(email)
    }





    override fun signIn(email: String, password: String):Task<AuthResult> =
        auth.signInWithEmailAndPassword(email, password)




}