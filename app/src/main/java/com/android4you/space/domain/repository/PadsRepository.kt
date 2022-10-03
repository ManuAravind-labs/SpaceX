package com.android4you.space.domain.repository

import com.android4you.space.data.model.NetworkState
import com.android4you.space.domain.model.pads.LandPadsModel
import com.android4you.space.domain.model.pads.LaunchPadsModel
import kotlinx.coroutines.flow.Flow

interface PadsRepository {

    suspend fun getAllLandPads(): Flow<NetworkState<List<LandPadsModel>>>

    suspend fun getAllLaunchPads(): Flow<NetworkState<List<LaunchPadsModel>>>

    suspend fun getOneLaunchPad(id: String): Flow<NetworkState<LaunchPadsModel>>
}
