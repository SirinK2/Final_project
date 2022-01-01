package com.tuwaiq.finalproject.core.util

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider.getUriForFile
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tuwaiq.finalproject.R
import com.tuwaiq.finalproject.databinding.CameraBinding
import com.tuwaiq.finalproject.features.post.presentation.newPost.ItemsFragment
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File
import java.util.*


private const val TAG = "CameraImagePicker"

class CameraImagePicker: BottomSheetDialogFragment() {



    
    private lateinit var binding : CameraBinding
    private lateinit var photoFile: File
    private lateinit var photoUri: Uri
    private val fileName
        get() = "IMG_${UUID.randomUUID()}.jpg"
    private val fileDir by lazy { context?.applicationContext?.filesDir }


    private val getCameraLauncher =
        registerForActivityResult(
            ActivityResultContracts
                .TakePicture()){
        }

    private val getImageLauncher =
        registerForActivityResult(
            ActivityResultContracts
                .OpenMultipleDocuments() ){
            it.forEach { uri ->

            }

           
            
           


    }

    private val getPermissionRequest =
        registerForActivityResult(
            ActivityResultContracts
                .RequestPermission()){

        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = CameraBinding.inflate(layoutInflater)
        return binding.root
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(
            STYLE_NORMAL,
            R.style.AppBottomSheetDialogTheme)


        photoFile =
            File(fileDir,
                fileName)

        photoUri =
            getUriForFile(requireContext()
            ,"com.tuwaiq.finalproject.fileProvider"
            ,photoFile)

    }

    override fun onStart() {
        super.onStart()


        binding.cameraDialogBtn.setOnClickListener {



            when(PackageManager.PERMISSION_GRANTED){
                context?.let {
                    ContextCompat
                        .checkSelfPermission(
                            it,
                        Manifest.permission.CAMERA)
                } -> getCameraLauncher.launch(photoUri)
                else -> getPermissionRequest.launch(Manifest.permission.CAMERA)
            }

        }



        binding.galleryDialogBtn.setOnClickListener {

            getImageLauncher.launch(arrayOf("image/*"))
        }


    }


    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: HIIIII")
    }





}