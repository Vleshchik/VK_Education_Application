package com.example.vk_education_application.feature.appdetails.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vk_education_application.feature.appdetails.domain.GetAppDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel экрана карточки приложения.
 *
 * Идентификатор приложения [appId] берётся из `SavedStateHandle` (аргумент навигации
 * `appId`), после чего данные загружаются через [GetAppDetailsUseCase].
 *
 * @param savedStateHandle содержит аргументы навигации, включая `appId`.
 * @param getAppDetails use case загрузки карточки и витрины.
 */
@HiltViewModel
class AppDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getAppDetails: GetAppDetailsUseCase,
) : ViewModel() {

    private val appId: String = checkNotNull(savedStateHandle["appId"])

    private val _uiState = MutableStateFlow<AppDetailsUiState>(AppDetailsUiState.Loading)

    /** Текущее состояние экрана: загрузка, ошибка или контент. */
    val uiState: StateFlow<AppDetailsUiState> = _uiState.asStateFlow()

    private var installJob: Job? = null

    init {
        load()
    }

    /** Загружает данные приложения и список витрины, обновляя [uiState]. */
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

    fun onInstallClick() {
        val current = _uiState.value
        if (current !is AppDetailsUiState.Content) return

        if (current.installState is InstallState.Installing) {
            // Отменяем установку
            installJob?.cancel()
            installJob = null
            _uiState.update {
                if (it is AppDetailsUiState.Content) it.copy(installState = InstallState.NotInstalled) else it
            }
        } else {
            // Запускаем установку
            installJob = viewModelScope.launch {
                emulateInstall()
            }
        }
    }

    private suspend fun emulateInstall() {
        for (progress in 0..100 step 5) {
            delay(150)
            _uiState.update {
                if (it is AppDetailsUiState.Content) {
                    it.copy(installState = InstallState.Installing(progress))
                } else it
            }
        }
        // По окончании
        _uiState.update {
            if (it is AppDetailsUiState.Content) it.copy(installState = InstallState.Installed) else it
        }
    }
}