package com.tuwaiq.finalproject.ui.Homepage

import androidx.lifecycle.ViewModel
import com.tuwaiq.finalproject.database.Test

class HomePageViewModel : ViewModel() {
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