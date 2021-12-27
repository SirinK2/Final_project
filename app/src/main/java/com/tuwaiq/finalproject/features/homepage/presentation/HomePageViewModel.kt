package com.tuwaiq.finalproject.features.homepage.presentation

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.tuwaiq.finalproject.core.domain.model.Post
import com.tuwaiq.finalproject.core.domain.use_case.GetPostUseCase
import com.tuwaiq.finalproject.core.util.Constant.mAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

private const val TAG = "HomePageViewModel"
@HiltViewModel
class HomePageViewModel @Inject constructor(private val getPostUseCase: GetPostUseCase) : ViewModel() {

    fun getPost(context: Context, dist: Float): LiveData<List<Post>> = liveData(Dispatchers.IO) {
          emit(getPostUseCase(context, dist))
    }


    fun btn(){
        mAdapter.filter.filter("cars")
    }






}