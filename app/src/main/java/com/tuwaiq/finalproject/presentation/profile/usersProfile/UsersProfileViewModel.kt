package com.tuwaiq.finalproject.presentation.profile.usersProfile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.tuwaiq.finalproject.domain.model.Post
import com.tuwaiq.finalproject.domain.model.User
import com.tuwaiq.finalproject.domain.use_case.GetPostByIdUseCase
import com.tuwaiq.finalproject.domain.use_case.GetUserUseCase
import com.tuwaiq.finalproject.domain.use_case.GetUsersPostUseCase
import com.tuwaiq.finalproject.domain.use_case.MyPostUseCase
import com.tuwaiq.finalproject.util.Constant.postCollectionRef
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class UsersProfileViewModel @Inject constructor(
    private val getUsersPostUseCase: GetUsersPostUseCase,
    private val getUserUseCase: GetUserUseCase
    ): ViewModel() {


    fun userPost(args:String): LiveData<List<Post>> = liveData {
        emit(getUsersPostUseCase(args))

    }

    fun getUser():LiveData<User> = liveData{
        emit(getUserUseCase())
    }





//    postCollectionRef.document(args).get().await()

}