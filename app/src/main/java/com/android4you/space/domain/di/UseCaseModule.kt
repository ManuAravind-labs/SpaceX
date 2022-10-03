package com.android4you.space.domain.di

import com.android4you.space.domain.repository.LaunchesRepository
import com.android4you.space.domain.repository.RocketsRepository
import com.android4you.space.domain.usecases.GetAllLaunchesUseCase
import com.android4you.space.domain.usecases.GetAllRocketUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//@Module
//@InstallIn(SingletonComponent::class)
//object UseCaseModule {
//
//    @Provides
//    @Singleton
//    fun provideLaunchesUseCase(
//        apiService: LaunchesRepository
//    ): GetAllLaunchesUseCase {
//        return GetAllLaunchesUseCase(apiService)
//    }
//
//    @Provides
//    @Singleton
//    fun provideRocketsUseCase(
//        apiService: RocketsRepository
//    ): GetAllRocketUseCase {
//        return GetAllRocketUseCase(apiService)
//    }
//}
