package com.tuwaiq.finalproject.features.post.presentation.newPost

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.tuwaiq.finalproject.R
import com.tuwaiq.finalproject.databinding.ItemsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.*

private const val TAG = "ItemsFragment"


@AndroidEntryPoint
class ItemsFragment : Fragment(){



    private val viewModel: ItemsViewModel by viewModels()

    private lateinit var binding: ItemsFragmentBinding


    lateinit var uris: Uri

    lateinit var photoUrl: String





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {





        binding = ItemsFragmentBinding.inflate(layoutInflater)

        val categories = resources.getStringArray(R.array.categories)
        val arrayAdapter = ArrayAdapter(requireContext(),R.layout.dorpdown_items,categories)
        binding.autoCompleteTextView2.setAdapter(arrayAdapter)




        binding.itemDoneBtn.setOnClickListener {

            val title = binding.itemsTitleEt.text.toString()
            val description = binding.itemsDescripEt.text.toString()
            val price = binding.itemsPriceEt.text.toString()
            val category = binding.autoCompleteTextView2.text.toString()

            val x = viewModel.uploadImg(uris)






//            CoroutineScope(Dispatchers.IO).launch {
//                val fileName = "${UUID.randomUUID()}.jpg"
//                val imgRef = Firebase.storage.reference.child("images/$fileName")
//
//                imgRef.putFile(uris).await()
//            }
            viewModel.addPost(requireContext(),category,title, description, price)

        }

        binding.cameraBtn.setOnClickListener {

            getImageLauncher.launch(arrayOf("image/*"))

        }


        return binding.root



    }

    private val getImageLauncher =
        registerForActivityResult(
            ActivityResultContracts
                .OpenMultipleDocuments() ){
            it.forEach { uri ->

                uris =  uri
            }

        }




}