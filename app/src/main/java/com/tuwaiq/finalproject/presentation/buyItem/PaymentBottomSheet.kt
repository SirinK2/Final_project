package com.tuwaiq.finalproject.presentation.buyItem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tuwaiq.finalproject.R
import com.tuwaiq.finalproject.databinding.PaymentBottomSheetBinding

class PaymentBottomSheet:BottomSheetDialogFragment() {


    private lateinit var binding: PaymentBottomSheetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(
            STYLE_NORMAL,
            R.style.AppBottomSheetDialogTheme
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = PaymentBottomSheetBinding.inflate(layoutInflater)
        return binding.root


    }




}