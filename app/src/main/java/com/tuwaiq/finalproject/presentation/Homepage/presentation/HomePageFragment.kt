package com.tuwaiq.finalproject.presentation.Homepage.presentation

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tuwaiq.finalproject.core.Test
import com.tuwaiq.finalproject.databinding.HomePageFragmentBinding
import com.tuwaiq.finalproject.databinding.HomePageItemsBinding

class HomePageFragment : Fragment() {



    private val homePageViewModel by lazy { ViewModelProvider(this)[HomePageViewModel::class.java] }

    private lateinit var binding : HomePageFragmentBinding






    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = HomePageFragmentBinding.inflate(layoutInflater)

        binding.homeRv.layoutManager = GridLayoutManager(context,2)
        binding.homeRv.adapter = HomeAdapter(homePageViewModel.items)


        return binding.root

    }

    override fun onStart() {
        super.onStart()
        val navCon = findNavController()
        binding.homeProfileBtn.setOnClickListener {
            val action = HomePageFragmentDirections.actionHomePageFragmentToMyProfileFragment()
            navCon.navigate(action)
        }

        binding.floatingActionButton.setOnClickListener {
            val action = HomePageFragmentDirections.actionHomePageFragmentToItemsFragment()
            navCon.navigate(action)
        }

        binding.homeDmBtn.setOnClickListener {
            val action = HomePageFragmentDirections.actionHomePageFragmentToDirectMessageFragment()
            navCon.navigate(action)
        }
    }


    private inner class HomeHolder(val binding: HomePageItemsBinding):RecyclerView.ViewHolder(binding.root), View.OnClickListener{

        fun bind(test: Test){
            binding.homeTitleTv.text= test.title
        }

        override fun onClick(v: View?) {
            if (v == itemView){
                val navCon1 =  findNavController()
                val action = HomePageFragmentDirections.actionHomePageFragmentToPreviewFragment()
                navCon1.navigate(action)
            }
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