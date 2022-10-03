package com.android4you.space.data.di

import com.android4you.space.data.local.LocalDao
import com.android4you.space.data.remote.ApiService
import com.android4you.space.data.repository.CrewRepositoryImpl
import com.android4you.space.data.repository.HistoryRepositoryImpl
import com.android4you.space.data.repository.LaunchesRepositoryImpl
import com.android4you.space.data.repository.PadsRepositoryImpl
import com.android4you.space.data.repository.PayloadsRepositoryImpl
import com.android4you.space.data.repository.RocketRepositoryImpl
import com.android4you.space.domain.repository.CrewRepository
import com.android4you.space.domain.repository.HistoryRepository
import com.android4you.space.domain.repository.LaunchesRepository
import com.android4you.space.domain.repository.PadsRepository
import com.android4you.space.domain.repository.PayloadsRepository
import com.android4you.space.domain.repository.RocketsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideLaunchesRepository(
        apiService: ApiService
    ): LaunchesRepository {
        return LaunchesRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideRocketsRepository(
        apiService: ApiService
    ): RocketsRepository {
        return RocketRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideCrewRepository(
        apiService: ApiService,
        localDao: LocalDao
    ): CrewRepository {
        return CrewRepositoryImpl(apiService, localDao)
    }

    @Provides
    @Singleton
    fun providePayloadsRepository(
        apiService: ApiService,
        localDao: LocalDao
    ): PayloadsRepository {
        return PayloadsRepositoryImpl(apiService, localDao)
    }

    @Provides
    @Singleton
    fun provideHistoryRepository(
        apiService: ApiService
    ): HistoryRepository {
        return HistoryRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun providePadsRepository(
        apiService: ApiService
    ): PadsRepository {
        return PadsRepositoryImpl(apiService)
    }
}
