package com.android4you.space.domain.repository

import com.android4you.space.data.model.NetworkState
import com.android4you.space.domain.model.payloads.PayloadsModel
import kotlinx.coroutines.flow.Flow

interface PayloadsRepository {

    suspend fun getAllPayloads(): Flow<NetworkState<List<PayloadsModel>>>

    suspend fun getAllPayloadsFromDb(list: List<String>?): List<PayloadsModel>
}
