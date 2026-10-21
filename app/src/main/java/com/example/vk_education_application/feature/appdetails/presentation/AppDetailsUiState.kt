package com.example.vk_education_application.feature.appdetails.presentation

import com.example.vk_education_application.core.model.App

sealed interface AppDetailsUiState {
    data object Loading : AppDetailsUiState
    data class Error(val message: String) : AppDetailsUiState
    data class Content(
        val app: App,
        val showcaseApps: List<App>,
    ) : AppDetailsUiState
}