package com.tuwaiq.finalproject.core.domain.use_case

import com.tuwaiq.finalproject.core.domain.repo.PostRepo
import javax.inject.Inject

class UploadImgUseCase @Inject constructor(private val repo: PostRepo) {

    suspend operator fun invoke() = repo.uploadImage()

}