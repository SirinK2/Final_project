package com.tuwaiq.finalproject.presentation.messages.directMessage

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tuwaiq.finalproject.R
import com.tuwaiq.finalproject.databinding.DirectMessageFragmentBinding
import com.tuwaiq.finalproject.databinding.DirectMessageItemListBinding
import com.tuwaiq.finalproject.domain.model.Chat
import com.tuwaiq.finalproject.domain.model.ChatList
import com.tuwaiq.finalproject.domain.model.User
import com.tuwaiq.finalproject.util.Constant.format
import com.tuwaiq.finalproject.util.Constant.uid
import com.tuwaiq.finalproject.util.FirebaseCallback
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.log

private const val TAG = "DirectMessageFragment"
@AndroidEntryPoint
class DirectMessageFragment : Fragment() {


    private val viewModel by viewModels<DirectMessageViewModel>()
    private lateinit var users: MutableList<User>
    lateinit var chat:MutableList<Chat>

    private lateinit var binding: DirectMessageFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DirectMessageFragmentBinding.inflate(layoutInflater)

        binding.dmRv.apply {
            layoutManager = LinearLayoutManager(context)
        }

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        chat = mutableListOf()
        users = mutableListOf()
        var receiverId = ""
        var senderId = ""

        viewModel.getMessages(object : FirebaseCallback{

            override fun onCallback(callback: List<Chat>) {
                callback.forEach {
                   receiverId =  it.receiverID
                    senderId = it.senderId
                    if (it.senderId == uid){
                        chat += it


                    }

                }


            }




        }

        )



        viewModel.getUser().observe(
            viewLifecycleOwner,{
                it.forEach { user ->
                    if (user.authId != receiverId){
                        users += user


                    }


                }

                val chatList = listOf(ChatList(chat,users))

                Log.d(TAG, "onViewCreated: $chatList ")
                binding.dmRv.adapter = MessageAdapter(chatList)

            }
        )









    }


    private inner class MessageHolder(val binding: DirectMessageItemListBinding
    ):RecyclerView.ViewHolder(binding.root){


        fun bind(chatList: ChatList){

            binding.apply {
                chatList.user.forEach {
                    Glide.with(requireContext()).load(it.photoUrl).placeholder(R.drawable.ic_person).into(dmIv)
                    dmNameTv.text = it.name
                }

                chatList.chat.forEach {
                    dmMessageTv.text = it.message
                    dmDateTv.text = DateFormat.format(format,it.date)


                }




            }


        }
    }


    private inner class MessageAdapter(val chatList: List<ChatList>):RecyclerView.Adapter<MessageHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageHolder {
            val binding = DirectMessageItemListBinding.inflate(
                layoutInflater,
                parent,
                false
            )
            return MessageHolder(binding)
        }

        override fun onBindViewHolder(holder: MessageHolder, position: Int) {
            val chat = chatList[position]
            holder.bind(chat)

        }

        override fun getItemCount(): Int = chatList.size


    }









}