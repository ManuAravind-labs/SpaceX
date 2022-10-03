package com.android4you.space.domain.repository

import com.android4you.space.data.model.NetworkState
import com.android4you.space.domain.model.history.HistoryModel
import kotlinx.coroutines.flow.Flow

interface HistoryRepository {
    suspend fun getAllHistory(): Flow<NetworkState<List<HistoryModel>>>
}
