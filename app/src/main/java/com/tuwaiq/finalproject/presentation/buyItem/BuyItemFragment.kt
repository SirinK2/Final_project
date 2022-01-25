package com.tuwaiq.finalproject.presentation.buyItem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.tuwaiq.finalproject.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BuyItemFragment : Fragment() {



    private val viewModel by viewModels<BuyItemViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.buy_item_fragment, container, false)
    }



}