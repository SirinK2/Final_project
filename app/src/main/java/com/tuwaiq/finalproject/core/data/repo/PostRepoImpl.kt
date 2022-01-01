package com.tuwaiq.finalproject.core.data.repo

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.util.Log
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationServices
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.UploadTask
import com.google.firebase.storage.ktx.storage
import com.tuwaiq.finalproject.core.data.remote.dto.CurrentLocation
import com.tuwaiq.finalproject.core.domain.model.Post
import com.tuwaiq.finalproject.core.domain.repo.PostRepo
import com.tuwaiq.finalproject.core.util.Constant.postCollectionRef
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import java.util.*

private const val TAG = "PostRepoImpl"

class PostRepoImpl : PostRepo {


    override suspend fun addPost(post: Post) {

        val ref = postCollectionRef.document()
        post.id = ref.id
        ref.set(post)

    }


    override suspend fun savePost(
        context: Context,
        category: String,
        title: String,
        description: String,
        price: String
    ) {

        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        val location = fusedLocationClient.lastLocation.await()

        val myLocation = CurrentLocation(location?.latitude, location?.longitude)

        Log.d(TAG, " from location ${location?.longitude}  ${location?.latitude} ")


        val items = Post(category, title, description, price, myLocation)

        addPost(items)

    }


    override suspend fun uploadImage(uri: Uri): UploadTask.TaskSnapshot{
        val fileName = "${UUID.randomUUID()}.jpg"
        val imgRef = Firebase.storage.reference.child("images/$fileName")

          return imgRef.putFile(uri).await()





//        val upload =
//        if (upload.task.isSuccessful){
//            val uri = upload.storage.downloadUrl.await()
//
//        }





    }

    
    @SuppressLint("MissingPermission")
    override suspend fun getPost(
        @ApplicationContext context: Context,
        dist: Float): List<Post> {
        
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        val location = fusedLocationClient.lastLocation.await()

        val listPost: MutableList<Post> = mutableListOf()
        val toLocation = Location("ToLocation")

            postCollectionRef.get().await().documents.forEach {



                val post = it.toObject(Post::class.java)

                post?.let { p ->


                    toLocation.apply {
                        this.longitude = p.location.longitude ?: 0.0
                        this.latitude = p.location.latitude ?: 0.0
                    }

                    Log.d(TAG, "getLocation: long ${p.location.longitude} lat ${p.location.latitude}  ")
                    val distance = location.distanceTo(toLocation)
                    Log.d(TAG, "dist: $distance")
                    if (distance <= dist) {
                        listPost += p
                        Log.d(TAG, "getLocation: distance $distance")
                    }
                }
            }

        return listPost

    }


}