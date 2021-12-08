package com.tuwaiq.finalproject.ui.addItem

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tuwaiq.finalproject.R
import com.tuwaiq.finalproject.databinding.ItemsFragmentBinding

class ItemsFragment : Fragment() {



    private val itemsViewModel by lazy { ViewModelProvider(this)[ItemsViewModel::class.java] }

    lateinit var binding: ItemsFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ItemsFragmentBinding.inflate(layoutInflater)
        return binding.root
    }



}