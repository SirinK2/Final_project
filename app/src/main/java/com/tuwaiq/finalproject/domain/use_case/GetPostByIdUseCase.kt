package com.tuwaiq.finalproject.domain.use_case

import com.tuwaiq.finalproject.domain.model.Post
import com.tuwaiq.finalproject.domain.repo.PostRepo
import javax.inject.Inject

class GetPostByIdUseCase @Inject constructor(val repo: PostRepo) {

    suspend operator fun invoke(id: String):Post = repo.getPostById(id)
}