package com.tuwaiq.finalproject.features.post.domain.use_cases

import android.content.Context
import com.tuwaiq.finalproject.core.data.repo.PostRepo
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class GetLocationUseCase @Inject constructor(val repo: PostRepo) {

    suspend operator fun invoke(@ApplicationContext context: Context, title: String, description: String, price: String) =
        repo.getLocation(context,title, description, price)



}