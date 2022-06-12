package com.tuwaiq.finalproject.presentation.homepage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tuwaiq.finalproject.R
import com.tuwaiq.finalproject.databinding.FilterBottomSheetBinding
import com.tuwaiq.finalproject.util.Constant.mAdapter
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "FilterBottomSheet"
@AndroidEntryPoint
class FilterBottomSheet: BottomSheetDialogFragment() {

    private lateinit var binding: FilterBottomSheetBinding

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

        binding = FilterBottomSheetBinding.inflate(layoutInflater)


        return binding.root


    }




    override fun onStart() {
        super.onStart()

        binding.threeKm.setOnClickListener {
//            observe(3000.0f)
        }


        binding.fiveKm.setOnClickListener {
//            observe(5000.0f)

        }




        binding.cars.setOnClickListener { mAdapter.filter.filter("vehicles") }

        binding.electronic.setOnClickListener {   mAdapter.filter.filter("electronic")  }

        binding.furniture.setOnClickListener { mAdapter.filter.filter("furniture") }

        binding.clothes.setOnClickListener { mAdapter.filter.filter("clothes") }

        binding.realestate.setOnClickListener { mAdapter.filter.filter("real estate")  }





    }


}