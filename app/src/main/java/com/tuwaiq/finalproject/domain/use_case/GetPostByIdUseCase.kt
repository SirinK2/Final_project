package com.tuwaiq.finalproject.domain.use_case

import com.tuwaiq.finalproject.data.remote.dto.PostDto
import com.tuwaiq.finalproject.data.remote.dto.toPost
import com.tuwaiq.finalproject.domain.model.Post
import com.tuwaiq.finalproject.domain.repo.PostRepo
import javax.inject.Inject

class GetPostByIdUseCase @Inject constructor(val repo: PostRepo) {

    suspend operator fun invoke(id: String):Post? {
        var post: PostDto? = PostDto()

        repo.getPost().forEach {
            if (it.id == id){
                post = it
            }
        }
       return post?.toPost()
    }

}