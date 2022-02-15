package com.tuwaiq.finalproject.data.repo

import android.net.Uri
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.tuwaiq.finalproject.data.remote.dto.PaymentDto
import com.tuwaiq.finalproject.data.remote.dto.PostDto
import com.tuwaiq.finalproject.domain.model.Payment
import com.tuwaiq.finalproject.domain.model.Post
import com.tuwaiq.finalproject.domain.repo.PostRepo
import com.tuwaiq.finalproject.util.Constant.paymentCollectionRef
import com.tuwaiq.finalproject.util.Constant.postCollectionRef
import kotlinx.coroutines.tasks.await
import java.util.*

private const val TAG = "PostRepoImpl"

class PostRepoImpl : PostRepo {


    override fun addPost(post: Post) {
        val ref = postCollectionRef.document()
        post.id = ref.id
        ref.set(post)
    }



    
    override suspend fun getPost(): List<PostDto> =
        postCollectionRef.get().await().toObjects(PostDto::class.java).sortedBy {
            it.postDate
        }.asReversed()



    override fun addPayment(payment: Payment) {
        val ref = paymentCollectionRef.document()
        payment.paymentID = ref.id
        ref.set(payment)
    }




    override fun deletePost(id: String) {
        postCollectionRef.document(id).delete()
    }

    override suspend fun getPayments(): List<Payment> {
        return paymentCollectionRef.get().await().toObjects(Payment::class.java)
    }


    override suspend fun uploadImage(uri: List<Uri>): List<String>{
        val uriList: MutableList<String> = mutableListOf()
        uri.forEach {
            val fileName = "${UUID.randomUUID()}.jpg"
            val imgRef = Firebase.storage.reference.child("images/$fileName")

            val uriTask= imgRef.putFile(it)
                .continueWithTask { task ->
                if (!task.isSuccessful){
                    task.exception?.let { e ->
                        throw e
                    }
                }
                imgRef.downloadUrl

            }.await()
            uriList += uriTask.toString()

        }
        return uriList
    }


}