package com.tuwaiq.finalproject.presentation.post.presentation.Preview

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tuwaiq.finalproject.databinding.PreviewFragmentBinding

class PreviewFragment : Fragment() {



    private val previewViewModel by lazy { ViewModelProvider(this)[PreviewViewModel::class.java]}

    lateinit var binding: PreviewFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PreviewFragmentBinding.inflate(layoutInflater)
        return binding.root
    }



}