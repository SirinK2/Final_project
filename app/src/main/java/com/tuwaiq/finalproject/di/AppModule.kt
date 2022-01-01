package com.tuwaiq.finalproject.di

import com.tuwaiq.finalproject.data.repo.AuthRepoImpl
import com.tuwaiq.finalproject.data.repo.PostRepoImpl
import com.tuwaiq.finalproject.data.repo.UserRepoImpl
import com.tuwaiq.finalproject.domain.repo.PostRepo
import com.tuwaiq.finalproject.domain.repo.UserRepo
import com.tuwaiq.finalproject.domain.repo.AuthRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun provideAuthRepo(): AuthRepo = AuthRepoImpl()


    @Singleton
    @Provides
    fun providePostRepo(): PostRepo = PostRepoImpl()


    @Singleton
    @Provides
    fun provideUserRepo(): UserRepo = UserRepoImpl()



}