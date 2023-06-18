package com.example.submission.ui.screen.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.submission.data.SerialRepository
import com.example.submission.model.Serial
import com.example.submission.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoriteViewModel(private val repository: SerialRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Serial>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Serial>>>
        get() = _uiState

    fun getAddedSerialFavorite() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getAddedSeriesFavorite().collect { listSerial ->
                _uiState.value = UiState.Success(listSerial)
            }
        }
    }

    fun updateSerialFavorite(serialId: Long) {
        viewModelScope.launch {
            repository.updateSeriesFavorite(serialId)
        }
    }

    init {
        viewModelScope.launch {
            repository.getAddedSeriesFavorite().collect { serialList ->
                _uiState.value = UiState.Success(serialList)
            }
        }
    }
}