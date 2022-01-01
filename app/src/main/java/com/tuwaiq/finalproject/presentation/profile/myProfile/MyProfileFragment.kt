package com.tuwaiq.finalproject.presentation.profile.myProfile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.tuwaiq.finalproject.R
import com.tuwaiq.finalproject.databinding.MyProfileFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyProfileFragment : Fragment() {



    private val viewModel: MyProfileViewModel by viewModels()

    lateinit var binding: MyProfileFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MyProfileFragmentBinding.inflate(layoutInflater)
        binding.myProfNameTv.text = Firebase.auth.currentUser?.displayName

//        binding.myProfButton.setOnClickListener {
//            val name = binding.myProfNameTv.text.toString()
//            val bio = binding.myProfBioTv.text.toString()
//            val rate: List<Rating> = listOf()
//
//            for (i in rate){
//                binding.myProfTextRating.text.toString().toFloat()
//            }
//
//            val user = UserDto(name,bio,rate)
//
//
//                viewModel.saveUser(user)
//
//
//        }


        return binding.root
    }

    override fun onStart() {
        super.onStart()

        binding.settingBtn.setOnClickListener {
            findNavController().navigate(R.id.settingFragment)
        }
    }







}