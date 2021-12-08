package com.tuwaiq.finalproject.ui.setting

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tuwaiq.finalproject.R
import com.tuwaiq.finalproject.databinding.SettingFragmentBinding

class SettingFragment : Fragment() {



    private val settingViewModel by lazy { ViewModelProvider(this)[SettingViewModel::class.java] }

    lateinit var binding: SettingFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       binding = SettingFragmentBinding.inflate(layoutInflater)
        return binding.root
    }



}