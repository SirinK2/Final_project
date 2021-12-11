package com.tuwaiq.finalproject.presentation.profile.presentation.myProfile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tuwaiq.finalproject.databinding.MyProfileFragmentBinding

class MyProfileFragment : Fragment() {



    private val myProfileViewModel by lazy { ViewModelProvider(this)[MyProfileViewModel::class.java] }

    lateinit var binding: MyProfileFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MyProfileFragmentBinding.inflate(layoutInflater)
        return binding.root
    }





}