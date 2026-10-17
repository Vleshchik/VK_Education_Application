package com.example.vk_education_application

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import com.example.vk_education_application.feature.applist.presentation.AppListScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                AppListScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .safeDrawingPadding(),
                )
            }
        }
    }
}