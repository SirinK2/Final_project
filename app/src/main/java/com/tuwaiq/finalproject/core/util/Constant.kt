package com.tuwaiq.finalproject.core.util

import android.net.Uri
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.tuwaiq.finalproject.features.homepage.presentation.HomePageFragment

object Constant {

    var imgFile: Uri? = null

    lateinit var mAdapter: HomePageFragment.HomeAdapter

     val postCollectionRef = Firebase.firestore.collection("post")




}