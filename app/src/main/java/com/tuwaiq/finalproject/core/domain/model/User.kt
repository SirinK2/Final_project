package com.tuwaiq.finalproject.core.domain.model

import com.tuwaiq.finalproject.core.data.remote.dto.Rating

data class User (
//    var authId: String = "",
    var name: String,
    var bio: String,
    var ratings: List<Rating>
)