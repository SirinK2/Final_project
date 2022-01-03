package com.tuwaiq.finalproject.domain.use_case

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.tuwaiq.finalproject.data.remote.dto.PostDto
import com.tuwaiq.finalproject.data.remote.dto.toPost
import com.tuwaiq.finalproject.domain.model.Post
import com.tuwaiq.finalproject.domain.repo.PostRepo
import javax.inject.Inject

class MyPostUseCase @Inject constructor(private val repo: PostRepo) {

    suspend operator fun invoke(): List<Post> {
        val owner = Firebase.auth.currentUser?.uid.toString()
        val listPost: MutableList<PostDto> = mutableListOf()
        repo.getPost().forEach {
            if (it.owner == owner)
            listPost += it
        }

        return listPost.map { it.toPost() }

    }
}