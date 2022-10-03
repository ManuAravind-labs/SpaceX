package com.android4you.space.domain.repository

import com.android4you.space.data.model.NetworkState
import com.android4you.space.domain.model.rockets.RocketModel
import kotlinx.coroutines.flow.Flow

interface RocketsRepository {

    suspend fun getAllRockets(): Flow<NetworkState<List<RocketModel>>>

    suspend fun getOneRocket(id: String): Flow<NetworkState<RocketModel>>
}
