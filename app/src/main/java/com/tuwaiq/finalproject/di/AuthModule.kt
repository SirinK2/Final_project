package com.tuwaiq.finalproject.di

import com.tuwaiq.finalproject.presentation.auth.data.repo.AuthRepo
import com.tuwaiq.finalproject.presentation.auth.domain.repo.AuthRepoImpl
import com.tuwaiq.finalproject.presentation.auth.domain.use_cases.RegisterUseCase
import com.tuwaiq.finalproject.presentation.auth.domain.use_cases.SingInUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AuthModule {


    @Singleton
    @Provides
    fun provideAuthRepo():AuthRepo = AuthRepoImpl()


    @Singleton
    @Provides
    fun provideSingInUseCase(repo: AuthRepo): SingInUseCase = SingInUseCase(repo)



    @Singleton
    @Provides
    fun provideRegisterUseCase(repo: AuthRepo): RegisterUseCase = RegisterUseCase(repo)

}