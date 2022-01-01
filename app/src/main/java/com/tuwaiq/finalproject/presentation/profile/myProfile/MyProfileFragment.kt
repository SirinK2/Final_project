package com.tuwaiq.finalproject.presentation.profile.myProfile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.tuwaiq.finalproject.R
import com.tuwaiq.finalproject.databinding.HomePageItemsBinding
import com.tuwaiq.finalproject.databinding.MyProfileFragmentBinding
import com.tuwaiq.finalproject.domain.model.Post
import com.tuwaiq.finalproject.util.Constant.postCollectionRef
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.tasks.await

@AndroidEntryPoint
class MyProfileFragment : Fragment() {



    private val viewModel: MyProfileViewModel by viewModels()

    lateinit var binding: MyProfileFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MyProfileFragmentBinding.inflate(layoutInflater)

        binding.myPostRv.layoutManager = LinearLayoutManager(context)

        binding.myProfNameTv.text = Firebase.auth.currentUser?.displayName


        return binding.root
    }

    private fun observe(){
        viewModel.myPost().observe(
            viewLifecycleOwner, {
                binding.myPostRv.adapter = MyListAdapter(it)

            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe()
        binding.sales.setOnClickListener {
           observe()

        }

    }

    override fun onStart() {
        super.onStart()

        binding.settingBtn.setOnClickListener {
            findNavController().navigate(R.id.settingFragment)
        }
    }


    private inner class MyListHolder(val binding: HomePageItemsBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(post: Post){
            binding.homeTitleTv.text = post.title
            binding.homePriceTv.text = post.price
        }
    }

    private inner class MyListAdapter(val posts: List<Post>):RecyclerView.Adapter<MyListHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyListHolder {
            val binding = HomePageItemsBinding.inflate(layoutInflater,parent,false)
            return MyListHolder(binding)
        }

        override fun onBindViewHolder(holder: MyListHolder, position: Int) {
            val post = posts[position]
            holder.bind(post)
        }

        override fun getItemCount(): Int = posts.size
    }


}