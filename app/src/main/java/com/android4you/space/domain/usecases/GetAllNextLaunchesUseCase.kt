package com.android4you.space.domain.usecases

import com.android4you.space.data.model.NetworkState
import com.android4you.space.domain.model.LaunchModel
import com.android4you.space.domain.repository.LaunchesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllNextLaunchesUseCase @Inject constructor(
    private val appRepository: LaunchesRepository
) {
    suspend fun execute(): Flow<NetworkState<LaunchModel>> {
        return appRepository.getNextLaunch()
    }
}
