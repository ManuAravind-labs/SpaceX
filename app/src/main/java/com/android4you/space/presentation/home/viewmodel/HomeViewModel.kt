package com.android4you.space.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android4you.space.data.model.NetworkState
import com.android4you.space.domain.model.ResponseType
import com.android4you.space.domain.model.dynamic.BaseUIModel
import com.android4you.space.domain.model.dynamic.CrewUIModel
import com.android4you.space.domain.model.dynamic.HistoryUIModel
import com.android4you.space.domain.model.dynamic.LaunchUIModel
import com.android4you.space.domain.model.dynamic.PayloadUIModel
import com.android4you.space.domain.model.dynamic.RocketUIModel
import com.android4you.space.domain.usecases.GetAllCrewUseCase
import com.android4you.space.domain.usecases.GetAllHistoryUseCase
import com.android4you.space.domain.usecases.GetAllLaunchesUseCase
import com.android4you.space.domain.usecases.GetAllPayloadsUseCase
import com.android4you.space.domain.usecases.GetAllRocketUseCase
import com.android4you.space.utils.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val networkHelper: NetworkHelper,
    private val getAllLaunchesUseCase: GetAllLaunchesUseCase,
    private val getAllRocketUseCase: GetAllRocketUseCase,
    private val getAllCrewUseCase: GetAllCrewUseCase,
    private val getAllPayloadsUseCase: GetAllPayloadsUseCase,
    private val getAllHistoryUseCase: GetAllHistoryUseCase
) : ViewModel() {

    private val homeViewStateFlowPrivate = MutableStateFlow<HomeViewState>(HomeViewState.Empty)
    val homeViewStateFlow: StateFlow<HomeViewState> get() = homeViewStateFlowPrivate
    private var uiList = ArrayList<BaseUIModel>()

    init {
        fetchHomeInfo()
    }

    private fun fetchHomeInfo() {
        if (networkHelper.isNetworkConnected()) {
            viewModelScope.launch {
                getAllLaunchesUseCase.execute()
                    .zip(getAllRocketUseCase.execute()) { upcomingLaunches, allRockets ->
                        return@zip Pair(
                            upcomingLaunches.data,
                            allRockets.data
                        )
                    }.zip(getAllCrewUseCase.execute()) { data, crews ->
                        return@zip Pair(data, crews.data)
                    }.zip(getAllPayloadsUseCase.execute()) { data, payloads ->
                        return@zip Pair(data, payloads.data)
                    }
                    .flowOn(Dispatchers.IO)
                    .catch { e ->
                        homeViewStateFlowPrivate.value = HomeViewState.Loading(false)
                        homeViewStateFlowPrivate.value = HomeViewState.Error("Errors" + e.message)
                    }
                    .collect {
                        homeViewStateFlowPrivate.value = HomeViewState.Loading(false)
                        it.first.first.first?.let { launches ->
                            uiList.add(LaunchUIModel("Launches", launches, ResponseType.LAUNCH))
                        }
                        it.first.first.second?.let { rockets ->
                            uiList.add(RocketUIModel("Rockets", rockets, ResponseType.ROCKET))
                        }
                        it.first.second?.let { crews ->
                            uiList.add(CrewUIModel("Crews", crews, ResponseType.CREW))
                        }
                        it.second?.let { payloads ->
                            uiList.add(PayloadUIModel("Payloads", payloads, ResponseType.CREW))
                        }
                        fetchHistory()
                    }
            }
        } else {
            homeViewStateFlowPrivate.value = HomeViewState.Error("No Internet Connection")
        }
    }

    private fun fetchHistory() {
        if (networkHelper.isNetworkConnected()) {
            viewModelScope.launch {
                getAllHistoryUseCase.execute()
                    .flowOn(Dispatchers.IO)
                    .catch {
                    }
                    .collect { result ->
                        when (result) {
                            is NetworkState.Error -> {
                                homeViewStateFlowPrivate.value = HomeViewState.Error("Error")
                            }
                            is NetworkState.Success -> {
                                result.data?.let {
                                    uiList.add(HistoryUIModel("History", it, ResponseType.HISTORY))
                                    homeViewStateFlowPrivate.value = HomeViewState.Success(uiList)
                                }
                            }
                            else -> {}
                        }
                    }
            }
        } else {
            homeViewStateFlowPrivate.value = HomeViewState.Error("No Internet Connection")
        }
    }
}

sealed class HomeViewState {
    data class Error(val message: String) : HomeViewState()
    object Empty : HomeViewState()
    data class Loading(val isLoading: Boolean) : HomeViewState()
    data class Success(val list: List<BaseUIModel>?) :
        HomeViewState()
}
