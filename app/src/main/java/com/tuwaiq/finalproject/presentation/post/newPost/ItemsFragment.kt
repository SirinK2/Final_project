package com.tuwaiq.finalproject.presentation.post.newPost

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.text.isDigitsOnly
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.tuwaiq.finalproject.R
import com.tuwaiq.finalproject.databinding.ImagePostItemsBinding
import com.tuwaiq.finalproject.databinding.ItemsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint


private const val TAG = "ItemsFragment"


@AndroidEntryPoint
class ItemsFragment : Fragment(){



    private val viewModel: ItemsViewModel by viewModels()

    private lateinit var binding: ItemsFragmentBinding

    private lateinit var rv: RecyclerView

    lateinit var photosUri: List<Uri>

    lateinit var photoUrl: List<String>

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

        rv = binding.photoRv

        binding.photoRv.layoutManager =
            GridLayoutManager(
                context,
                2,
                GridLayoutManager.HORIZONTAL,
            false)

        binding.photoRv.isHorizontalScrollBarEnabled = false

        binding.progressBar2.visibility = View.GONE

        return binding.root

    }


    override fun onStart() {
        super.onStart()
        binding.itemDoneBtn.setOnClickListener {

            val title = binding.itemsTitleEt.text.toString()
            val description = binding.itemsDescripEt.text.toString()
            val price = binding.itemsPriceEt.text.toString()
            val category = binding.autoCompleteTextView2.text.toString()

            when{
                title.trim().isEmpty() -> binding.titleTil.error = getString(R.string.add_item_title)
                description.trim().isEmpty() -> binding.descripTil.error = getString(R.string.add_description)
                price.trim().isEmpty() -> binding.priceTil.error = getString(R.string.add_post_price)
                category.contains(getString(R.string.choose_category)) -> binding.categoryEt.error = getString(R.string.add_post_category)
                !::photosUri.isInitialized -> Snackbar.make(binding.cameraBtn,getString(R.string.add_post_photos),Snackbar.LENGTH_LONG).show()
                else -> addPost(category, title, description, price).also {
                    binding.progressBar2.visibility = View.VISIBLE
                }


            }






        }

        binding.cameraBtn.setOnClickListener {

            getImageLauncher.launch(arrayOf("image/*"))

        }



    }

    private fun addPost(category:String, title:String, description:String, price:String){

        viewModel.uploadImg(photosUri).observe(
            viewLifecycleOwner,{ url ->

                photoUrl = url
                viewModel.addPost(requireContext(),category,title, description, price,photoUrl)
                findNavController().navigate(R.id.homePageFragment)
            }

        )


    }






    private inner class PhotoAdapter(val photos: List<Uri>):RecyclerView.Adapter<PhotoAdapter.PhotosHolder>(){


        val mPhotos : MutableList<Uri> = photos as MutableList<Uri>


        private inner class PhotosHolder(val binding: ImagePostItemsBinding):RecyclerView.ViewHolder(binding.root){
            fun bind(uri: Uri, position: Int){

                binding.apply {

                    itemPhotoIv.setImageURI(uri)
                    itemCancelBtn.setOnClickListener { deleteItem(position) }
                }


            }


        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosHolder {

            val binding = ImagePostItemsBinding.inflate(layoutInflater, parent, false)
            return PhotosHolder(binding)
        }

        override fun onBindViewHolder(holder: PhotosHolder, position: Int) {
            val photo = photos[position]
            holder.bind(photo,position)
        }

        override fun getItemCount(): Int = photos.size


        fun deleteItem(index: Int){
            mPhotos.removeAt(index)
            notifyDataSetChanged()
        }
    }






}