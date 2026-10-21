package com.example.vk_education_application.feature.appdetails.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.vk_education_application.core.model.App

@Composable
fun AppDetailsContent(
    app: App,
    showcaseApps: List<App>,
    onBackClick: () -> Unit,
    onShowcaseAppClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {

        item {
            AppDetailsHeader(app = app, onBackClick = onBackClick)
        }

        item {
            Column(Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                Text("Описание", style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.height(4.dp))
                Text(app.description, style = MaterialTheme.typography.bodyMedium)
            }
        }

        // 🔥 ВИТРИНА — в самом низу карточки
        item {
            ShowcaseBlock(
                apps = showcaseApps,
                onAppClick = onShowcaseAppClick,
            )
        }

        item { Spacer(Modifier.height(24.dp)) }
    }
}