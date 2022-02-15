package com.tuwaiq.finalproject.presentation.profile.myProfile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.tuwaiq.finalproject.domain.model.Post
import com.tuwaiq.finalproject.domain.model.User
import com.tuwaiq.finalproject.domain.use_case.DeletePostUseCase
import com.tuwaiq.finalproject.domain.use_case.GetCurrentUserUseCase
import com.tuwaiq.finalproject.domain.use_case.GetPurchasesUseCase
import com.tuwaiq.finalproject.domain.use_case.MyPostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

private const val TAG = "MyProfileViewModel"
@HiltViewModel
class MyProfileViewModel@Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val getMyPostUseCase: MyPostUseCase,
    private val deletePostUseCase: DeletePostUseCase,
    private val getPurchasesUseCase: GetPurchasesUseCase
) : ViewModel() {


    fun getUser():LiveData<User> = liveData(Dispatchers.IO){
        emit(getCurrentUserUseCase())
    }


    fun myPost():LiveData<List<Post>> = liveData (Dispatchers.IO){
        emit(getMyPostUseCase())
    }

    fun deletePost(id:String) = deletePostUseCase(id)

    fun myPurchases(): LiveData<List<Post>> = liveData (Dispatchers.IO){  emit(getPurchasesUseCase()) }



}