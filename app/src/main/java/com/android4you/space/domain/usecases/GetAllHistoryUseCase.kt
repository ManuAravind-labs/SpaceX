package com.android4you.space.domain.usecases

import com.android4you.space.data.model.NetworkState
import com.android4you.space.domain.model.history.HistoryModel
import com.android4you.space.domain.repository.HistoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllHistoryUseCase @Inject constructor(
    private val appRepository: HistoryRepository
) {
    suspend fun execute(): Flow<NetworkState<List<HistoryModel>>> {
        return appRepository.getAllHistory()
    }
}
