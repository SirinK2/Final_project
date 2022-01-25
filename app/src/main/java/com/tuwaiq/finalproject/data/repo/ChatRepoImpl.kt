package com.tuwaiq.finalproject.data.repo

import android.util.Log
import com.tuwaiq.finalproject.domain.model.Chat
import com.tuwaiq.finalproject.domain.repo.ChatRepo
import com.tuwaiq.finalproject.util.Constant.chatCollectionRef
import com.tuwaiq.finalproject.util.FirebaseCallback

private const val TAG = "ChatRepoImpl"
class ChatRepoImpl:ChatRepo {

    override fun sendMessage(chat: Chat) {
        val ref = chatCollectionRef.document()
        chat.docId = ref.id
        ref.set(chat)
    }

    override suspend fun getMessages(callback: FirebaseCallback) {

          chatCollectionRef.addSnapshotListener { value, error ->

              if (value != null) {
                  val messageList =  value.toObjects(Chat::class.java)
                  callback.onCallback(messageList.sortedBy { it.date })
                  Log.e(TAG, "getMessages from snapshot: $messageList", )

              }


          }




    }
}