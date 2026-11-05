package com.example.vk_education_application

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import com.example.vk_education_application.core.navigation.AppNavHost
import dagger.hilt.android.AndroidEntryPoint

/**
 * Единственная Activity приложения.
 *
 * Включает режим edge-to-edge и размещает весь UI в Compose-дереве,
 * начиная с [AppNavHost]. Контент получает отступы под системные панели
 * через `safeDrawingPadding()`.
 *
 * [AndroidEntryPoint] позволяет Hilt внедрять зависимости в Activity и её ViewModel.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    /** Настраивает edge-to-edge и устанавливает корневой Compose-контент. */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                AppNavHost(
                    modifier = Modifier
                        .fillMaxSize()
                        .safeDrawingPadding(),
                )
            }
        }
    }
}