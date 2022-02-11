package com.tuwaiq.finalproject.presentation.messages.directMessage

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tuwaiq.finalproject.R
import com.tuwaiq.finalproject.databinding.DirectMessageFragmentBinding
import com.tuwaiq.finalproject.databinding.DirectMessageItemListBinding
import com.tuwaiq.finalproject.domain.model.Chat
import com.tuwaiq.finalproject.domain.model.User
import com.tuwaiq.finalproject.generated.callback.OnClickListener
import com.tuwaiq.finalproject.util.Constant.format
import com.tuwaiq.finalproject.util.Constant.uid
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "DirectMessageFragment"
@AndroidEntryPoint
class DirectMessageFragment : Fragment() {


    private val viewModel by viewModels<DirectMessageViewModel>()
    private lateinit var users: MutableList<String>
    lateinit var chat: List<Chat>

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

        chat = listOf()
        users = mutableListOf()
        var receiverId = ""
        var senderId = ""




//        viewModel.lastMessage(object : ChatCallback {
//
//            override fun onCallback(callback: List<Chat>) {
//                callback.forEach {
//                    chat += it
//                    users += it.receiverID
//                    users.toSet().toList()
//                    Log.e(TAG, "onViewCreated: users  ${users.toSet().toList()}", )
//                    viewModel.getUser(users).observe(
//                        viewLifecycleOwner,{
//
//                            binding.dmRv.adapter = MessageAdapter(it)
//
//                            Log.d(TAG, "onCallback: us $it")
//
//
//                        }
//                    )

//                    receiverId = it.receiverID
//                    senderId = it.senderId
//                    if (it.senderId == uid) {
//
//                        chat += it
//
//
//                    }

//                       val x =  chat
//                    Log.d(TAG, "onCallback: $x")



//                }
////                if(receiverId != uid && senderId == uid){
////                    users += receiverId
////                    users.toSet().toList()
////                    Log.e(TAG, "onViewCreated: users  ${users.toSet().toList()}", )
////                }
//
//
//
//
//            }
//
//
//        }
//
//        )

        viewModel.lastMessage().observe(
            viewLifecycleOwner,{ chat ->
                    this.chat = chat.sortedBy { it.date }
                Log.e(TAG, "onViewCreated: $chat", )
                chat.forEach {
                    users += it.receiverID
                    users.toSet().toList()
                    Log.e(TAG, "onViewCreated: users  ${users.toSet().toList()}", )
                }
                viewModel.getUser(users).observe(
                    viewLifecycleOwner,{

                        binding.dmRv.adapter = MessageAdapter(it)

                        Log.d(TAG, "onCallback: us $it")


                    }
                )
            }
        )



    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy: Hii", )
    }


    private inner class MessageHolder(
        val binding: DirectMessageItemListBinding
    ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        var user = User()
        init {
            itemView.setOnClickListener(this)
        }

        fun bind(user: User) {
            this.user = user
            binding.apply {

                    Glide.with(requireContext()).load(user.photoUrl)
                        .placeholder(R.drawable.ic_person)
                        .into(dmIv)
                    dmNameTv.text = user.name

                chat.forEach {
                    if (user.authId == it.receiverID) {
                        dmMessageTv.text = it.message
//
                        dmDateTv.text = android.text.format.DateFormat.format(format, it.date)
                    }
                }
//

            }
        }

        override fun onClick(v: View?) {
            if (v == itemView){
                Log.d(TAG, "onClick: nnlnlnlb")
                findNavController().navigate(DirectMessageFragmentDirections.actionDirectMessageFragmentToChatFragment(user.authId))
            }
        }
    }


    private inner class MessageAdapter(val chatList: List<User>) :
        RecyclerView.Adapter<MessageHolder>() {
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

        override fun getItemCount(): Int {
//            Log.d(TAG, "size: ${chatList.user.size}")
            return chatList.size
        }
    }
}