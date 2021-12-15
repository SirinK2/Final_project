package com.tuwaiq.finalproject.core.data.repo

import com.tuwaiq.finalproject.core.data.firebase.Post

interface PostRepo {

    suspend fun savePost(post: Post)

}