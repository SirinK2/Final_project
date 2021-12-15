package com.tuwaiq.finalproject.di

import com.tuwaiq.finalproject.core.data.repo.UserRepo
import com.tuwaiq.finalproject.core.domain.repo.UserRepoImpl
import com.tuwaiq.finalproject.presentation.profile.domain.user_cases.SaveUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UserModule {

    @Singleton
    @Provides
    fun provideUserRepo():UserRepo = UserRepoImpl()

    @Singleton
    @Provides
    fun provideSaveUserUseCase(repo: UserRepo): SaveUserUseCase = SaveUserUseCase(repo)


}