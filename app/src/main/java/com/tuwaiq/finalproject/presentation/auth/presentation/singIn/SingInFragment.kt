package com.tuwaiq.finalproject.presentation.auth.presentation.singIn

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.tuwaiq.finalproject.R
import com.tuwaiq.finalproject.databinding.SingInFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SingInFragment : Fragment() {



    private lateinit var binding: SingInFragmentBinding
    private val viewModel: SingInViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SingInFragmentBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onStart() {
        super.onStart()

        binding.singinBtn.setOnClickListener {

            val email = binding.singinEmailEt.text.toString()
            val password = binding.singinPasswordEt.text.toString()


            viewModel.singIn(email, password)


        }

    }



}