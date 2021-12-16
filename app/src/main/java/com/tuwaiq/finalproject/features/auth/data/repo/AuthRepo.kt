package com.tuwaiq.finalproject.features.auth.data.repo

interface AuthRepo {


    fun register(username:String,email:String, password: String)

    fun singIn(email: String,password: String)


}