package com.tuwaiq.finalproject.core.data.remote.dto

import android.location.Location


data class PostDto (
    var categoryName: String,
    var title: String,
    var description: String,
    var price: String,
    var location: CurrentLocation

)

