package com.tuwaiq.finalproject.util

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.tuwaiq.finalproject.presentation.homepage.HomePageFragment

object Constant {

    lateinit var mAdapter: HomePageFragment.HomeAdapter

     val postCollectionRef = Firebase.firestore.collection("post")




}