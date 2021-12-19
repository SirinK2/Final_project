package com.tuwaiq.finalproject.features.post.presentation.newPost

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import com.tuwaiq.finalproject.R
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

        val categories = resources.getStringArray(R.array.categories)
        val arrayAdapter = ArrayAdapter(requireContext(),R.layout.dorpdown_items,categories)
        binding.autoCompleteTextView2.setAdapter(arrayAdapter)


        binding.itemDoneBtn.setOnClickListener {

            val title = binding.itemsTitleEt.text.toString()
            val description = binding.itemsDescripEt.text.toString()
            val price = binding.itemsPriceEt.text.toString()
            val category = binding.autoCompleteTextView2.text.toString()


            viewModel.addPost(requireContext(),category,title, description, price)
//            viewModel.savePost(requireContext(),category,title, description, price)
        }


        return binding.root



    }





}