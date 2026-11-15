package com.example.vk_education_application.feature.applist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vk_education_application.core.model.App
import com.example.vk_education_application.feature.applist.domain.usecase.GetAppsUseCase
import com.example.vk_education_application.feature.applist.domain.usecase.LoadRatingsInParallelUseCase
import com.example.vk_education_application.feature.applist.domain.usecase.LoadRatingsSequentiallyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named
import kotlin.collections.map

/**
 * ViewModel экрана списка приложений.
 *
 * При создании сразу запускает загрузку каталога через [GetAppsUseCase]
 * и хранит результат в [uiState]. Разовые события отдаются через [events].
 *
 * @param getAppsUseCase use case загрузки списка приложений.
 */
@HiltViewModel
class AppListViewModel @Inject constructor(
    private val getAppsUseCase: GetAppsUseCase,
    private val loadRatingsSequentiallyUseCase: LoadRatingsSequentiallyUseCase,
    private val loadRatingsInParallelUseCase: LoadRatingsInParallelUseCase,
    @Named("AppVersion") private val appVersion: String,
) : ViewModel() {

    private val _uiState = MutableStateFlow<AppListUiState>(AppListUiState.Loading)

    /** Текущее состояние экрана: загрузка, ошибка или контент. */
    val uiState: StateFlow<AppListUiState> = _uiState.asStateFlow()

    private val _events = Channel<AppListEvent>(Channel.BUFFERED)

    /** Поток разовых событий (snackbar и т. п.). */
    val events: Flow<AppListEvent> = _events.receiveAsFlow()

    private val _ratingsLoading = MutableStateFlow(false)
    val ratingsLoading: StateFlow<Boolean> = _ratingsLoading.asStateFlow()

    init {
        loadApps()
        println("Текущая версия приложения: $appVersion")
    }

    /** Обрабатывает нажатие на заголовок тулбара, показывая snackbar. */
    fun onToolbarClick() {
        viewModelScope.launch {
            _events.send(AppListEvent.ShowSnackbar("Show Snackbar"))
        }
    }

    private fun loadApps() {
        viewModelScope.launch {
            _uiState.value = AppListUiState.Loading
            try {
                val apps = getAppsUseCase()
                _uiState.value = AppListUiState.Content(apps)
            } catch (e: Exception) {
                _uiState.value = AppListUiState.Error(
                    e.message ?: "Не удалось загрузить приложения"
                )
            }
        }
    }

    fun loadRatingsSequentially() {
        viewModelScope.launch {
            val current = _uiState.value
            if (current is AppListUiState.Content) {
                _ratingsLoading.value = true
                val ids = current.apps.map { it.id }
                val ratingsMap = loadRatingsSequentiallyUseCase(ids)
                updateAppsWithRatings(current.apps, ratingsMap)
                _ratingsLoading.value = false
            }
        }
    }

    fun loadRatingsInParallel() {
        viewModelScope.launch {
            val current = _uiState.value
            if (current is AppListUiState.Content) {
                _ratingsLoading.value = true
                val ids = current.apps.map { it.id }
                val ratingsMap = loadRatingsInParallelUseCase(ids)
                updateAppsWithRatings(current.apps, ratingsMap)
                _ratingsLoading.value = false
            }
        }
    }

    private fun updateAppsWithRatings(apps: List<App>, ratingsMap: Map<String, Float>) {
        val updatedApps = apps.map { app ->
            app.copy(rating = ratingsMap[app.id])
        }
        _uiState.value = AppListUiState.Content(updatedApps)
    }
}