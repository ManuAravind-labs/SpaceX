package com.android4you.space.domain.usecases

import com.android4you.space.domain.model.payloads.PayloadsModel
import com.android4you.space.domain.repository.PayloadsRepository
import javax.inject.Inject

class GetAllPayloadLocalUseCase @Inject constructor(
    private val appRepository: PayloadsRepository
) {
    suspend fun execute(list: List<String>?): List<PayloadsModel> {
        return appRepository.getAllPayloadsFromDb(list)
    }
}
