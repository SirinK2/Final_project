package com.tuwaiq.finalproject.domain.use_case

import android.content.Context
import com.tuwaiq.finalproject.domain.model.Post
import com.tuwaiq.finalproject.domain.repo.PostRepo
import javax.inject.Inject

class GetPostUseCase @Inject constructor(val repo: PostRepo) {

    suspend operator fun invoke(context: Context, dist: Float): List<Post> = repo.getPost(context, dist)
}