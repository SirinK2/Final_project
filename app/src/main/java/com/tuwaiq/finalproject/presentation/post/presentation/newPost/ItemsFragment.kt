package com.tuwaiq.finalproject.presentation.post.presentation.newPost

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.tuwaiq.finalproject.databinding.ItemsFragmentBinding
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class ItemsFragment : Fragment() {



    private val viewModel: ItemsViewModel by viewModels()

    lateinit var binding: ItemsFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ItemsFragmentBinding.inflate(layoutInflater)




        return binding.root



    }



}