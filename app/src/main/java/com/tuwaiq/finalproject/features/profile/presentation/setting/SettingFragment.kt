package com.tuwaiq.finalproject.features.profile.presentation.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.tuwaiq.finalproject.R
import com.tuwaiq.finalproject.databinding.SettingFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : Fragment() {



    private val settingViewModel by viewModels<SettingViewModel>()

    lateinit var binding: SettingFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       binding = SettingFragmentBinding.inflate(layoutInflater)

        binding.logOutBtn.setOnClickListener{
            Firebase.auth.signOut()
            findNavController().navigate(R.id.singInFragment)
        }

        return binding.root
    }



}