package com.android4you.space.data.repository

import com.android4you.space.data.local.LocalDao
import com.android4you.space.data.model.NetworkState
import com.android4you.space.data.remote.ApiService
import com.android4you.space.domain.model.payloads.PayloadsModel
import com.android4you.space.domain.repository.PayloadsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PayloadsRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val localDao: LocalDao
) : PayloadsRepository {

    override suspend fun getAllPayloads(): Flow<NetworkState<List<PayloadsModel>>> {
        return flow {
            val response = apiService.getAllpayloads()
            if (response.isSuccessful) {
                val body = response.body()!!
                saveAllPayloadsInLocal(body)
                emit(NetworkState.Success(body))
            } else {
                emit(NetworkState.Error(response.message(), null))
            }
        }
    }

    override suspend fun getAllPayloadsFromDb(list: List<String>?): List<PayloadsModel> {
        val cachedList = localDao.getPayloadsModelAll(list)
        return cachedList.ifEmpty {
            emptyList()
        }
    }
    /**
     * Save data in room database
     */
    private suspend fun saveAllPayloadsInLocal(model: List<PayloadsModel>?) {
        model?.let {
            try {
                localDao.insertPayloadsAll(model)
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }
}
