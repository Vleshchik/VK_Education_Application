package com.example.vk_education_application.feature.appdetails.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.vk_education_application.core.model.App
import com.example.vk_education_application.feature.appdetails.presentation.InstallState

/**
 * Прокручиваемое содержимое карточки приложения.
 *
 * Состоит из шапки [AppDetailsHeader], блока описания и витрины [ShowcaseBlock].
 *
 * @param app приложение, карточка которого открыта.
 * @param showcaseApps приложения для блока «Витрина».
 * @param onShowcaseAppClick открывает приложение из витрины по его `id`.
 * @param modifier модификатор списка.
 */
@Composable
fun AppDetailsContent(
    app: App,
    showcaseApps: List<App>,
    onShowcaseAppClick: (String) -> Unit,
    onInstallClick: () -> Unit,
    installState: InstallState,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {

        // 1. Иконка, название и краткое описание
        item {
            AppDetailsHeader(app = app)
        }

        item {
            Box(modifier = Modifier.fillMaxWidth().padding(16.dp), contentAlignment = Alignment.Center) {
                InstallButton(installState = installState, onClick = onInstallClick)
            }
        }

        // 2. Полное описание (если оно отличается от краткого)
        item {
            Column(Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                Text("Описание", style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.height(4.dp))
                Text(app.description, style = MaterialTheme.typography.bodyMedium)
            }
        }

        // 3. Блок Витрины
        item {
            ShowcaseBlock(
                apps = showcaseApps,
                onAppClick = onShowcaseAppClick,
            )
        }

        item { Spacer(Modifier.height(24.dp)) }
    }
}

@Composable
fun InstallButton(installState: InstallState, onClick: () -> Unit) {
    when (installState) {
        is InstallState.NotInstalled -> {
            Button(onClick = onClick) { Text("Установить") }
        }
        is InstallState.Installing -> {
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
            ) {
                Text("Отменить (${installState.progress}%)")
            }
        }
        is InstallState.Installed -> {
            Button(onClick = {}, enabled = false) { Text("Установлено") }
        }
    }
}