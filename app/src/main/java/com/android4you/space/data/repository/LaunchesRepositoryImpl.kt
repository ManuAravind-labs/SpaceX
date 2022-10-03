package com.android4you.space.data.repository

import com.android4you.space.data.model.NetworkState
import com.android4you.space.data.remote.ApiService
import com.android4you.space.domain.model.LaunchModel
import com.android4you.space.domain.repository.LaunchesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LaunchesRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : LaunchesRepository {

    override suspend fun getAllLaunches(): Flow<NetworkState<List<LaunchModel>>> {
        return flow {
            val response = apiService.getAllLaunches()
            if (response.isSuccessful) {
                val body = response.body()!!
                emit(NetworkState.Success(body))
            } else {
                emit(NetworkState.Error(response.message()))
            }
        }
    }

    override suspend fun getOneLaunch(id: Int): Flow<NetworkState<LaunchModel>> {
        return flow {
            val response = apiService.getOneLaunch(id)
            if (response.isSuccessful) {
                val body = response.body()!!
                emit(NetworkState.Success(body))
            } else {
                emit(NetworkState.Error(response.message()))
            }
        }
    }

    override suspend fun getPastLaunches(): Flow<NetworkState<List<LaunchModel>>> {
        return flow {
            val response = apiService.getPastLaunches()
            if (response.isSuccessful) {
                val body = response.body()!!
                emit(NetworkState.Success(body))
            } else {
                emit(NetworkState.Error(response.message()))
            }
        }
    }

    override suspend fun getUpcomingLaunches(): Flow<NetworkState<List<LaunchModel>>> {
        return flow {
            val response = apiService.getUpcomingLaunches()
            if (response.isSuccessful) {
                val body = response.body()!!
                emit(NetworkState.Success(body))
            } else {
                emit(NetworkState.Error(response.message()))
            }
        }
    }

    override suspend fun getLatestLaunch(): Flow<NetworkState<LaunchModel>> {
        return flow {
            val response = apiService.getLatestLaunch()
            if (response.isSuccessful) {
                val body = response.body()!!
                emit(NetworkState.Success(body))
            } else {
                emit(NetworkState.Error(response.message()))
            }
        }
    }

    override suspend fun getNextLaunch(): Flow<NetworkState<LaunchModel>> {
        return flow {
            val response = apiService.getNextLaunch()
            if (response.isSuccessful) {
                val body = response.body()!!
                emit(NetworkState.Success(body))
            } else {
                emit(NetworkState.Error(response.message()))
            }
        }
    }
}
