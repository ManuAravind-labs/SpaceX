package com.android4you.space.domain.usecases

import com.android4you.space.data.model.NetworkState
import com.android4you.space.domain.model.pads.LaunchPadsModel
import com.android4you.space.domain.repository.PadsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLaunchPadsInfoBasedOnIDUseCase @Inject constructor(
    private val padsRepository: PadsRepository
) {
    suspend fun execute(id: String): Flow<NetworkState<LaunchPadsModel>> {
        return padsRepository.getOneLaunchPad(id)
    }
}
