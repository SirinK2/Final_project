package com.tuwaiq.finalproject.presentation.auth.presentation.singIn

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tuwaiq.finalproject.R

class SingInFragment : Fragment() {

    companion object {
        fun newInstance() = SingInFragment()
    }

    private lateinit var viewModel: SingInViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.sing_in_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SingInViewModel::class.java)
        // TODO: Use the ViewModel
    }

}