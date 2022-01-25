package com.tuwaiq.finalproject.presentation.post.preview

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.bumptech.glide.Glide
import com.tuwaiq.finalproject.R
import com.tuwaiq.finalproject.databinding.PreviewFragmentBinding
import com.tuwaiq.finalproject.databinding.PreviewImageItemBinding
import com.tuwaiq.finalproject.domain.model.Post
import com.tuwaiq.finalproject.domain.model.User
import com.tuwaiq.finalproject.presentation.buyItem.PaymentBottomSheet
import com.tuwaiq.finalproject.util.Constant.uid
import dagger.hilt.android.AndroidEntryPoint
const val ARGS_OWNER_KEY = "args kay"
const val ARGS_ITEM_DOC_KEY = "item doc id"
private const val TAG = "PreviewFragment"
@AndroidEntryPoint
class PreviewFragment : Fragment() {



    private val previewViewModel:PreviewViewModel by viewModels()

    private var snapHelper: SnapHelper = LinearSnapHelper()

    private val args:PreviewFragmentArgs by navArgs()

    private lateinit var paymentBottomSheet: PaymentBottomSheet

    private lateinit var users: User

    private lateinit var post: Post





    lateinit var binding: PreviewFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PreviewFragmentBinding.inflate(layoutInflater)

        users = User()
        binding.apply {

            imageRv.layoutManager =
                LinearLayoutManager(context,
                    LinearLayoutManager.HORIZONTAL,
                    false)

            snapHelper.attachToRecyclerView(imageRv)

            imageRv.isHorizontalScrollBarEnabled = false
        }



        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        previewViewModel.getPostById(args.id).observe(
            viewLifecycleOwner,{
                it?.let { post ->

                    this.post = post

                    users.authId = post.owner
//                    Log.e(TAG, "onViewCreated: post owner ${post.owner}", )
                    Log.e(TAG, "onViewCreated: ${users.authId}", )
                    binding.apply {
                        prevTitle.text = post.title
                        prevDescrip.text = post.description
                        prevPrice.text = post.price
                        imageRv.adapter = PhotoAdapter(post.photoUrl)
                    }
                    previewViewModel.getUser(users.authId).observe(
                        viewLifecycleOwner,{ user ->
                            Log.e(TAG, "onViewCreated: $user", )

                            binding.apply {
                                Log.e(TAG, "onViewCreated: post owner ${users.authId}", )

                                prevUsername.text = user.name
                                Glide.with(requireContext()).load(user.photoUrl).into(prevUserIv)

                            }
                        }
                    )
                }
            }
        )







    }

    override fun onStart() {
        super.onStart()


        binding.paymentBtn.setOnClickListener {
//            val args = Bundle()
//            args.putSerializable(ARGS_OWNER_KEY, post.owner)
//            args.putSerializable(ARGS_ITEM_DOC_KEY,post.id)
//            paymentBottomSheet = PaymentBottomSheet()
//            paymentBottomSheet.arguments = args
//            paymentBottomSheet.show(parentFragmentManager,paymentBottomSheet.tag)

            findNavController().navigate(PreviewFragmentDirections.actionPreviewFragmentToChatFragment(users.authId))
        }


        binding.prevUserIv.setOnClickListener {
            navToUserProfile()
        }

        binding.prevUsername.setOnClickListener {
            navToUserProfile()
        }






    }

    private fun navToUserProfile(){
        if (uid == users.authId){
            findNavController().navigate(R.id.myProfileFragment)
            Log.e(TAG, "onStart: from my profile ${users.authId}")
        }else{
            val action = PreviewFragmentDirections.actionPreviewFragmentToUsersProfileFragment(users.authId)
            findNavController().navigate(action)
            Log.e(TAG, "onStart: from user profile ${users.authId}")
        }

    }




    private inner class PhotosHolder(val binding: PreviewImageItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(photo: String){

            Glide.with(requireContext())
                .load(photo)
                .into(binding.previewIv)

        }
    }

    private inner class PhotoAdapter(val photos: List<String>): RecyclerView.Adapter<PhotosHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosHolder {

            val binding = PreviewImageItemBinding.inflate(layoutInflater, parent, false)
            return PhotosHolder(binding)
        }

        override fun onBindViewHolder(holder: PhotosHolder, position: Int) {
            val photo = photos[position]
            holder.bind(photo)
        }

        override fun getItemCount(): Int = photos.size

    }



}