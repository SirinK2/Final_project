package com.tuwaiq.finalproject.presentation.messages.presentation.directMessage

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tuwaiq.finalproject.databinding.DirectMessageFragmentBinding
import com.tuwaiq.finalproject.databinding.DirectMessageItemListBinding

class DirectMessageFragment : Fragment() {


    private val directMessageViewModel by lazy { ViewModelProvider(this)[DirectMessageViewModel::class.java] }

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


    private inner class MessageHolder(val binding: DirectMessageItemListBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(){

        }
    }


    private inner class MessageAdapter():RecyclerView.Adapter<MessageHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageHolder {
            val binding = DirectMessageItemListBinding.inflate(
                layoutInflater,
                parent,
                false
            )
            return MessageHolder(binding)
        }

        override fun onBindViewHolder(holder: MessageHolder, position: Int) {
            TODO("Not yet implemented")
        }

        override fun getItemCount(): Int {
            TODO("Not yet implemented")
        }


    }









}