package com.tuwaiq.finalproject.domain.repo

import com.tuwaiq.finalproject.domain.model.Chat
import com.tuwaiq.finalproject.util.FirebaseCallback

interface ChatRepo {


    fun sendMessage(chat: Chat)
    suspend fun getMessages(callback: FirebaseCallback)
    suspend fun getUsersMessage():List<Chat>
}