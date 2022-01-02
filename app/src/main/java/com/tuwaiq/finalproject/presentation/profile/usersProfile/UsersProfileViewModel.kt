package com.tuwaiq.finalproject.presentation.profile.usersProfile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.tuwaiq.finalproject.domain.model.Post
import com.tuwaiq.finalproject.domain.use_case.MyPostUseCase
import com.tuwaiq.finalproject.util.Constant.postCollectionRef
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class UsersProfileViewModel @Inject constructor(private val getMyPostUseCase: MyPostUseCase) : ViewModel() {


    fun myPost(args:String): LiveData<List<Post>> = liveData {
        postCollectionRef.document(args).get().await()

    }


}