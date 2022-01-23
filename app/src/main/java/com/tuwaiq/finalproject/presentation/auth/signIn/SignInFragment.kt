package com.tuwaiq.finalproject.presentation.auth.signIn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.tuwaiq.finalproject.R
import com.tuwaiq.finalproject.databinding.SignInFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "SingInFragment"
@AndroidEntryPoint
class SignInFragment : Fragment() {



    private lateinit var binding: SignInFragmentBinding
    private val viewModel: SignInViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SignInFragmentBinding.inflate(layoutInflater)

        return binding.root
    }


    override fun onStart() {
        super.onStart()
         val navControl = findNavController()
        binding.singinBtn.setOnClickListener {

            val email = binding.singinEmailEt.text.toString()
            val password = binding.singinPasswordEt.text.toString()


            validateSignIn(email, password)



        }

        binding.toRegisterTv.setOnClickListener {
            findNavController().navigate(R.id.registerFragment)
//            navControl.navigate(SignInFragmentDirections.actionSingInFragmentToRegisterFragment())
        }



    }


    private fun validateSignIn(email:String, password:String){
        when{
            email.isEmpty() -> binding.singinEmailEt.error = "enter email"
            password.isEmpty() -> binding.singinPasswordEt.error = "you should enter password"

            else -> {
                viewModel.signIn(email, password).addOnCompleteListener {
                    if (it.isSuccessful){
                        findNavController().popBackStack(R.id.homePageFragment,false)
                    }
                }.addOnFailureListener {

//                    Toast.makeText(requireContext(),it.localizedMessage,Toast.LENGTH_LONG).show()
                }
            }
        }
    }



}