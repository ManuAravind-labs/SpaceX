package com.android4you.space.presentation.payloads.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android4you.space.domain.model.payloads.PayloadsModel
import com.android4you.space.domain.usecases.GetAllPayloadsUseCase
import com.android4you.space.utils.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PayloadsViewModel @Inject constructor(
    private val networkHelper: NetworkHelper,
    private val getAllPayloadsUseCase: GetAllPayloadsUseCase,
) : ViewModel() {

    private val payloadsStateflowPrivate = MutableStateFlow<PayloadsViewState>(PayloadsViewState.Empty)
    val payloadsStateflow: StateFlow<PayloadsViewState> get() = payloadsStateflowPrivate

    init {
        fetchAllLandPads()
    }
    private fun fetchAllLandPads() {
        if (networkHelper.isNetworkConnected()) {
            viewModelScope.launch {
                payloadsStateflowPrivate.value = PayloadsViewState.Loading(true)
                getAllPayloadsUseCase.execute()
                    .flowOn(Dispatchers.Main)
                    .catch {
                        payloadsStateflowPrivate.value = PayloadsViewState.Loading(false)
                        payloadsStateflowPrivate.value = PayloadsViewState.UnknownError
                    }
                    .collect { result ->
                        payloadsStateflowPrivate.value = PayloadsViewState.Loading(false)
                        if (result.data.isNullOrEmpty())
                            payloadsStateflowPrivate.value = PayloadsViewState.NotFoundError
                        else {
                            payloadsStateflowPrivate.value = PayloadsViewState.Success(result.data)
                        }
                    }
            }
        } else {
            payloadsStateflowPrivate.value = PayloadsViewState.NoNetworkError
        }
    }
}

sealed class PayloadsViewState {
    object NoNetworkError : PayloadsViewState()
    object NotFoundError : PayloadsViewState()
    object UnknownError : PayloadsViewState()
    object Empty : PayloadsViewState()
    data class Loading(val isLoading: Boolean) : PayloadsViewState()
    data class Success(val payloads: List<PayloadsModel>?) :
        PayloadsViewState()
}
