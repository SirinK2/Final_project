package com.tuwaiq.finalproject.di

import com.tuwaiq.finalproject.core.data.repo.PostRepo
import com.tuwaiq.finalproject.core.data.repo.UserRepo
import com.tuwaiq.finalproject.core.domain.repo.PostRepoImpl
import com.tuwaiq.finalproject.core.domain.repo.UserRepoImpl
import com.tuwaiq.finalproject.features.auth.data.repo.AuthRepo
import com.tuwaiq.finalproject.features.auth.domain.repo.AuthRepoImpl
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
    fun provideAuthRepo():AuthRepo = AuthRepoImpl()


    @Singleton
    @Provides
    fun providePostRepo(): PostRepo = PostRepoImpl()


    @Singleton
    @Provides
    fun provideUserRepo(): UserRepo = UserRepoImpl()



}