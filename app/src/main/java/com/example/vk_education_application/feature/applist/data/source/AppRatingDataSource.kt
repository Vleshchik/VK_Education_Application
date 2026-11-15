package com.example.vk_education_application.feature.applist.data.source

import kotlinx.coroutines.delay
import javax.inject.Inject

class AppRatingDataSource @Inject constructor() {
    suspend fun getRating(id: String): Float {
        delay(1500) // Эмуляция задержки сети
        return (30..50).random() / 10f
    }
}