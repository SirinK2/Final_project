package com.tuwaiq.finalproject.features.post.presentation.newPost

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.tuwaiq.finalproject.databinding.ItemsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "ItemsFragment"
@AndroidEntryPoint
class ItemsFragment : Fragment() {



    private val viewModel: ItemsViewModel by viewModels()


    lateinit var binding: ItemsFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        binding = ItemsFragmentBinding.inflate(layoutInflater)



        binding.itemDoneBtn.setOnClickListener {

            val title = binding.itemsTitleEt.text.toString()
            val description = binding.itemsDescripEt.text.toString()
            val price = binding.itemsPriceEt.text.toString()

            viewModel.getLocation(requireContext(),title, description, price)
        }


        return binding.root



    }





}