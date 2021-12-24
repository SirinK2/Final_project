package com.tuwaiq.finalproject.core.domain.use_case

import android.content.Context
import com.tuwaiq.finalproject.core.domain.repo.PostRepo
import javax.inject.Inject

class GetLocationUseCase @Inject constructor(val repo: PostRepo) {


    suspend operator fun invoke(context: Context, latitude: Double?, longitude: Double? ): Float  = repo.getLocation(context, latitude, longitude)



}