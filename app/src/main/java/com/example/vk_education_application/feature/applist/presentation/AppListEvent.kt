package com.example.vk_education_application.feature.applist.presentation

/**
 * Разовые события экрана списка приложений.
 *
 * В отличие от [AppListUiState] события не хранят состояние, а доставляются
 * подписчику один раз (через `Channel` во ViewModel).
 */
sealed interface AppListEvent {

    /**
     * Показывает snackbar с текстом [message].
     */
    data class ShowSnackbar(val message: String) : AppListEvent
}