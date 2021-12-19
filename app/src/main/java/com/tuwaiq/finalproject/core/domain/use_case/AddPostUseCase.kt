package com.tuwaiq.finalproject.core.domain.use_case

import android.content.Context
import com.tuwaiq.finalproject.core.domain.repo.PostRepo
import javax.inject.Inject

class AddPostUseCase @Inject constructor(val repo: PostRepo) {

    suspend operator fun invoke(context: Context,
                                category: String,
                                title: String,
                                description: String,
                                price: String)
    = repo.savePost(context, category, title, description, price)



}