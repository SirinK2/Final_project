package com.tuwaiq.finalproject.presentation.post.domain.use_cases

import com.tuwaiq.finalproject.core.data.firebase.Post
import com.tuwaiq.finalproject.core.data.repo.PostRepo

class SavePostUseCase(val repo: PostRepo) {

    suspend operator fun invoke(post: Post) = repo.savePost(post)
}