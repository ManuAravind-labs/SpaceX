package com.android4you.space.presentation.missions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android4you.space.domain.model.pads.LaunchPadsModel
import com.android4you.space.domain.usecases.*
import com.android4you.space.utils.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MissionsViewModel @Inject constructor(
    private val networkHelper: NetworkHelper,
    private val getAllLandPadsUseCase: GetAllLandPadsUseCase,
    private val getAllLaunchPadsUseCase: GetAllLaunchPadsUseCase,
) : ViewModel() {

    private val stateflowPrivate =
        MutableStateFlow<LaunchPadsViewState>(LaunchPadsViewState.Empty)
    val stateflow: StateFlow<LaunchPadsViewState> = stateflowPrivate

    /***
     fun fetchAllLandPads() {
     viewModelScope.launch {
     stateflowPrivate.value = LaunchLandPadsViewState.Loading
     getAllLandPadsUseCase.execute()
     .zip(getAllLaunchPadsUseCase.execute()) { landPadsList, launchPadsList ->
     return@zip Pair(
     landPadsList.data,
     launchPadsList.data
     )
     }
     .flowOn(Dispatchers.IO)
     .catch {
     stateflowPrivate.value = LaunchLandPadsViewState.Error("Error")
     }
     .collect {
     stateflowPrivate.emit(LaunchLandPadsViewState.Empty)
     }
     }
     }
     ****/

    fun fetchAllLaunchPads() {
        if (networkHelper.isNetworkConnected()) {
            viewModelScope.launch {
                stateflowPrivate.value = LaunchPadsViewState.Loading
                getAllLaunchPadsUseCase.execute()
                    .flowOn(Dispatchers.IO)
                    .catch {
                        stateflowPrivate.value = LaunchPadsViewState.UnknownError
                    }
                    .collect {
                        if (it.data.isNullOrEmpty()) {
                            stateflowPrivate.emit(LaunchPadsViewState.NotFoundError)
                        } else {
                            stateflowPrivate.emit(LaunchPadsViewState.Success(it.data))
                        }
                    }
            }
        } else {
            stateflowPrivate.value = LaunchPadsViewState.NoNetworkError
        }
    }
}

sealed class LaunchPadsViewState {
    object NoNetworkError : LaunchPadsViewState()
    object NotFoundError : LaunchPadsViewState()
    object UnknownError : LaunchPadsViewState()
    object Empty : LaunchPadsViewState()
    object Loading : LaunchPadsViewState()
    data class Success(
        val launchPadsList: List<LaunchPadsModel>?
    ) : LaunchPadsViewState()
}

/****
sealed class LaunchLandPadsViewState {
data class Error(val message: String) : LaunchLandPadsViewState()
object Empty : LaunchLandPadsViewState()
object Loading : LaunchLandPadsViewState()
data class Success(
val landPadsList: List<LandPadsModel>?,
val launchPadsList: List<LaunchPadsModel>?
) : LaunchLandPadsViewState()
}
 ****/
