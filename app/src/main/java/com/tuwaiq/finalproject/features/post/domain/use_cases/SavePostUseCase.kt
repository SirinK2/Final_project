package com.tuwaiq.finalproject.features.post.domain.use_cases

import com.tuwaiq.finalproject.core.data.firebase.Post
import com.tuwaiq.finalproject.core.data.repo.PostRepo
import javax.inject.Inject

class SavePostUseCase @Inject constructor(val repo: PostRepo) {

    suspend operator fun invoke(post: Post) = repo.savePost(post)

}