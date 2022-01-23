package com.tuwaiq.finalproject.domain.use_case

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.google.android.gms.location.LocationServices
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.tuwaiq.finalproject.data.remote.dto.CurrentLocation
import com.tuwaiq.finalproject.domain.model.Post
import com.tuwaiq.finalproject.domain.repo.PostRepo
import javax.inject.Inject

private const val TAG = "AddPostUseCase"
class AddPostUseCase @Inject constructor(val repo: PostRepo) {

     @SuppressLint("MissingPermission")
     operator fun invoke(context: Context,
                         category: String,
                         title: String,
                         description: String,
                         price: String, photoUrl: List<String>){

         val owner = Firebase.auth.currentUser?.uid.toString()
         val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

         fusedLocationClient.lastLocation.addOnSuccessListener { location ->
             val myLocation = CurrentLocation(location.latitude, location.longitude)

             Log.d(TAG, " from location ${location.longitude}  ${location.latitude} ")

             val items = Post(owner,category, title, description, price, myLocation,photoUrl)

             repo.addPost(items)

         }
     }

}