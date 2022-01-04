package com.tuwaiq.finalproject.data.remote.dto

import com.tuwaiq.finalproject.domain.model.User


data class UserDto(
    var authId: String = "",
    var name: String = "",
    var bio: String = "",
    val photoUrl: String = "",
    var ratings: List<Rating> = listOf(),
    var id: String = ""
)

fun UserDto.toUser():User{
    return User(
        authId= authId,
        name = name,
        bio = bio,
        photoUrl= photoUrl,
        ratings = ratings,
        id = id
    )
}