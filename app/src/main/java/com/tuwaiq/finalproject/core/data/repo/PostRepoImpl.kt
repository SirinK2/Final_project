package com.tuwaiq.finalproject.core.data.repo

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.LocationServices
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.tuwaiq.finalproject.core.data.remote.dto.CurrentLocation
import com.tuwaiq.finalproject.core.domain.model.Post
import com.tuwaiq.finalproject.core.domain.repo.PostRepo
import com.tuwaiq.finalproject.core.util.Constant.imgFile
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import java.util.*

private const val TAG = "PostRepoImpl"

class PostRepoImpl : PostRepo {


    private val fileName = "${UUID.randomUUID()}.jpg"
    private val postCollectionRef = Firebase.firestore.collection("post")
    private val imgRef = Firebase.storage.reference.child("images/$fileName")


    override suspend fun addPost(post: Post) {

            postCollectionRef.add(post).addOnSuccessListener {
                Log.d(TAG, "add post ")
            }.addOnFailureListener {
                Log.e(TAG, "addPost: error", it)
            }


    }




    override suspend fun getPost(): List<Post> {
        val listPost: MutableList<Post> = mutableListOf()
        try {
            postCollectionRef.get().await().documents.forEach {
                val post = it.toObject(Post::class.java)
                post?.let { p ->
                    listPost += p
                }
                 Log.d(TAG, "getPost: $listPost")
            }
        }catch (e: Exception){
            Log.e(TAG, "getPost: something wrong", e)
        }

        return listPost

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



    override suspend fun uploadImage() {

        try {
            imgFile?.let { imgRef.putFile(it).await() }

        } catch (e: Exception){
            Log.e(TAG, "uploadImage: ", e)
        }

    }


}