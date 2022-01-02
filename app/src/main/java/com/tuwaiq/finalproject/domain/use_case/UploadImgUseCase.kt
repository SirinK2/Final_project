package com.tuwaiq.finalproject.domain.use_case

import android.net.Uri
import com.google.firebase.storage.UploadTask
import com.tuwaiq.finalproject.domain.repo.PostRepo
import javax.inject.Inject

class UploadImgUseCase @Inject constructor(private val repo: PostRepo) {

    suspend operator fun invoke(uri: List<Uri>): List<String> = repo.uploadImage(uri)

}