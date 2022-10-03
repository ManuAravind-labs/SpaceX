package com.android4you.space.domain.repository

import com.android4you.space.data.model.NetworkState
import com.android4you.space.domain.model.LaunchModel
import kotlinx.coroutines.flow.Flow

interface LaunchesRepository {

    suspend fun getAllLaunches(): Flow<NetworkState<List<LaunchModel>>>

    suspend fun getOneLaunch(id: Int): Flow<NetworkState<LaunchModel>>

    suspend fun getPastLaunches(): Flow<NetworkState<List<LaunchModel>>>

    suspend fun getUpcomingLaunches(): Flow<NetworkState<List<LaunchModel>>>

    suspend fun getLatestLaunch(): Flow<NetworkState<LaunchModel>>

    suspend fun getNextLaunch(): Flow<NetworkState<LaunchModel>>

    //  suspend fun combineMethod(): Flow<NetworkState<LaunchModel>>
}
