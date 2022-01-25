package com.tuwaiq.finalproject.presentation.messages.chatting

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tuwaiq.finalproject.databinding.ChatFragmentBinding
import com.tuwaiq.finalproject.databinding.MessageItemBinding
import com.tuwaiq.finalproject.domain.model.Chat

class ChatFragment : Fragment() {



    private val chatViewModel by lazy { ViewModelProvider(this)[ChatViewModel::class.java] }

    lateinit var binding: ChatFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ChatFragmentBinding.inflate(layoutInflater)
        binding.chatRv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ChatAdapter(listOf( Chat("Hellllllllo"), Chat("mfjlbnlrfm ls ")))
        }
        return binding.root
    }


    private inner class ChatHolder(val binding: MessageItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(chat: Chat){

            binding.chatMessageTv.text = chat.r
        }
    }

    private inner class ChatAdapter(val chat:List<Chat>):RecyclerView.Adapter<ChatHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatHolder {
            val binding = MessageItemBinding.inflate(
                layoutInflater,
                parent,
                false
            )
            return ChatHolder(binding)
        }

        override fun onBindViewHolder(holder: ChatHolder, position: Int) {
            val chats = chat[position]
            holder.bind(chats)

        }

        override fun getItemCount(): Int = chat.size
    }


}