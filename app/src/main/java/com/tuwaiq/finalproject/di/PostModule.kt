package com.tuwaiq.finalproject.di

import com.tuwaiq.finalproject.core.data.repo.PostRepo
import com.tuwaiq.finalproject.core.domain.repo.PostRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object PostModule {

    @Singleton
    @Provides
    fun providePostRepo():PostRepo = PostRepoImpl()

}