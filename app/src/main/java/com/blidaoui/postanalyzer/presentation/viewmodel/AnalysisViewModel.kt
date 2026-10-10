package com.blidaoui.postanalyzer.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blidaoui.postanalyzer.data.local.AnalysisEntity
import com.blidaoui.postanalyzer.data.repository.AnalysisRepository
import com.blidaoui.postanalyzer.domain.model.AnalysisResult
import com.blidaoui.postanalyzer.domain.model.Platform
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AnalysisUiState(
    val isLoading: Boolean = false,
    val result: AnalysisResult? = null,
    val error: String? = null
)

@HiltViewModel
class AnalysisViewModel @Inject constructor(
    private val repository: AnalysisRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AnalysisUiState())
    val uiState: StateFlow<AnalysisUiState> = _uiState.asStateFlow()

    fun analyzeText(platform: Platform, text: String) {
        if (text.isBlank()) return
        _uiState.value = _uiState.value.copy(isLoading = true, error = null)
        viewModelScope.launch {
            try {
                val result = repository.analyzeText(platform, text)
                _uiState.value = _uiState.value.copy(isLoading = false, result = result, error = null)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isLoading = false, error = e.message ?: "حدث خطأ أثناء التحليل")
            }
        }
    }

    fun analyzeImage(platform: Platform, description: String) {
        if (description.isBlank()) return
        _uiState.value = _uiState.value.copy(isLoading = true, error = null)
        viewModelScope.launch {
            try {
                val result = repository.analyzeImage(platform, description)
                _uiState.value = _uiState.value.copy(isLoading = false, result = result, error = null)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isLoading = false, error = e.message ?: "حدث خطأ أثناء تحليل الصورة")
            }
        }
    }

    fun analyzeVideo(platform: Platform, description: String) {
        if (description.isBlank()) return
        _uiState.value = _uiState.value.copy(isLoading = true, error = null)
        viewModelScope.launch {
            try {
                val result = repository.analyzeVideo(platform, description)
                _uiState.value = _uiState.value.copy(isLoading = false, result = result, error = null)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isLoading = false, error = e.message ?: "حدث خطأ أثناء تحليل الفيديو")
            }
        }
    }

    fun setResult(result: AnalysisResult) {
        _uiState.value = _uiState.value.copy(result = result)
    }

    fun clearState() {
        _uiState.value = AnalysisUiState()
    }
}

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val repository: AnalysisRepository
) : ViewModel() {

    private val _historyState = MutableStateFlow<List<AnalysisEntity>>(emptyList())
    val historyState: StateFlow<List<AnalysisEntity>> = _historyState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getHistory().collect { items ->
                _historyState.value = items
            }
        }
    }

    fun deleteHistoryItem(entity: AnalysisEntity) {
        viewModelScope.launch {
            repository.deleteResult(entity)
        }
    }
}
