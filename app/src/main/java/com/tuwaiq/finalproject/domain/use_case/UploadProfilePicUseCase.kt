package com.tuwaiq.finalproject.domain.use_case

import android.net.Uri
import com.tuwaiq.finalproject.domain.repo.UserRepo
import javax.inject.Inject

class UploadProfilePicUseCase @Inject constructor(val repo: UserRepo) {


    suspend operator fun invoke(uri: Uri):String = repo.uploadProfilePic(uri)



}