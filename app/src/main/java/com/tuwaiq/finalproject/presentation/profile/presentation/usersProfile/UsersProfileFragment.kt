package com.tuwaiq.finalproject.presentation.profile.presentation.usersProfile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tuwaiq.finalproject.databinding.UsersProfileFragmentBinding
import com.tuwaiq.finalproject.databinding.UsersProfileItemsListBinding

class UsersProfileFragment : Fragment() {


    private val usersProfileViewModel by lazy { ViewModelProvider(this)[UsersProfileViewModel::class.java] }

    private lateinit var binding: UsersProfileFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = UsersProfileFragmentBinding.inflate(layoutInflater)

        binding.usersProfRv.layoutManager = GridLayoutManager(context, 2)

        binding.usersProfRv.adapter = ProfileAdapter()



        return binding.root
    }


    private inner class ProfileHolder(val binding: UsersProfileItemsListBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(){

        }
    }


    private inner class ProfileAdapter():RecyclerView.Adapter<ProfileHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileHolder {
            val binding = UsersProfileItemsListBinding.inflate(
                layoutInflater,
                parent,
                false
            )
            return ProfileHolder(binding)
        }

        override fun onBindViewHolder(holder: ProfileHolder, position: Int) {
            TODO("Not yet implemented")
        }

        override fun getItemCount(): Int {
            TODO("Not yet implemented")
        }

    }




}