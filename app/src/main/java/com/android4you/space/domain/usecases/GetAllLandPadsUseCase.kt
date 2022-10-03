package com.android4you.space.domain.usecases

import com.android4you.space.data.model.NetworkState
import com.android4you.space.domain.model.pads.LandPadsModel
import com.android4you.space.domain.repository.PadsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllLandPadsUseCase @Inject constructor(
    private val appRepository: PadsRepository
) {
    suspend fun execute(): Flow<NetworkState<List<LandPadsModel>>> {
        return appRepository.getAllLandPads()
    }
}
