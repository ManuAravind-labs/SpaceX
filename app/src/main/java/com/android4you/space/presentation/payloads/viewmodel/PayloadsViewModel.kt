package com.android4you.space.presentation.payloads.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android4you.space.domain.model.payloads.PayloadsModel
import com.android4you.space.domain.usecases.GetAllPayloadsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PayloadsViewModel @Inject constructor(
    private val getAllPayloadsUseCase: GetAllPayloadsUseCase,
) : ViewModel() {

    private val payloadsStateflowPrivate = MutableStateFlow<PayloadsViewState>(PayloadsViewState.Empty)
    val payloadsStateflow: StateFlow<PayloadsViewState> get() = payloadsStateflowPrivate

    init {
        fetchAllLandPads()
    }
    private fun fetchAllLandPads() {
        viewModelScope.launch {
            payloadsStateflowPrivate.value = PayloadsViewState.Loading(true)
            getAllPayloadsUseCase.execute()
                .flowOn(Dispatchers.IO)
                .catch {
                    payloadsStateflowPrivate.value = PayloadsViewState.Loading(false)
                    payloadsStateflowPrivate.value = PayloadsViewState.Error("Error")
                }
                .collect { result ->
                    payloadsStateflowPrivate.value = PayloadsViewState.Loading(false)
                    payloadsStateflowPrivate.value = PayloadsViewState.Success(result.data)
                }
        }
    }
}

sealed class PayloadsViewState {
    data class Error(val message: String) : PayloadsViewState()
    object Empty : PayloadsViewState()
    data class Loading(val isLoading: Boolean) : PayloadsViewState()
    data class Success(val payloads: List<PayloadsModel>?) :
        PayloadsViewState()
}
