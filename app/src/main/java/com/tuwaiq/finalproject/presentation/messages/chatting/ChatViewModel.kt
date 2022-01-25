package com.tuwaiq.finalproject.presentation.messages.chatting

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.tuwaiq.finalproject.domain.model.Chat
import com.tuwaiq.finalproject.domain.model.User
import com.tuwaiq.finalproject.domain.use_case.GetMessagesUseCase
import com.tuwaiq.finalproject.domain.use_case.GetUserByIdUseCase
import com.tuwaiq.finalproject.domain.use_case.SendChatUseCase
import com.tuwaiq.finalproject.util.FirebaseCallback
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ChatViewModel @Inject constructor(
    private val sendChatUseCase: SendChatUseCase,
    private val getUserByIdUseCase: GetUserByIdUseCase,
    private val getMessagesUseCase: GetMessagesUseCase
    ) : ViewModel() {


        fun sendMessage(chat: Chat) = sendChatUseCase(chat)


        fun getUser(id:String):LiveData<User> = liveData(Dispatchers.IO) { emit(getUserByIdUseCase(id)) }


        fun getMessage(callback: FirebaseCallback)=
            viewModelScope.launch (Dispatchers.IO) {getMessagesUseCase(callback) }





    }