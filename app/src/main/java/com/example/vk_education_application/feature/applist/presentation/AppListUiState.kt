package com.example.vk_education_application.feature.applist.presentation

import com.example.vk_education_application.core.model.App

/**
 * Состояние экрана списка приложений.
 *
 * Гарантирует, что UI одновременно находится ровно в одном из состояний:
 * загрузка, ошибка или готовый контент.
 */
sealed interface AppListUiState {

    /** Данные загружаются; на экране показывается индикатор прогресса. */
    data object Loading : AppListUiState

    /**
     * Загрузка завершилась ошибкой.
     *
     * @property message текст ошибки для отображения пользователю.
     */
    data class Error(val message: String) : AppListUiState

    /**
     * Данные успешно загружены.
     *
     * @property apps приложения для отображения в сетке.
     */
    data class Content(val apps: List<App>) : AppListUiState
}