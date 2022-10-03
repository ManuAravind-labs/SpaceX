package com.android4you.space.data.repository

import com.android4you.space.data.local.LocalDao
import com.android4you.space.data.model.NetworkState
import com.android4you.space.data.remote.ApiService
import com.android4you.space.domain.model.crew.CrewModel
import com.android4you.space.domain.repository.CrewRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CrewRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val localDao: LocalDao
) : CrewRepository {

    override suspend fun getAllCrews(): Flow<NetworkState<List<CrewModel>>> {
        return flow {
            val response = apiService.getAllcrews()
            if (response.isSuccessful) {
                val body = response.body()!!
                saveAllCrewInLocal(body)
                emit(NetworkState.Success(body))
            } else {
                emit(NetworkState.Error(response.message()))
            }
        }
    }

    override suspend fun getAllCrewsFromDb(list: List<String>?): List<CrewModel> {
        val cachedList = localDao.getCrewModelAll(list)
        return cachedList.ifEmpty {
            emptyList()
        }
    }

    /**
     * Save data in room database
     */
    private suspend fun saveAllCrewInLocal(model: List<CrewModel>?) {
        model?.let {
            try {
                localDao.insertCrewAll(model)
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }
}
