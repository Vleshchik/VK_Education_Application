package com.example.vk_education_application.feature.applist.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Заголовок экрана со списком приложений.
 *
 * Вся строка кликабельна; нажатие передаётся в [onClick]
 * (сейчас используется для демонстрационного snackbar).
 *
 * @param onClick обработчик нажатия на тулбар.
 * @param modifier модификатор корневого `Row`.
 */
@Composable
fun AppListToolbar(
    onTitleClick: () -> Unit,
    onLoadSequential: () -> Unit,
    onLoadParallel: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth().padding(16.dp)) {
        Text(
            text = "Приложения",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.clickable(onClick = onTitleClick) // Клик по заголовку = снекбар
        )

        Spacer(Modifier.height(8.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(
                onClick = onLoadSequential,
                modifier = Modifier.weight(1f)
            ) {
                Text("Последовательно", fontSize = 12.sp)
            }
            Button(
                onClick = onLoadParallel,
                modifier = Modifier.weight(1f)
            ) {
                Text("Параллельно", fontSize = 12.sp)
            }
        }
    }
}