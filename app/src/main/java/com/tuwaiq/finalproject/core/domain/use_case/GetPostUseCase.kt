package com.tuwaiq.finalproject.core.domain.use_case

import com.tuwaiq.finalproject.core.domain.model.Post
import com.tuwaiq.finalproject.core.domain.repo.PostRepo
import javax.inject.Inject

class GetPostUseCase @Inject constructor(val repo: PostRepo) {

    suspend operator fun invoke(): List<Post> = repo.getPost()
}