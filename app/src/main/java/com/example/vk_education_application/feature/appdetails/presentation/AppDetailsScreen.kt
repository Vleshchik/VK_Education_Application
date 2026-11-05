package com.example.vk_education_application.feature.appdetails.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.vk_education_application.feature.appdetails.presentation.components.AppDetailsContent

/**
 * Экран карточки приложения.
 *
 * Подписывается на [AppDetailsViewModel.uiState] и показывает индикатор загрузки,
 * текст ошибки либо контент через [AppDetailsContent].
 *
 * @param appId идентификатор приложения; используется ViewModel для загрузки данных.
 * @param onBackClick обработчик кнопки «Назад».
 * @param onShowcaseAppClick открывает другое приложение из блока «Витрина» по его `id`.
 * @param modifier модификатор корневого контейнера.
 * @param viewModel ViewModel экрана; по умолчанию создаётся Hilt.
 */
@Composable
fun AppDetailsScreen(
    appId: Int,
    onBackClick: () -> Unit,
    onShowcaseAppClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AppDetailsViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    when (val s = state) {
        AppDetailsUiState.Loading -> {
            Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is AppDetailsUiState.Error -> {
            Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = s.message, style = MaterialTheme.typography.bodyLarge)
            }
        }

        is AppDetailsUiState.Content -> {
            AppDetailsContent(
                app = s.app,
                showcaseApps = s.showcaseApps,
                onBackClick = onBackClick,
                onShowcaseAppClick = onShowcaseAppClick,
                modifier = modifier,
            )
        }
    }
}