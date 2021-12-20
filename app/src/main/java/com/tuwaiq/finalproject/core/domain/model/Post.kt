package com.tuwaiq.finalproject.core.domain.model

import com.tuwaiq.finalproject.core.data.remote.dto.CurrentLocation

data class Post (
    var categoryName: String = "",
    var title: String = "",
    var description: String = "",
    var price: String = "",
    var location: CurrentLocation = CurrentLocation(0.0,0.0)
        )