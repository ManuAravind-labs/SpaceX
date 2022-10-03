package com.android4you.space.domain.usecases

import com.android4you.space.data.model.NetworkState
import com.android4you.space.domain.model.rockets.RocketModel
import com.android4you.space.domain.repository.RocketsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRocketInfoBasedOnIDUseCase @Inject constructor(
    private val rocketsRepository: RocketsRepository
) {
    suspend fun execute(id: String): Flow<NetworkState<RocketModel>> {
        return rocketsRepository.getOneRocket(id)
    }
}
