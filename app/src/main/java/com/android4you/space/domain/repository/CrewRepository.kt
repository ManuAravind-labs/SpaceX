package com.android4you.space.domain.repository

import com.android4you.space.data.model.NetworkState
import com.android4you.space.domain.model.crew.CrewModel
import kotlinx.coroutines.flow.Flow

interface CrewRepository {
    suspend fun getAllCrews(): Flow<NetworkState<List<CrewModel>>>

    suspend fun getAllCrewsFromDb(list: List<String>?): List<CrewModel>
}
