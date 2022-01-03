package com.tuwaiq.finalproject.presentation.profile.myProfile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.tuwaiq.finalproject.R
import com.tuwaiq.finalproject.databinding.EditProfileFragmentBinding
import com.tuwaiq.finalproject.domain.model.Post
import com.tuwaiq.finalproject.domain.model.User
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditProfileFragment : Fragment() {


    val user = User()

    private val viewModel by viewModels<EditProfileViewModel>()
    lateinit var binding: EditProfileFragmentBinding
    val args: EditProfileFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = EditProfileFragmentBinding.inflate(layoutInflater)
        binding.editPhotoIv.load(R.drawable.ic_person)
        return binding.root
    }


    override fun onStart() {
        super.onStart()

        binding.updateProfileBtn.setOnClickListener {
            val name = binding.updateNameEt.text.toString()
            val bio = binding.updateBioEt.text.toString()

            viewModel.updateUser(args.id,name, bio)
        }

    }



}