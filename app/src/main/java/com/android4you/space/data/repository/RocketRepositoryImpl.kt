package com.android4you.space.data.repository

import com.android4you.space.data.model.NetworkState
import com.android4you.space.data.remote.ApiService
import com.android4you.space.domain.model.rockets.RocketModel
import com.android4you.space.domain.repository.RocketsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RocketRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : RocketsRepository {

    override suspend fun getAllRockets(): Flow<NetworkState<List<RocketModel>>> {
        return flow {
            val response = apiService.getAllRockets()
            if (response.isSuccessful) {
                val body = response.body()!!
                emit(NetworkState.Success(body))
            } else {
                emit(NetworkState.Error(response.message()))
            }
        }
    }

    override suspend fun getOneRocket(id: String): Flow<NetworkState<RocketModel>> {
        return flow {
            val response = apiService.getOneRocket(id)
            if (response.isSuccessful) {
                val body = response.body()!!
                emit(NetworkState.Success(body))
            } else {
                emit(NetworkState.Error(response.message()))
            }
        }
    }
}
