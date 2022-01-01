package com.tuwaiq.finalproject.features.auth.presentation.register

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.tuwaiq.finalproject.databinding.RegisterFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "RegisterFragment"
@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private lateinit var binding : RegisterFragmentBinding

    private val viewModel : RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RegisterFragmentBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        val navControl = findNavController()

        binding.registerBtn.setOnClickListener {

            val userName:String = binding.registerUsernameEt.text.toString()
            val email:String = binding.registerEmailEt.text.toString()
            val password:String = binding.registerPasswordEt.text.toString()
            val confirmPass: String = binding.registerComPassEt.text.toString()

            when{
                userName.isEmpty() -> showToast("enter username")
                email.isEmpty() -> showToast("please enter email")
                password.isEmpty() -> showToast("please enter password")
                password != confirmPass -> showToast("passwords must be matched")
                else -> {
                    viewModel.register(userName,email,password).addOnCompleteListener {
                        if (it.isSuccessful) {
                            navControl.navigate(RegisterFragmentDirections.actionRegisterFragmentToHomePageFragment())
                        }

                    }.addOnFailureListener {
                        Toast.makeText(requireContext(),it.localizedMessage, Toast.LENGTH_LONG).show()
                        Log.e(TAG, "onStart: $it", )

                    }
                }

            }


        }

        binding.toSinginTv.setOnClickListener {
            navControl.navigate(RegisterFragmentDirections.actionRegisterFragmentToSingInFragment())
        }

    }

    private fun showToast(msg:String){
        Toast.makeText(requireContext(),msg, Toast.LENGTH_LONG).show()
    }


}