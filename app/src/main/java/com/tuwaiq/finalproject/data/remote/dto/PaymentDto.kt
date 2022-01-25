package com.tuwaiq.finalproject.data.remote.dto

import com.tuwaiq.finalproject.domain.model.Payment

data class PaymentDto (
    var buyerName: String,
    var price: String,
    var buyerId: String,
    var sellerId: String,
    var itemDocId: String
        )


fun PaymentDto.toPayment(): Payment{
    return Payment(
        buyerName = buyerName,
        price = price,
        buyerId = buyerId,
        sellerId = sellerId,
        itemDocId = itemDocId
    )
}