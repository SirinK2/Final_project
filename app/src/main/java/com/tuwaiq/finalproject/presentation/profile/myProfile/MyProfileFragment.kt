package com.tuwaiq.finalproject.presentation.profile.myProfile

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tuwaiq.finalproject.R
import com.tuwaiq.finalproject.databinding.MyProfileFragmentBinding
import com.tuwaiq.finalproject.databinding.ProfilePostItemBinding
import com.tuwaiq.finalproject.domain.model.Post
import com.tuwaiq.finalproject.domain.model.User
import com.tuwaiq.finalproject.util.Constant.uid
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

        binding.myPostRv.layoutManager = GridLayoutManager(context, 2)


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
                Glide.with(requireContext())

                    .load(it.photoUrl)
                    .placeholder(R.drawable.ic_person)
                    .into(binding.myProfIv)


                Log.e(TAG, "onViewCreated: $it", )
            }
        )


        observe()
        binding.sales.setOnClickListener {
           observe()

        }

        binding.purchases.setOnClickListener {
            viewModel.myPurchases().observe(
                viewLifecycleOwner,{
                    binding.myPostRv.adapter = MyListAdapter(it)

                }
            )

        }



    }

    override fun onStart() {
        super.onStart()



        binding.updateProfile.setOnClickListener {
            val action = MyProfileFragmentDirections.actionMyProfileFragmentToEditProfileFragment(user.id)
            findNavController().navigate(action)
        }


        binding.settingBtn.setOnClickListener {
            findNavController().navigate(R.id.settingFragment)
        }
    }






    private inner class MyListAdapter(val posts: List<Post>)
        :RecyclerView.Adapter<MyListAdapter.MyListHolder>() {


        val mList : MutableList<Post> = posts as MutableList<Post>
        private inner class MyListHolder(val binding: ProfilePostItemBinding)
            :RecyclerView.ViewHolder(binding.root),View.OnClickListener{

            var post = Post()
            init {
                itemView.setOnClickListener(this)
            }
            fun bind(post: Post, index: Int){

                this.post = post
                binding.apply {

                    postTitleTv.text = post.title

                    post.photoUrl.forEach {
                        Glide.with(requireContext())
                            .load(it)
                            .into(postPhotoIv)
                    }

                }
                if (post.owner != uid){ binding.deleteIv.visibility = View.GONE }


                binding.deleteIv.setOnClickListener { deleteDialog(index) }


            }



            override fun onClick(v: View?) {
                if (v == itemView){
                    findNavController()
                        .navigate(MyProfileFragmentDirections
                            .actionMyProfileFragmentToPreviewFragment(post.id))
                }


            }

            private fun deleteDialog(index: Int){
                AlertDialog.Builder(requireContext()).apply {
                    setTitle(getString(R.string.delete_post))
                    setIcon(R.drawable.ic_baseline_cancel_24)
                    setMessage(getString(R.string.are_you_sure))
                    setPositiveButton(getString(R.string.yes_tv)){ _, _ ->
                        viewModel.deletePost(post.id).also {
                            mList.removeAt(index)
                            notifyDataSetChanged()
                        }
                    }
                    setNegativeButton(getString(R.string.no_tv),null)
                    show()
                }
            }
        }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyListHolder {
            val binding = ProfilePostItemBinding.inflate(layoutInflater,parent,false)
            return MyListHolder(binding)
        }

        override fun onBindViewHolder(holder: MyListHolder, position: Int) {
            val post = posts[position]
            holder.bind(post,position)
        }

        override fun getItemCount(): Int = posts.size
    }


}