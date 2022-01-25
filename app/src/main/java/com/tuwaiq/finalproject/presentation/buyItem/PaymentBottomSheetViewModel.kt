package com.tuwaiq.finalproject.presentation.buyItem

import androidx.lifecycle.ViewModel
import com.tuwaiq.finalproject.domain.model.Payment
import com.tuwaiq.finalproject.domain.use_case.AddPaymentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PaymentBottomSheetViewModel @Inject constructor(
    private val addPaymentUseCase: AddPaymentUseCase
    ): ViewModel() {

        fun addPayment(payment: Payment) = addPaymentUseCase(payment)




}