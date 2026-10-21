package com.example.vk_education_application.feature.appdetails.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vk_education_application.feature.appdetails.domain.GetAppDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getAppDetails: GetAppDetailsUseCase,
) : ViewModel() {

    private val appId: Int = checkNotNull(savedStateHandle["appId"])

    private val _uiState = MutableStateFlow<AppDetailsUiState>(AppDetailsUiState.Loading)
    val uiState: StateFlow<AppDetailsUiState> = _uiState.asStateFlow()

    init {
        load()
    }

    private fun load() {
        viewModelScope.launch {
            _uiState.value = AppDetailsUiState.Loading
            try {
                val result = getAppDetails(appId)
                _uiState.value = if (result == null) {
                    AppDetailsUiState.Error("Приложение не найдено")
                } else {
                    AppDetailsUiState.Content(
                        app = result.app,
                        showcaseApps = result.showcaseApps,
                    )
                }
            } catch (e: Exception) {
                _uiState.value = AppDetailsUiState.Error(
                    e.message ?: "Не удалось загрузить данные",
                )
            }
        }
    }
}