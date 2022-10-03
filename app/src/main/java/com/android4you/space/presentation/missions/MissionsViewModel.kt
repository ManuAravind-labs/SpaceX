package com.android4you.space.presentation.missions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android4you.space.domain.model.pads.LaunchPadsModel
import com.android4you.space.domain.usecases.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MissionsViewModel @Inject constructor(
    private val getAllLandPadsUseCase: GetAllLandPadsUseCase,
    private val getAllLaunchPadsUseCase: GetAllLaunchPadsUseCase,
) : ViewModel() {

    //    init {
//       // fetchAllLandPads()
//    }
//    private val _newsResponse = MutableStateFlow<LaunchLandPadsViewState>(LaunchLandPadsViewState.Empty)
//    val newsResponse: StateFlow<LaunchLandPadsViewState>
//        get() = _newsResponse
//
    private val stateflowPrivate =
        MutableStateFlow<LaunchPadsViewState>(LaunchPadsViewState.Empty)
    val stateflow: StateFlow<LaunchPadsViewState> = stateflowPrivate

//    fun test() {
//        viewModelScope.launch {
//            _newsResponse.value = LaunchLandPadsViewState.Loading
//            getAllLandPadsUseCase.execute().zip(getAllLaunchPadsUseCase.execute()) {
//                    landPadsList, launchPAdsList ->
//                return@zip Pair(
//                    landPadsList.data,
//                    launchPAdsList.data
//                )
//            }
//                .flowOn(Dispatchers.IO)
//                .catch {
//                    _newsResponse.value = LaunchLandPadsViewState.Error("Error Aravind")
//                }
//                .collect { result ->
//                    // stateflowPrivate.value = LaunchLandPadsViewState.Success(result.first, result.second)
//                   // _newsResponse.value = LaunchLandPadsViewState.Success("Manu Aravind")
//                }
//        }
//    }
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
        viewModelScope.launch {
            stateflowPrivate.value = LaunchPadsViewState.Loading
            getAllLaunchPadsUseCase.execute()
                .flowOn(Dispatchers.IO)
                .catch {
                    stateflowPrivate.value = LaunchPadsViewState.Error("Error")
                }
                .collect {
                    stateflowPrivate.emit(LaunchPadsViewState.Success(it.data))
                }
        }
    }
}

sealed class LaunchPadsViewState {
    data class Error(val message: String) : LaunchPadsViewState()
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
