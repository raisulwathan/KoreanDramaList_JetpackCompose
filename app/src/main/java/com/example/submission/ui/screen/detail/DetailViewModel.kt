package com.example.submission.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.submission.data.SerialRepository
import com.example.submission.model.Serial
import com.example.submission.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: SerialRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Serial>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Serial>>
        get() = _uiState

    fun getSerialById(serialId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getSerialById(serialId))
        }
    }

    fun addToFavorite(serial: Serial) {
        repository.addToFavorite(serial)
    }
}