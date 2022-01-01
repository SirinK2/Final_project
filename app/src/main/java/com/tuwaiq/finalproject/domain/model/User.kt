package com.tuwaiq.finalproject.domain.model

import com.tuwaiq.finalproject.data.remote.dto.Rating

data class User (
    var authId: String = "",
    var name: String,
    var bio: String,
    var ratings: List<Rating>
)