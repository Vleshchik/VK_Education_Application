package com.example.vk_education_application.feature.appdetails.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.vk_education_application.feature.appdetails.presentation.components.AppDetailsContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDetailsScreen(
    appId: String,
    onBackClick: () -> Unit,
    onShowcaseAppClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AppDetailsViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Карточка приложения") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Назад"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues) // <-- Отступ от TopAppBar
        ) {
            when (val s = state) {
                AppDetailsUiState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                is AppDetailsUiState.Error -> {
                    Text(
                        text = s.message,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                is AppDetailsUiState.Content -> {
                    AppDetailsContent(
                        app = s.app,
                        showcaseApps = s.showcaseApps,
                        onShowcaseAppClick = onShowcaseAppClick,
                        modifier = Modifier.fillMaxSize(),
                    )
                }
            }
        }
    }
}