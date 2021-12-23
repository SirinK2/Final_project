package com.tuwaiq.finalproject.core.util

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tuwaiq.finalproject.databinding.CameraBinding
import java.io.File

class Camera: BottomSheetDialogFragment() {
    private lateinit var binding : CameraBinding
    private lateinit var photoFile: File
    private lateinit var photoUri: Uri





    private val getCameraLauncher =
        registerForActivityResult(ActivityResultContracts.TakePicture()){

        }

    private val getPermissionRequest =
        registerForActivityResult(ActivityResultContracts.RequestPermission()){

        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CameraBinding.inflate(layoutInflater)

        return binding.root
    }




    override fun onStart() {
        super.onStart()



//        photoFile =

        binding.cameraDialogBtn.setOnClickListener {

            when(PackageManager.PERMISSION_GRANTED){
                context?.let {
                    ContextCompat.checkSelfPermission(it, Manifest.permission.CAMERA)
                } -> getCameraLauncher.launch(photoUri)
                else -> getPermissionRequest.launch(Manifest.permission.CAMERA)
            }

        }


    }






}