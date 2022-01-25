package com.tuwaiq.finalproject.domain.use_case

import android.util.Log
import com.tuwaiq.finalproject.domain.model.Chat
import com.tuwaiq.finalproject.domain.repo.ChatRepo
import com.tuwaiq.finalproject.util.Constant.uid
import com.tuwaiq.finalproject.util.FirebaseCallback
import javax.inject.Inject

private const val TAG = "GetMessagesUseCase"
class GetMessagesUseCase @Inject constructor(private val repo: ChatRepo) {

    suspend operator fun invoke(callback: FirebaseCallback) =
        repo.getMessages(callback)

}