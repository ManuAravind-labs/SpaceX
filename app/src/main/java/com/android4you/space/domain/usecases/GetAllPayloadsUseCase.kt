package com.android4you.space.domain.usecases

import com.android4you.space.data.model.NetworkState
import com.android4you.space.domain.model.payloads.PayloadsModel
import com.android4you.space.domain.repository.PayloadsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllPayloadsUseCase @Inject constructor(
    private val appRepository: PayloadsRepository
) {
    suspend fun execute(): Flow<NetworkState<List<PayloadsModel>>> {
        return appRepository.getAllPayloads()
    }
}
