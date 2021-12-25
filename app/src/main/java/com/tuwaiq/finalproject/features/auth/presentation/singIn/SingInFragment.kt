package com.tuwaiq.finalproject.features.auth.presentation.singIn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
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
        val navControl = findNavController()
        binding.singinBtn.setOnClickListener {

            val email = binding.singinEmailEt.text.toString()
            val password = binding.singinPasswordEt.text.toString()

            when{
                email.isEmpty() -> Toast.makeText(context, "you should enter email",Toast.LENGTH_LONG).show()
                password.isEmpty() -> Toast.makeText(context, "you should enter email",Toast.LENGTH_LONG).show()
                else -> {
                    viewModel.singIn(email, password)

                    navControl.navigate(SingInFragmentDirections.actionSingInFragmentToHomePageFragment())
                }
            }

        }

        binding.toRegisterTv.setOnClickListener {
            navControl.navigate(SingInFragmentDirections.actionSingInFragmentToRegisterFragment())
        }

    }



}