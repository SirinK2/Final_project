package com.tuwaiq.finalproject.domain.use_case

import android.util.Log
import com.tuwaiq.finalproject.data.remote.dto.PostDto
import com.tuwaiq.finalproject.data.remote.dto.toPost
import com.tuwaiq.finalproject.domain.model.Post
import com.tuwaiq.finalproject.domain.repo.PostRepo
import javax.inject.Inject

private const val TAG = "GetUsersPostUseCase"
class GetUsersPostUseCase @Inject constructor(private val repo: PostRepo) {

    suspend operator fun invoke(id:String):List<Post> {
        val usersPost: MutableList<PostDto> = mutableListOf()
        repo.getPost().forEach {
            if (it.owner == id){
                usersPost += it
            }
        }
        Log.e(TAG, "invoke: $usersPost", )
        return usersPost.map { it.toPost() }

    }

}

//postCollectionRef.document(args).get().await()