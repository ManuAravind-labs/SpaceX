package com.android4you.space.domain.usecases

import com.android4you.space.data.model.NetworkState
import com.android4you.space.domain.model.rockets.RocketModel
import com.android4you.space.domain.repository.RocketsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllRocketUseCase @Inject
constructor(
    private val appRepository: RocketsRepository
) {
    suspend fun execute(): Flow<NetworkState<List<RocketModel>>> {
        return appRepository.getAllRockets()
    }
}
