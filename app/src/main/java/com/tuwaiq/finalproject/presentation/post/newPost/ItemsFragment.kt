package com.tuwaiq.finalproject.presentation.post.newPost

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.tuwaiq.finalproject.R
import com.tuwaiq.finalproject.databinding.ItemsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.tuwaiq.finalproject.databinding.ImageItemsBinding


private const val TAG = "ItemsFragment"


@AndroidEntryPoint
class ItemsFragment : Fragment(){



    private val viewModel: ItemsViewModel by viewModels()

    private lateinit var binding: ItemsFragmentBinding


    lateinit var photosUri: List<Uri>

    lateinit var photoUrl: String

    private val getImageLauncher =
        registerForActivityResult(
            ActivityResultContracts
                .OpenMultipleDocuments() ){
            binding.photoRv.adapter = PhotoAdapter(it)
            photosUri = it

        }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = ItemsFragmentBinding.inflate(layoutInflater)

        val categories = resources.getStringArray(R.array.categories)
        val arrayAdapter = ArrayAdapter(requireContext(),R.layout.dorpdown_items,categories)
        binding.autoCompleteTextView2.setAdapter(arrayAdapter)


        binding.photoRv.layoutManager =
            LinearLayoutManager(context,
            LinearLayoutManager.HORIZONTAL,
            false)

        return binding.root

    }



    override fun onStart() {
        super.onStart()
        binding.itemDoneBtn.setOnClickListener {

            val title = binding.itemsTitleEt.text.toString()
            val description = binding.itemsDescripEt.text.toString()
            val price = binding.itemsPriceEt.text.toString()
            val category = binding.autoCompleteTextView2.text.toString()


            photosUri.forEach {
                viewModel.uploadImg(it)
            }


            viewModel.addPost(requireContext(),category,title, description, price)

        }

        binding.cameraBtn.setOnClickListener {

            getImageLauncher.launch(arrayOf("image/*"))

        }

    }




    private inner class PhotosHolder(val binding: ImageItemsBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(uri: Uri){
            binding.imageView7.setImageURI(uri)
        }
    }

    private inner class PhotoAdapter(val photos: List<Uri>):RecyclerView.Adapter<PhotosHolder>(){
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