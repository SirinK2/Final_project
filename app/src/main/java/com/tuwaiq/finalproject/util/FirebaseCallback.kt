package com.tuwaiq.finalproject.util

import com.tuwaiq.finalproject.domain.model.Chat

interface FirebaseCallback {
    fun onCallback(callback: List<Chat>)
}