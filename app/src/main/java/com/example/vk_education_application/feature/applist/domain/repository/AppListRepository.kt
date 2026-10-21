package com.example.vk_education_application.feature.applist.domain.repository

import com.example.vk_education_application.core.model.App

interface AppListRepository {
    suspend fun getApps(): List<App>
}