package com.tuwaiq.finalproject.presentation.homepage

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.tuwaiq.finalproject.R
import com.tuwaiq.finalproject.domain.model.Post
import com.tuwaiq.finalproject.domain.use_case.GetPostUseCase
import com.tuwaiq.finalproject.util.Constant.mAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

private const val TAG = "HomePageViewModel"
@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val getPostUseCase: GetPostUseCase
    ) : ViewModel() {



    fun getPost(
        context: Context,
        dist: Float): LiveData<List<Post>>
    = liveData(Dispatchers.IO) {
          emit(getPostUseCase(context, dist))
    }


    fun carsBtn(){
        mAdapter.filter.filter("vehicles")

    }
    fun electronicBtn(){
        mAdapter.filter.filter("electronic")


    }
    fun furnitureBtn(){
        mAdapter.filter.filter("furniture")

    }
    fun clothesBtn(){
        mAdapter.filter.filter("clothes")
    }
    fun realEstateBtn(){
        mAdapter.filter.filter("real estate")
    }




}



