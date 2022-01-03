package com.tuwaiq.finalproject.domain.use_case

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.util.Log
import com.google.android.gms.location.LocationServices
import com.tuwaiq.finalproject.data.remote.dto.PostDto
import com.tuwaiq.finalproject.data.remote.dto.toPost
import com.tuwaiq.finalproject.domain.model.Post
import com.tuwaiq.finalproject.domain.repo.PostRepo
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

private const val TAG = "GetPostUseCase"
class GetPostUseCase @Inject constructor(val repo: PostRepo) {

    @SuppressLint("MissingPermission")
    suspend operator fun invoke(context: Context, dist: Float): List<Post> {

        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        val location = fusedLocationClient.lastLocation.await()

        val listPost: MutableList<PostDto> = mutableListOf()
        val toLocation = Location("ToLocation")
        repo.getPost().forEach { p ->


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
        return listPost.map { it.toPost() }
    }
}
