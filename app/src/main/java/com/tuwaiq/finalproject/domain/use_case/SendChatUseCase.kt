package com.tuwaiq.finalproject.domain.use_case

import com.tuwaiq.finalproject.domain.model.Chat
import com.tuwaiq.finalproject.domain.repo.ChatRepo
import javax.inject.Inject

class SendChatUseCase @Inject constructor(private val repo: ChatRepo) {

    operator fun invoke(chat: Chat) = repo.sendMessage(chat)
}