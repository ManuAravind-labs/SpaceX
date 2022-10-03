package com.android4you.space.data.repository

import com.android4you.space.data.model.NetworkState
import com.android4you.space.data.remote.ApiService
import com.android4you.space.domain.model.history.HistoryModel
import com.android4you.space.domain.repository.HistoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HistoryRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : HistoryRepository {

    override suspend fun getAllHistory(): Flow<NetworkState<List<HistoryModel>>> {
        return flow {
            val response = apiService.getAllHistory()
            if (response.isSuccessful) {
                val body = response.body()!!
                emit(NetworkState.Success(body))
            } else {
                emit(NetworkState.Error(response.message()))
            }
        }
    }
}
