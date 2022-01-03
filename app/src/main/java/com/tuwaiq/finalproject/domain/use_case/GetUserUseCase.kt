package com.tuwaiq.finalproject.domain.use_case

import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.tuwaiq.finalproject.domain.model.User
import com.tuwaiq.finalproject.domain.repo.UserRepo
import javax.inject.Inject

private const val TAG = "GetUserUseCase"
class GetUserUseCase @Inject constructor(val repo: UserRepo) {

    suspend operator fun invoke(): User {
        var user = User()
        repo.getUser().forEach {
            val userId = Firebase.auth.currentUser?.uid
            if (it.authId == userId){
                user = it
            }
        }
        return user
    }
}