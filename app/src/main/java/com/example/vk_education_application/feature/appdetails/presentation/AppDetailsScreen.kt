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