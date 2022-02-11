package com.tuwaiq.finalproject.domain.use_case

import android.util.Log
import com.tuwaiq.finalproject.domain.model.User
import com.tuwaiq.finalproject.domain.repo.UserRepo
import com.tuwaiq.finalproject.util.Constant.userCollectionRef
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

private const val TAG = "GetAllUsersUseCase"
class GetAllUsersUseCase @Inject constructor(private val repo: UserRepo) {

    suspend operator fun invoke(id: List<String>):List<User> {
        val user = mutableListOf<User>()
        userCollectionRef
            .get().await().toObjects(User::class.java).forEach {
                if (id.contains(it.authId)){
                    user += it
                    Log.e(TAG, "invoke: $user", )
                }
                }




        return  user

    }





}