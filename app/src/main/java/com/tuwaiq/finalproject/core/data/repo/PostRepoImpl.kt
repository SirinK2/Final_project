package com.tuwaiq.finalproject.core.data.repo

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationServices
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.tuwaiq.finalproject.core.data.remote.dto.CurrentLocation
import com.tuwaiq.finalproject.core.data.remote.dto.PostDto
import com.tuwaiq.finalproject.core.domain.model.Post
import com.tuwaiq.finalproject.core.domain.repo.PostRepo
import kotlinx.coroutines.tasks.await
import java.lang.Exception

private const val TAG = "PostRepoImpl"

class PostRepoImpl : PostRepo {


    private val postCollectionRef = Firebase.firestore.collection("post")

    override suspend fun addPost(post: Post) {
        try {
            postCollectionRef.add(post)
            Log.d(TAG, "add post ")
        }catch (e: Exception){

            Log.d(TAG,"from PostRepoImpl", e)
        }
    }




    override suspend fun savePost(context: Context, category: String, title: String, description: String, price: String) {

        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){ return }

        val location =  fusedLocationClient.lastLocation.await()

        val myLocation = CurrentLocation(location?.latitude, location?.longitude)

        Log.d(TAG," from location ${location?.longitude}  ${location?.latitude} ")

        val items = Post(category,title,description,price,myLocation)

        addPost(items)

    }


}