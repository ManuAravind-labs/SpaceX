package com.android4you.space.domain.usecases

import com.android4you.space.domain.model.crew.CrewModel
import com.android4you.space.domain.repository.CrewRepository
import javax.inject.Inject

class GetAllCrewInfoLocalBasedOnIDUseCase @Inject constructor(
    private val crewRepository: CrewRepository
) {
    suspend fun execute(list: List<String>?): List<CrewModel> {
        return crewRepository.getAllCrewsFromDb(list)
    }
}
