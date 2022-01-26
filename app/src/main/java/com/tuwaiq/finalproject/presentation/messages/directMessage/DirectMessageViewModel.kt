package com.tuwaiq.finalproject.presentation.messages.directMessage

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.tuwaiq.finalproject.domain.model.User
import com.tuwaiq.finalproject.domain.use_case.GetAllUsersUseCase
import com.tuwaiq.finalproject.domain.use_case.GetMessagesUseCase
import com.tuwaiq.finalproject.domain.use_case.GetUserByIdUseCase
import com.tuwaiq.finalproject.util.FirebaseCallback
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DirectMessageViewModel @Inject constructor(
    private val getAllUsersUseCase: GetAllUsersUseCase,
    private val getMessagesUseCase: GetMessagesUseCase
    ) : ViewModel() {

        fun getUser():LiveData<List<User>> =
            liveData(Dispatchers.IO) { emit(getAllUsersUseCase()) }

        fun getMessages(callback: FirebaseCallback) =
            viewModelScope.launch(Dispatchers.IO) { getMessagesUseCase(callback) }




    }