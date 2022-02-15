package com.tuwaiq.finalproject.domain.use_case

import android.util.Log
import com.tuwaiq.finalproject.data.remote.dto.toPost
import com.tuwaiq.finalproject.domain.model.Post
import com.tuwaiq.finalproject.domain.repo.PostRepo
import com.tuwaiq.finalproject.util.Constant.uid
import javax.inject.Inject

private const val TAG = "GetPurchasesUseCase"
class GetPurchasesUseCase @Inject constructor(private val repo: PostRepo) {

    suspend operator fun invoke(): List<Post> {
        val itemsList = mutableListOf<Post>()
        repo.getPayments().forEach { payment ->
            repo.getPost().forEach {
                if (payment.itemDocId == it.id && payment.buyerId == uid){
                    itemsList += it.toPost()
                }
            }
        }
        Log.e(TAG, "invoke: $itemsList", )
        return itemsList

    }

}