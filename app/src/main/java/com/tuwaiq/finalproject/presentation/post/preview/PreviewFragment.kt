package com.tuwaiq.finalproject.presentation.post.preview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.tuwaiq.finalproject.databinding.ImageItemsBinding
import com.tuwaiq.finalproject.databinding.PreviewFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PreviewFragment : Fragment() {



    private val previewViewModel:PreviewViewModel by viewModels()


    private val args:PreviewFragmentArgs by navArgs()


    lateinit var binding: PreviewFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PreviewFragmentBinding.inflate(layoutInflater)



        binding.imageRv.layoutManager =
            LinearLayoutManager(context,
                LinearLayoutManager.HORIZONTAL,
                false)



        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        previewViewModel.getUserById(args.id).observe(
            viewLifecycleOwner,{ post ->

                binding.prevTitle.text = post?.title
                binding.prevDescrip.text = post?.description
                binding.prevPrice.text = post?.price
                binding.imageRv.adapter = post?.let { PhotoAdapter(it.photoUrl) }


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



    private inner class PhotosHolder(val binding: ImageItemsBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(photo: String){
            binding.imageView7.load(photo)
        }
    }

    private inner class PhotoAdapter(val photos: List<String>): RecyclerView.Adapter<PhotosHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosHolder {

            val binding = ImageItemsBinding.inflate(layoutInflater, parent, false)
            return PhotosHolder(binding)
        }

        override fun onBindViewHolder(holder: PhotosHolder, position: Int) {
            val photo = photos[position]
            holder.bind(photo)
        }

        override fun getItemCount(): Int = photos.size

    }



}