package com.android4you.space.domain.usecases

import com.android4you.space.data.model.NetworkState
import com.android4you.space.domain.model.crew.CrewModel
import com.android4you.space.domain.repository.CrewRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllCrewUseCase @Inject constructor(
    private val appRepository: CrewRepository
) {
    suspend fun execute(): Flow<NetworkState<List<CrewModel>>> {
        return appRepository.getAllCrews()
    }
}
