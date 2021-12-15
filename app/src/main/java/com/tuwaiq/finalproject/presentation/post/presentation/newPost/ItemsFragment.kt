package com.tuwaiq.finalproject.presentation.post.presentation.newPost

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.tuwaiq.finalproject.core.data.firebase.CurrentLocation
import com.tuwaiq.finalproject.core.data.firebase.Post
import com.tuwaiq.finalproject.databinding.ItemsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "ItemsFragment"
@AndroidEntryPoint
class ItemsFragment : Fragment() {



    private val viewModel: ItemsViewModel by viewModels()
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val currentLocation = CurrentLocation()

    var latitude = currentLocation.latitude
    var longitude = currentLocation.longitude

    lateinit var binding: ItemsFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        binding = ItemsFragmentBinding.inflate(layoutInflater)



        binding.itemDoneBtn.setOnClickListener {




            fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())

            if (ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return@setOnClickListener
            }
                val title = binding.itemsTitleEt.text.toString()
                val description = binding.itemsDescripEt.text.toString()
                val price = binding.itemsPriceEt.text.toString()
                val locations = CurrentLocation(latitude, longitude)

                val post = Post(title,description,price,locations)
                fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                    if (location != null) {

                        latitude = location.latitude
                        longitude = location.longitude
                    }
                    Log.d(TAG, " from item fragment ")

                }
                viewModel.savePost(post)

        }


        return binding.root



    }





}