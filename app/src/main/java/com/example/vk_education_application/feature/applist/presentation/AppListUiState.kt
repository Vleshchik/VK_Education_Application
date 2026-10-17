package com.example.vk_education_application.feature.applist.presentation

import com.example.vk_education_application.feature.applist.domain.model.App

data class AppListUiState(
    val isLoading: Boolean = false,
    val apps: List<App> = emptyList(),
    val error: String? = null,
)