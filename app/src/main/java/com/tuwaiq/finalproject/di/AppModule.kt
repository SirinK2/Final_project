package com.tuwaiq.finalproject.di

import com.tuwaiq.finalproject.core.data.repo.PostRepoImpl
import com.tuwaiq.finalproject.core.data.repo.UserRepoImpl
import com.tuwaiq.finalproject.core.domain.repo.PostRepo
import com.tuwaiq.finalproject.core.domain.repo.UserRepo
import com.tuwaiq.finalproject.features.auth.data.repo.AuthRepoImpl
import com.tuwaiq.finalproject.features.auth.domain.repo.AuthRepo
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