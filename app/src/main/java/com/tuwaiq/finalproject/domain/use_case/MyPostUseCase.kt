package com.tuwaiq.finalproject.domain.use_case

import com.tuwaiq.finalproject.domain.model.Post
import com.tuwaiq.finalproject.domain.repo.PostRepo
import javax.inject.Inject

class MyPostUseCase @Inject constructor(private val repo: PostRepo) {

    suspend operator fun invoke(): List<Post> = repo.getMyPost()
}