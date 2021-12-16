package com.tuwaiq.finalproject.features.auth.domain.repo

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.tuwaiq.finalproject.features.auth.data.repo.AuthRepo

private const val TAG = "AuthRepoImpl"

class AuthRepoImpl: AuthRepo {

    private val auth = FirebaseAuth.getInstance()

    override fun register(username:String, email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful){
                    Log.d(TAG,"register is success")
                }else{
                    Log.d(TAG,"failed", task.exception)
                }

            }

    }

    override fun singIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->

                if (task.isSuccessful){
                    Log.d(TAG,"login is success")
                }else{
                    Log.d(TAG,"failed", task.exception)
                }
            }
    }


}