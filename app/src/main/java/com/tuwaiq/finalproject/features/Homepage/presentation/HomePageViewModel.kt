package com.tuwaiq.finalproject.features.Homepage.presentation

import androidx.lifecycle.ViewModel
import com.tuwaiq.finalproject.core.Test

class HomePageViewModel : ViewModel() {



    fun click(){


        HomePageFragmentDirections.actionHomePageFragmentToMyProfileFragment()


    }
    val items = mutableListOf<Test>()

    val names = listOf(
        "Hind",
        "sirin",
        "Asma",
        "Ahmed",
        "Reem",
        "Rahaf",
        "Rawan",
        "Zienab",
        "Manar",
        "Hajar"
    )

    init {
        for (i in 0 until 10){
            val test = Test()
            test.title = names[i]

            items += test
        }
    }
}