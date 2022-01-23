package com.tuwaiq.finalproject.presentation.post.preview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.bumptech.glide.Glide
import com.tuwaiq.finalproject.databinding.PreviewFragmentBinding
import com.tuwaiq.finalproject.databinding.PreviewImageItemBinding
import com.tuwaiq.finalproject.domain.model.User
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PreviewFragment : Fragment() {



    private val previewViewModel:PreviewViewModel by viewModels()

    private var snapHelper: SnapHelper = LinearSnapHelper()

    private val args:PreviewFragmentArgs by navArgs()

    private lateinit var user: User




    lateinit var binding: PreviewFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PreviewFragmentBinding.inflate(layoutInflater)

        user = User()
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
                    user.authId = post.owner
                    binding.apply {
                        prevTitle.text = post.title
                        prevDescrip.text = post.description
                        prevPrice.text = post.price
                        imageRv.adapter = PhotoAdapter(post.photoUrl)
                    }
                }
            }
        )

        previewViewModel.getUser().observe(
            viewLifecycleOwner,{ user ->
                binding.apply {
                    prevUsername.text = user.name
                    Glide.with(requireContext()).load(user.photoUrl).into(prevUserIv)
                     user.ratings.forEach{

                         ratingBar.rating = it.rating
                     }
                }
            }
        )




    }

    override fun onStart() {
        super.onStart()



//        binding.userProf.setOnClickListener {
//            val action = PreviewFragmentDirections.actionPreviewFragmentToUsersProfileFragment(post.owner)
//            findNavController().navigate(action)
//        }




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