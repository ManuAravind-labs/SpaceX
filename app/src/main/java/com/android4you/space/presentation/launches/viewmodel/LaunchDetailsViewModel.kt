package com.android4you.space.presentation.launches.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android4you.space.data.model.NetworkState
import com.android4you.space.domain.model.LaunchModel
import com.android4you.space.domain.model.launchdetails.* // ktlint-disable no-wildcard-imports
import com.android4you.space.domain.usecases.GetAllCrewInfoLocalBasedOnIDUseCase
import com.android4you.space.domain.usecases.GetAllPayloadLocalUseCase
import com.android4you.space.domain.usecases.GetLaunchPadsInfoBasedOnIDUseCase
import com.android4you.space.domain.usecases.GetRocketInfoBasedOnIDUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LaunchDetailsViewModel @Inject constructor(
    private val getRocketInfoBasedOnIDUseCase: GetRocketInfoBasedOnIDUseCase,
    private val getLaunchPadsInfoBasedOnIDUseCase: GetLaunchPadsInfoBasedOnIDUseCase,
    private val getAllPayloadLocalUseCase: GetAllPayloadLocalUseCase,
    private val getAllCrewInfoLocalBasedOnIDUseCase: GetAllCrewInfoLocalBasedOnIDUseCase
) : ViewModel() {

    private val viewStateFlowPrivate =
        MutableStateFlow(LaunchDetailsViewState())
    val viewStateFlow: StateFlow<LaunchDetailsViewState> get() = viewStateFlowPrivate

    private val viewStateFlowTestPrivate =
        MutableStateFlow(LaunchDetailFlowsViewState())
    val viewStateTestFlow: StateFlow<LaunchDetailFlowsViewState> get() = viewStateFlowTestPrivate

    private var uiList = ArrayList<BaseDetailsUIModel>()

    init {
        uiList.clear()
    }

    fun fetchLaunchDetails(launchModel: LaunchModel) {
        Log.e("hhh ==>", "jbj")
        uiList.add(LaunchDetailsUIModel("Launch Info", launchModel))
        fetchPayloads(launchModel)
    }

    private fun fetchPayloads(launchModel: LaunchModel) {
        viewModelScope.launch {
            val list = getAllPayloadLocalUseCase.execute(launchModel.payloads)
            uiList.add(LaunchDetailsPayloadsUIModel("Payloads", list))
        }
        fetchCrew(launchModel)
    }

    private fun fetchCrew(launchModel: LaunchModel) {
        viewModelScope.launch {
            val list = getAllCrewInfoLocalBasedOnIDUseCase.execute(launchModel.crew)
        }
        fetchRocketInfo(launchModel)
    }

    private fun fetchRocketInfo(launchModel: LaunchModel) {
        viewModelScope.launch {
            getRocketInfoBasedOnIDUseCase.execute(launchModel.rocket ?: "")
                .flowOn(Dispatchers.IO)
                .catch {
                }
                .collect { result ->
                    when (result) {
                        is NetworkState.Error -> {
                            fetchAllLaunchPad(launchModel)
                        }
                        is NetworkState.Success -> {
                            result.data?.let {
                                val tt = LaunchDetailsRocketUIModel("Rockets ", result.data)
                                uiList.add(tt)
                                viewStateFlowPrivate.value =
                                    LaunchDetailsViewState(userList = uiList)
                                fetchAllLaunchPad(launchModel)
                            }
                        }
                        else -> {}
                    }
                }
        }
    }

    private fun fetchAllLaunchPad(launchModel: LaunchModel) {
        viewModelScope.launch {
            getLaunchPadsInfoBasedOnIDUseCase.execute(launchModel.launchpad ?: "")
                .flowOn(Dispatchers.IO)
                .catch {
                }
                .collect { result ->
                    when (result) {
                        is NetworkState.Error -> {
                        }
                        is NetworkState.Success -> {
                            result.data?.let {
                                viewStateFlowTestPrivate.value = LaunchDetailFlowsViewState(
                                    model = LaunchPadUIModel(
                                        "Launch Pads",
                                        result.data
                                    )
                                )
                            }
                        }
                        else -> {}
                    }
                }
        }
    }
}

data class LaunchDetailsViewState(
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val userList: List<BaseDetailsUIModel> = emptyList(),
)

data class LaunchDetailFlowsViewState(
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val model: BaseDetailsUIModel? = null
)
