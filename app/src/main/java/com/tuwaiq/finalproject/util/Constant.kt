package com.tuwaiq.finalproject.util

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.tuwaiq.finalproject.presentation.homepage.HomePageFragment


object Constant {

     private const val PAYMENT_COLLECTION = "payment"
     private const val POST_COLLECTION = "post"
     private const val USER_COLLECTION = "users"
     private const val RATING_COLLECTION = "Ratings"
     private const val CHAT = "chat"

     lateinit var mAdapter: HomePageFragment.HomeAdapter
     const val format = "hh:mm aa"
     const val dateFormat = "dd MMM yy"



     val uid = Firebase.auth.currentUser?.uid.toString()
     val postCollectionRef = Firebase.firestore.collection(POST_COLLECTION)
     val paymentCollectionRef = Firebase.firestore.collection(PAYMENT_COLLECTION)
     val userCollectionRef = Firebase.firestore.collection(USER_COLLECTION)
     val ratingCollectionRef = Firebase.firestore.collection(RATING_COLLECTION)
     val chatCollectionRef = Firebase.firestore.collection(CHAT)






}

