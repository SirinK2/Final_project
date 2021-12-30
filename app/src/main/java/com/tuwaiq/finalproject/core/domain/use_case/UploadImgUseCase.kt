package com.tuwaiq.finalproject.core.domain.use_case

import android.net.Uri
import com.tuwaiq.finalproject.core.domain.repo.PostRepo
import javax.inject.Inject

class UploadImgUseCase @Inject constructor(private val repo: PostRepo) {

    suspend operator fun invoke(uri: Uri) = repo.uploadImage(uri)

}