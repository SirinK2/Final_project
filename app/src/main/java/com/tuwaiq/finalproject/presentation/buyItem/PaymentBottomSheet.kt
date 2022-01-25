package com.tuwaiq.finalproject.presentation.buyItem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tuwaiq.finalproject.R
import com.tuwaiq.finalproject.databinding.PaymentBottomSheetBinding
import com.tuwaiq.finalproject.domain.model.Payment
import com.tuwaiq.finalproject.presentation.post.preview.ARGS_ITEM_DOC_KEY
import com.tuwaiq.finalproject.presentation.post.preview.ARGS_OWNER_KEY
import com.tuwaiq.finalproject.util.Constant.uid
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentBottomSheet:BottomSheetDialogFragment() {


    private lateinit var binding: PaymentBottomSheetBinding
    private val viewModel by viewModels<PaymentBottomSheetViewModel>()

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



    override fun onStart() {
        super.onStart()


        binding.buyBtn.setOnClickListener {
            val name = binding.userName.text.toString()
            val price = binding.itemPrice.text.toString()
            val buyerId = uid
            val sellerID = arguments?.getSerializable(ARGS_OWNER_KEY) as String
            val itemDocId = arguments?.getSerializable(ARGS_ITEM_DOC_KEY) as String


            val payment = Payment(name,price,buyerId, sellerID, itemDocId)

            viewModel.addPayment(payment)

        }


    }




}