package com.tuwaiq.finalproject.domain.model

import java.util.*

data class Chat (
    var message:String = "",
    var senderId:String = "",
    var receiverID:String = "",
    var type:String = "",
    var docId:String = "",
    val date: Date = Date()

)