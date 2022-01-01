package com.tuwaiq.finalproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "MainActivity"
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val user = Firebase.auth.currentUser
        if (user != null) {
            // User is signed in
            Log.d(TAG, "onCreate: User is signed in")
            findNavController(R.id.fragmentContainerView).navigate(R.id.homePageFragment)

        } else {
           findNavController(R.id.fragmentContainerView).navigate(R.id.singInFragment)
            Log.d(TAG, "onCreate: User is  not sign in")
        }


    }
}