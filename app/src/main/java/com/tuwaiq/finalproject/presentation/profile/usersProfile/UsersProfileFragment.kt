package com.tuwaiq.finalproject.presentation.profile.usersProfile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tuwaiq.finalproject.databinding.HomePageItemsBinding
import com.tuwaiq.finalproject.databinding.UsersProfileFragmentBinding
import com.tuwaiq.finalproject.domain.model.Post
import com.tuwaiq.finalproject.util.Constant
import com.tuwaiq.finalproject.util.Constant.postCollectionRef
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.tasks.await

@AndroidEntryPoint
class UsersProfileFragment : Fragment() {


    private val usersProfileViewModel by viewModels<UsersProfileViewModel>()

    private val args: UsersProfileFragmentArgs by navArgs()


    private lateinit var binding: UsersProfileFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = UsersProfileFragmentBinding.inflate(layoutInflater)

        binding.usersProfRv.layoutManager = GridLayoutManager(context, 2)




        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        usersProfileViewModel.myPost(args.owner).observe(
            viewLifecycleOwner, {
                binding.usersProfRv.adapter = ProfileAdapter(it)

            }
        )
    }


    private inner class ProfileHolder(val binding: HomePageItemsBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(post: Post){
            binding.homeTitleTv.text = post.title
            binding.homePriceTv.text = post.price
        }
    }


    private inner class ProfileAdapter(val posts: List<Post>):RecyclerView.Adapter<ProfileHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileHolder {
            val binding = HomePageItemsBinding.inflate(
                layoutInflater,
                parent,
                false
            )
            return ProfileHolder(binding)
        }

        override fun onBindViewHolder(holder: ProfileHolder, position: Int) {
            val post = posts[position]
            holder.bind(post)
        }

        override fun getItemCount(): Int = posts.size

    }




}