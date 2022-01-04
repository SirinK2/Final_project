package com.tuwaiq.finalproject.presentation.profile.myProfile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.tuwaiq.finalproject.R
import com.tuwaiq.finalproject.databinding.HomePageItemsBinding
import com.tuwaiq.finalproject.databinding.MyProfileFragmentBinding
import com.tuwaiq.finalproject.domain.model.Post
import com.tuwaiq.finalproject.domain.model.User
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "MyProfileFragment"
@AndroidEntryPoint
class MyProfileFragment : Fragment() {



    private val viewModel: MyProfileViewModel by viewModels()

    lateinit var binding: MyProfileFragmentBinding

    private var user = User()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MyProfileFragmentBinding.inflate(layoutInflater)

        binding.myPostRv.layoutManager = LinearLayoutManager(context)

        binding.myProfIv.load(R.drawable.ic_person)

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
        viewModel.getUser().observe(
            viewLifecycleOwner,{
                user = it
                binding.myProfNameTv.text = it.name
                binding.myProfBioTv.text = it.bio
                binding.myProfIv.load(it.photoUrl)
                Log.e(TAG, "onViewCreated: $it", )
            }
        )


        observe()
        binding.sales.setOnClickListener {
           observe()

        }

    }

    override fun onStart() {
        super.onStart()


        binding.settingBtn.setOnClickListener {
            val action = MyProfileFragmentDirections.actionMyProfileFragmentToEditProfileFragment(user.id)
            findNavController().navigate(action)
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