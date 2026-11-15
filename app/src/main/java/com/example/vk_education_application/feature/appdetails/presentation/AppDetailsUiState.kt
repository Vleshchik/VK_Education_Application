package com.example.vk_education_application.feature.appdetails.presentation

import com.example.vk_education_application.core.model.App

sealed interface InstallState {
    data object NotInstalled : InstallState
    data class Installing(val progress: Int) : InstallState
    data object Installed : InstallState
}

/**
 * Состояние экрана карточки приложения.
 */
sealed interface AppDetailsUiState {

    /** Данные загружаются; на экране показывается индикатор прогресса. */
    data object Loading : AppDetailsUiState

    /**
     * Загрузка завершилась ошибкой.
     *
     * @property message текст ошибки для отображения пользователю.
     */
    data class Error(val message: String) : AppDetailsUiState

    /**
     * Данные успешно загружены.
     *
     * @property app приложение, карточка которого открыта.
     * @property showcaseApps приложения для блока «Витрина».
     */
    data class Content(
        val app: App,
        val showcaseApps: List<App>,
        val installState: InstallState = InstallState.NotInstalled,
    ) : AppDetailsUiState
}