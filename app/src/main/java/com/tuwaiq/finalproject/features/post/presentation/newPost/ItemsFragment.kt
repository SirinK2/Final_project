package com.tuwaiq.finalproject.features.post.presentation.newPost

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import com.tuwaiq.finalproject.R
import com.tuwaiq.finalproject.core.util.CameraImagePicker
import com.tuwaiq.finalproject.core.util.Constant.imgFile
import com.tuwaiq.finalproject.databinding.ItemsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import io.ak1.pix.models.Flash
import io.ak1.pix.models.Mode
import io.ak1.pix.models.Options
import io.ak1.pix.models.Ratio

private const val TAG = "ItemsFragment"
private const val REQUEST_CODE = 0
@AndroidEntryPoint
class ItemsFragment : Fragment() {



    private val viewModel: ItemsViewModel by viewModels()

    private lateinit var binding: ItemsFragmentBinding







    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        binding = ItemsFragmentBinding.inflate(layoutInflater)

        val categories = resources.getStringArray(R.array.categories)
        val arrayAdapter = ArrayAdapter(requireContext(),R.layout.dorpdown_items,categories)
        binding.autoCompleteTextView2.setAdapter(arrayAdapter)

//        binding.cameraBtn.setOnClickListener {
////            Intent(Intent.ACTION_PICK).also {
////                it.type = "image/*"
////                startActivityForResult(it, REQUEST_CODE)
////            }
//
//        }

        binding.itemDoneBtn.setOnClickListener {

            val title = binding.itemsTitleEt.text.toString()
            val description = binding.itemsDescripEt.text.toString()
            val price = binding.itemsPriceEt.text.toString()
            val category = binding.autoCompleteTextView2.text.toString()


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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE){
            data?.data?.let {
                imgFile = it
                binding.imageView2.setImageURI(it)
            }
        }
    }








}