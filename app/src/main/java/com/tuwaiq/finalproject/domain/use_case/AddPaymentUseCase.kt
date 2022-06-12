package com.tuwaiq.finalproject.domain.use_case

import com.tuwaiq.finalproject.domain.model.Payment
import com.tuwaiq.finalproject.domain.repo.PostRepo
import javax.inject.Inject

class AddPaymentUseCase @Inject constructor(private val repo: PostRepo) {


    operator fun invoke(payment: Payment) =  repo.addPayment(payment)


}


