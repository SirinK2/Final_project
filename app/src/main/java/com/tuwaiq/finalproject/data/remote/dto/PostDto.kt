package com.tuwaiq.finalproject.data.remote.dto

import com.tuwaiq.finalproject.domain.model.Post


data class PostDto (
    var owner: String = "",
    var categoryName: String = "",
    var title: String = "",
    var description: String = "",
    var price: String = "",
    var location: CurrentLocation = CurrentLocation(0.0,0.0),
    var photoUrl: List<String> = listOf(),
    var id: String = ""

)
fun PostDto.toPost():Post{
    return Post(
        owner= owner,
        categoryName= categoryName,
        title = title,
        description = description,
        price = price,
        location = location,
        photoUrl= photoUrl,
        id= id
    )
}


