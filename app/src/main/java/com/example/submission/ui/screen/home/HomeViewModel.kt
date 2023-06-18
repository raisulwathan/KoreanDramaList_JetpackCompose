package com.example.submission.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.submission.data.SerialRepository
import com.example.submission.model.Serial
import com.example.submission.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: SerialRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Serial>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Serial>>>
        get() = _uiState

    private var currentKeyword = ""

    fun getAllSeries(keyword: String = "") {
        viewModelScope.launch {
            repository.getAllSeries(keyword)
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }

    fun searchSerial(keyword: String) {
        currentKeyword = keyword
        getAllSeries(keyword)
    }
}