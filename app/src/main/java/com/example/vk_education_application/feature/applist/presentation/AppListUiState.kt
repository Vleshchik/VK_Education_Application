package com.example.vk_education_application.feature.applist.presentation

import com.example.vk_education_application.core.model.App

sealed interface AppListUiState {

    /** Загрузка данных */
    data object Loading : AppListUiState

    /** Ошибка загрузки */
    data class Error(val message: String) : AppListUiState

    /** Данные успешно загружены */
    data class Content(val apps: List<App>) : AppListUiState
}