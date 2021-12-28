package com.tuwaiq.finalproject.core.util

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tuwaiq.finalproject.R
import com.tuwaiq.finalproject.databinding.CameraBinding
import io.ak1.pix.helpers.PixEventCallback
import java.io.File
import java.util.*

class CameraImagePicker: BottomSheetDialogFragment() {
    private lateinit var binding : CameraBinding
    private lateinit var photoFile: File
    private lateinit var photoUri: Uri
    private val fileName
        get() = "IMG_${UUID.randomUUID()}.jpg"
    private val fileDir = context?.applicationContext?.filesDir






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


    private fun updatePhotoView(){

        if (photoFile.exists()){

            val bitmap = BitmapFactory.decodeFile(photoFile.path)

            binding.ImgIv.setImageBitmap(bitmap)


        }else{

            binding.ImgIv.setImageDrawable(null)

        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)

    }

    override fun onStart() {
        super.onStart()



//        photoFile =

        binding.cameraDialogBtn.setOnClickListener {


            photoFile = getPhotoFile()
            photoUri = FileProvider.getUriForFile(requireContext(),"com.tuwaiq.finalproject.fileProvider",photoFile)

            updatePhotoView()

            when(PackageManager.PERMISSION_GRANTED){
                context?.let {
                    ContextCompat.checkSelfPermission(it, Manifest.permission.CAMERA)
                } -> getCameraLauncher.launch(photoUri)
                else -> getPermissionRequest.launch(Manifest.permission.CAMERA)
            }

        }



        binding.galleryDialogBtn.setOnClickListener {

        }


    }


    fun getPhotoFile():File = File(fileDir,fileName)






}