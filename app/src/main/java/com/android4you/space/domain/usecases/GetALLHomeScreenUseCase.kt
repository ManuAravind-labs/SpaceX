package com.android4you.space.domain.usecases

import android.util.Log
import com.android4you.space.data.model.NetworkState
import com.android4you.space.domain.model.rockets.RocketModel
import com.android4you.space.domain.repository.LaunchesRepository
import com.android4you.space.domain.repository.RocketsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.zip
import javax.inject.Inject

class GetALLHomeScreenUseCase @Inject constructor(
    private val launchesRepository: LaunchesRepository,
    private val appRepository: RocketsRepository
) {
    suspend fun executeLaunch() {
        return appRepository.getAllRockets()
            .zip(launchesRepository.getUpcomingLaunches()) { elephantsFromApi, moreElephantsFromApi ->
                moreElephantsFromApi.data?.forEach {
                    Log.e("GGG", "dfdfd")
                }
                return@zip Pair(elephantsFromApi.data, moreElephantsFromApi.data)
            }
            .flowOn(Dispatchers.Default)
            .catch { e ->
                Log.e("FFFFF", e.message.toString())
            }
            .collect {
                it.first?.forEach {
                    //Log.e(">>>>", it.name)
                }
                it.second?.forEach {
                    Log.e(">>>>", it.name?:"")
                }
            }
    }

    suspend fun executeRocket(): Flow<NetworkState<List<RocketModel>>> {
        return appRepository.getAllRockets()
    }
}
