package com.example.vk_education_application.feature.applist.presentation

sealed interface AppListEvent {
    data class ShowSnackbar(val message: String) : AppListEvent
}