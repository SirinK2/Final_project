package com.tuwaiq.finalproject.ui.Homepage

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tuwaiq.finalproject.R
import com.tuwaiq.finalproject.database.Test
import com.tuwaiq.finalproject.databinding.HomePageFragmentBinding
import com.tuwaiq.finalproject.databinding.HomePageItemsBinding

class HomePageFragment : Fragment() {



    private val homePageViewModel by lazy { ViewModelProvider(this)[HomePageViewModel::class.java] }

    lateinit var binding : HomePageFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = HomePageFragmentBinding.inflate(layoutInflater)

        binding.homeRv.layoutManager = GridLayoutManager(context,2)
        binding.homeRv.adapter = HomeAdapter(homePageViewModel.items)


        return binding.root

    }


    private inner class HomeHolder(val binding: HomePageItemsBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(test: Test){
            binding.textView.text = test.title
        }

    }


    private inner class HomeAdapter(val items: List<Test>): RecyclerView.Adapter<HomeHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {
            val binding = HomePageItemsBinding.inflate(
                layoutInflater,
                parent,
                false
            )

            return HomeHolder(binding)



        }

        override fun onBindViewHolder(holder: HomeHolder, position: Int) {
           val item = items[position]
            holder.bind(item)
        }

        override fun getItemCount(): Int = items.size
    }


}