package com.tuwaiq.finalproject.features.auth.domain.repo

interface AuthRepo {


    fun register(username:String,email:String, password: String)

    fun singIn(email: String,password: String)


}