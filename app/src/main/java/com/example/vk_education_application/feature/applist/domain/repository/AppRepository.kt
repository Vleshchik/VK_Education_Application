package com.example.vk_education_application.feature.applist.domain.repository

import com.example.vk_education_application.feature.applist.domain.model.App

interface AppRepository {
    suspend fun getApps(): List<App>
}