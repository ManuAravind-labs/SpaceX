package com.android4you.space.presentation.launches.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android4you.space.data.model.NetworkState
import com.android4you.space.domain.model.LaunchModel
import com.android4you.space.domain.usecases.GetAllLaunchesUseCase
import com.android4you.space.domain.usecases.GetAllPastLaunchesUseCase
import com.android4you.space.domain.usecases.GetAllUpcomingLaunchesUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LaunchesViewModel @Inject constructor(
    private val getAllLaunchesUseCase: GetAllLaunchesUseCase,
    private val getAllPastLaunchesUseCase: GetAllPastLaunchesUseCase,
    private val getAllUpcomingLaunchesUseCase: GetAllUpcomingLaunchesUsecase
) : ViewModel() {

    private val stateflowPrivate = MutableStateFlow<LaunchViewState>(LaunchViewState.Empty)
    val stateflow: StateFlow<LaunchViewState> get() = stateflowPrivate

    fun fetchAllLaunches() {
        viewModelScope.launch {
            getAllLaunchesUseCase.execute()
                .flowOn(Dispatchers.IO)
                .catch {
                }
                .collect { result ->
                    when (result) {
                        is NetworkState.Error -> {
                            stateflowPrivate.value = LaunchViewState.Error("ErrOR")
                        }
                        is NetworkState.Success -> {
                            stateflowPrivate.value = LaunchViewState.Success(result.data)
                        }
                        else -> {}
                    }
                }
        }
    }

    fun fetchAllPastLaunches() {
        viewModelScope.launch {
            getAllPastLaunchesUseCase.execute()
                .flowOn(Dispatchers.IO)
                .catch {
                }
                .collect { result ->
                    when (result) {
                        is NetworkState.Error -> {
                            stateflowPrivate.value = LaunchViewState.Error("ErrOR")
                        }
                        is NetworkState.Success -> {
                            stateflowPrivate.value = LaunchViewState.Success(result.data)
                        }
                        else -> {}
                    }
                }
        }
    }

    fun fetchAllUpcomingLaunches() {
        viewModelScope.launch {
            getAllUpcomingLaunchesUseCase.execute()
                .flowOn(Dispatchers.IO)
                .catch {
                }
                .collect { result ->
                    when (result) {
                        is NetworkState.Error -> {
                            stateflowPrivate.value = LaunchViewState.Error("ErrOR")
                        }
                        is NetworkState.Success -> {
                            stateflowPrivate.value = LaunchViewState.Success(result.data)
                        }
                        else -> {}
                    }
                }
        }
    }
}

sealed class LaunchViewState {
    data class Error(val message: String) : LaunchViewState()
    object Empty : LaunchViewState()
    data class Loading(val isLoading: Boolean) : LaunchViewState()
    data class Success(val list: List<LaunchModel>?) :
        LaunchViewState()
}
