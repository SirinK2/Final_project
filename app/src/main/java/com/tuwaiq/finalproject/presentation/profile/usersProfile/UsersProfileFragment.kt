package com.tuwaiq.finalproject.presentation.profile.usersProfile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tuwaiq.finalproject.R
import com.tuwaiq.finalproject.databinding.HomePageItemsBinding
import com.tuwaiq.finalproject.databinding.ProfilePostItemBinding
import com.tuwaiq.finalproject.databinding.UsersProfileFragmentBinding
import com.tuwaiq.finalproject.domain.model.Post
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "UsersProfileFragment"
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


        Log.e(TAG, "onViewCreated: ${args.owner}", )
        usersProfileViewModel.userPost(args.owner).observe(
            viewLifecycleOwner, {


                binding.usersProfRv.adapter = ProfileAdapter(it)
                Log.e(TAG, "onViewCreated: $it", )

                usersProfileViewModel.getUser(args.owner).observe(
                    viewLifecycleOwner,{ user ->
                        binding.apply {
                            Glide.with(requireContext())
                                .load(user.photoUrl)
                                .placeholder(R.drawable.ic_person)
                                .into(usersProfIv)
                            usersProfNameTv.text = user.name
                            Log.d(TAG, "onViewCreated: ${user.name}// ${user.authId}")
                            usersProfBioTv.text = user.bio
                        }

                    }
                )

            }
        )


    }


    private inner class ProfileHolder(val binding: ProfilePostItemBinding)
        :RecyclerView.ViewHolder(binding.root), View.OnClickListener{
        var post = Post()
        init {
            itemView.setOnClickListener(this)
            binding.deleteIv.visibility = View.GONE
        }
        fun bind(post: Post){

            this.post = post
            binding.apply {

                post.photoUrl.forEach {
                    Glide.with(requireContext())
                        .load(it)
                        .into(postPhotoIv)
                }
                postTitleTv.text = post.title
            }

        }

        override fun onClick(v: View?) {

            if (v == itemView){

                findNavController()
                    .navigate(
                    UsersProfileFragmentDirections
                        .actionUsersProfileFragmentToPreviewFragment(post.id)
                    )
            }
        }
    }


    private inner class ProfileAdapter(val posts: List<Post>):RecyclerView.Adapter<ProfileHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileHolder {
            val binding = ProfilePostItemBinding.inflate(
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