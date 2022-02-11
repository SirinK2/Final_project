package com.tuwaiq.finalproject

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "MainActivity"
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


        override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
            currentFocus?.let {
                val imm: InputMethodManager = getSystemService(
                    Context.INPUT_METHOD_SERVICE
                ) as (InputMethodManager)
                imm.hideSoftInputFromWindow(it.windowToken, 0)
            }
            return super.dispatchTouchEvent(ev)
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val user = Firebase.auth.currentUser
        if (user == null) {

           findNavController(R.id.fragmentContainerView).navigate(R.id.singInFragment)
            Log.d(TAG, "onCreate: No user is signed in")

        }









    }





}