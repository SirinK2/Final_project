package com.tuwaiq.finalproject.presentation.messages.chatting

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tuwaiq.finalproject.databinding.ChatFragmentBinding

class ChatFragment : Fragment() {



    private val chatViewModel by lazy { ViewModelProvider(this)[ChatViewModel::class.java] }

    lateinit var binding: ChatFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ChatFragmentBinding.inflate(layoutInflater)
        return binding.root
    }



}