package com.tuwaiq.finalproject.presentation.post.preview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.tuwaiq.finalproject.domain.model.Post
import com.tuwaiq.finalproject.util.Constant.postCollectionRef
import com.tuwaiq.finalproject.databinding.PreviewFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PreviewFragment : Fragment() {



    private val previewViewModel:PreviewViewModel by viewModels()


    private val args:PreviewFragmentArgs by navArgs()
    lateinit var id : String
    var post = Post()

    lateinit var binding: PreviewFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PreviewFragmentBinding.inflate(layoutInflater)







        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = previewViewModel



    }

    override fun onStart() {
        super.onStart()
        postCollectionRef.document(args.id).get().addOnSuccessListener {
            binding.viewModel?.post = it.toObject(Post::class.java)
            binding.prevTitle.text = it.getString("title")
            binding.prevDescrip.text = it.getString("description")
            binding.prevPrice.text = it.getString("price")


        }

        binding.userProf.setOnClickListener {
            val action = PreviewFragmentDirections.actionPreviewFragmentToUsersProfileFragment(post.owner)
            findNavController().navigate(action)
        }




    }



}