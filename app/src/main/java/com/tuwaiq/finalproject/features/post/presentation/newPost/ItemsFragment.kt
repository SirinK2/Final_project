package com.tuwaiq.finalproject.features.post.presentation.newPost

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.tuwaiq.finalproject.R
import com.tuwaiq.finalproject.core.util.CameraImagePicker
import com.tuwaiq.finalproject.databinding.ItemsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.*

private const val TAG = "ItemsFragment"


@AndroidEntryPoint
class ItemsFragment : Fragment(), CameraImagePicker.CallBack {



    private val viewModel: ItemsViewModel by viewModels()

    private lateinit var binding: ItemsFragmentBinding


    var b = mutableListOf<Uri>()




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

            CoroutineScope(Dispatchers.IO).launch {
                uri()
            }


            viewModel.addPost(requireContext(),category,title, description, price)

        }

        binding.cameraBtn.setOnClickListener {
            val args = Bundle()

            val bottomSheet = CameraImagePicker()
            bottomSheet.arguments = args

            bottomSheet.show(parentFragmentManager, bottomSheet.tag)




        }


        return binding.root



    }
    override suspend fun uri(a: List<Uri>) {


       a.forEach {

           val fileName = "${UUID.randomUUID()}.jpg"
           val imgRef = Firebase.storage.reference.child("images/$fileName")

           imgRef.putFile(it).await()
           Log.d(TAG, "uri: $it")
       }


    }


}