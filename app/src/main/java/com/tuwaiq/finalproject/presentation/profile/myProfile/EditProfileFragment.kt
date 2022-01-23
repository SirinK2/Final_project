package com.tuwaiq.finalproject.presentation.profile.myProfile

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.bumptech.glide.Glide
import com.tuwaiq.finalproject.R
import com.tuwaiq.finalproject.databinding.EditProfileFragmentBinding
import com.tuwaiq.finalproject.domain.model.Post
import com.tuwaiq.finalproject.domain.model.User
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "EditProfileFragment"
@AndroidEntryPoint
class EditProfileFragment : Fragment() {


    val user = User()

    private val viewModel by viewModels<EditProfileViewModel>()
    private lateinit var binding: EditProfileFragmentBinding
    private val args: EditProfileFragmentArgs by navArgs()
    private lateinit var photoUri: Uri
    private lateinit var photoUrl:String
    lateinit var users: User


    private val getImageLauncher =
        registerForActivityResult(
            ActivityResultContracts
                .OpenDocument()){
            if (it != null) {
                photoUri = it

                binding.editPhotoIv.load(it)

            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = EditProfileFragmentBinding.inflate(layoutInflater)
        binding.editPhotoIv.load(R.drawable.ic_person)
        binding.progressBar.visibility = View.GONE
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            viewModel.getUserInfo().observe(
                viewLifecycleOwner,{ user ->
                    users = user
                    photoUrl = user.photoUrl
                    Glide.with(requireContext())
                        .load(user.photoUrl)
                        .into(editPhotoIv)

                    updateNameEt.setText(user.name)
                    updateBioEt.setText(user.bio)
                }
            )
        }
    }



    override fun onStart() {
        super.onStart()

        photoUri = user.photoUrl.toUri()


        binding.editPhotoIv.setOnClickListener {
            getImageLauncher.launch(arrayOf("image/*"))
        }



        binding.updateProfileBtn.setOnClickListener {

            val name = binding.updateNameEt.text.toString()
            val bio = binding.updateBioEt.text.toString()

           updateProfile(name, bio).also {
               binding.progressBar.visibility = View.VISIBLE
           }

        }


    }

    private fun updateProfile(name:String,bio:String){


        if(photoUri.toString().isEmpty()) {
            Log.e(TAG, "updateProfile: without observer", )

            viewModel.updateUser(
                args.id,
                name,
                bio,
                users.photoUrl
            )
            findNavController().navigate(R.id.myProfileFragment)

        }else{

            viewModel.uploadImg(photoUri).observe(
                viewLifecycleOwner, {
                    photoUrl = it

                    Log.e(TAG, "updateProfile: with observer", )
                    viewModel.updateUser(
                        args.id,
                        name,
                        bio,
                        photoUrl
                    )
                    findNavController().navigate(R.id.myProfileFragment)

                }
            )



        }





    }



}