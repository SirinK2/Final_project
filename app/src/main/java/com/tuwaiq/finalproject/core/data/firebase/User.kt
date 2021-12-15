package com.tuwaiq.finalproject.core.data.firebase


data class User(
    var name: String,
    var bio: String,
    var ratings: List<Rating>
)