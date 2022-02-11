package com.tuwaiq.finalproject.domain.use_case

import com.tuwaiq.finalproject.domain.repo.PostRepo
import javax.inject.Inject

class DeletePostUseCase @Inject constructor(private val repo: PostRepo) {

    operator fun invoke(id:String) = repo.deletePost(id)


}