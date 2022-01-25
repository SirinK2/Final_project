package com.tuwaiq.finalproject.domain.model

data class Payment (
    var buyerName: String = "",
    var price: String = "",
    var buyerId: String = "",
    var sellerId: String = "",
    var itemDocId:String = "",
    var paymentID:String = "",
    var code: String = ""
)