package com.tuwaiq.finalproject.core.data.remote.dto

import com.tuwaiq.finalproject.core.domain.model.User


data class UserDto(
    var name: String,
    var bio: String,
    var ratings: List<Rating>
)

fun UserDto.toUser():User{
    return User(
        name = name,
        bio = bio,
        ratings = ratings
    )
}