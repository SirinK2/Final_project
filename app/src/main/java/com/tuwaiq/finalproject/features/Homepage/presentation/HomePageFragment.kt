package com.tuwaiq.finalproject.features.Homepage.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tuwaiq.finalproject.core.domain.model.Post
import com.tuwaiq.finalproject.databinding.HomePageFragmentBinding
import com.tuwaiq.finalproject.databinding.HomePageItemsBinding
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "HomePageFragment"
@AndroidEntryPoint
class HomePageFragment : Fragment() {



    private val homePageViewModel: HomePageViewModel by viewModels()

    private lateinit var binding : HomePageFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = HomePageFragmentBinding.inflate(layoutInflater)

        binding.homeRv.layoutManager = GridLayoutManager(context,2)



        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homePageViewModel.getPost().observe(
            viewLifecycleOwner, {
                binding.homeRv.adapter = HomeAdapter(it)

                Log.d(TAG, "onCreate: ${it.size} ")
            }
        )
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


        init {
            itemView.setOnClickListener(this)
        }

        fun bind(post: Post){
//            binding.viewModel?.post = post
            binding.homeTitleTv.text = post.title
            binding.homePriceTv.text = post.price
        }

        override fun onClick(v: View?) {
            if (v == itemView){
                val navCon1 =  findNavController()
                val action = HomePageFragmentDirections.actionHomePageFragmentToPreviewFragment()
                navCon1.navigate(action)
            }
        }

    }



    private inner class HomeAdapter(val posts: List<Post>): RecyclerView.Adapter<HomeHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {
            val binding = HomePageItemsBinding.inflate(
                layoutInflater,
                parent,
                false
            )

            return HomeHolder(binding)



        }

        override fun onBindViewHolder(holder: HomeHolder, position: Int) {
           val post = posts[position]
            holder.bind(post)
        }

        override fun getItemCount(): Int = posts.size
    }


}