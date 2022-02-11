package com.tuwaiq.finalproject.domain.model

import java.util.*


data class Chat (
    var senderId:String = "",
    var receiverID:String = "",
    var message:String = "",
    val date: Date = Date(),
    var docId:String = ""



)