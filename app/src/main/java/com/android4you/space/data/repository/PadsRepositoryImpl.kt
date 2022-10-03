package com.android4you.space.data.repository

import com.android4you.space.data.model.NetworkState
import com.android4you.space.data.remote.ApiService
import com.android4you.space.domain.model.pads.LandPadsModel
import com.android4you.space.domain.model.pads.LaunchPadsModel
import com.android4you.space.domain.repository.PadsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PadsRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : PadsRepository {

    override suspend fun getAllLandPads(): Flow<NetworkState<List<LandPadsModel>>> {
        return flow {
            val response = apiService.getAllLandPads()
            if (response.isSuccessful) {
                val body = response.body()!!
                emit(NetworkState.Success(body))
            } else {
                emit(NetworkState.Error(response.message()))
            }
        }
    }

    override suspend fun getAllLaunchPads(): Flow<NetworkState<List<LaunchPadsModel>>> {
        return flow {
            val response = apiService.getAllLaunchPads()
            if (response.isSuccessful) {
                val body = response.body()!!
                emit(NetworkState.Success(body))
            } else {
                emit(NetworkState.Error(response.message()))
            }
        }
    }

    override suspend fun getOneLaunchPad(id: String): Flow<NetworkState<LaunchPadsModel>> {
        return flow {
            val response = apiService.getOneLaunchPad(id)
            if (response.isSuccessful) {
                val body = response.body()!!
                emit(NetworkState.Success(body))
            } else {
                emit(NetworkState.Error(response.message()))
            }
        }
    }
}
