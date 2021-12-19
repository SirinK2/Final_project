package com.tuwaiq.finalproject.features.post.domain.use_cases

import com.tuwaiq.finalproject.core.data.remote.dto.PostDto
import com.tuwaiq.finalproject.core.domain.repo.PostRepo
import javax.inject.Inject


class SavePostUseCase @Inject constructor(val repo: PostRepo) {

    suspend operator fun invoke(post: PostDto) = repo.addPost(post)

}