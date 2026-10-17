package com.example.vk_education_application.feature.applist.data.repository

import com.example.vk_education_application.feature.applist.data.mapper.toDomain
import com.example.vk_education_application.feature.applist.data.remote.AppApiService
import com.example.vk_education_application.feature.applist.domain.model.App
import com.example.vk_education_application.feature.applist.domain.repository.AppRepository

class AppRepositoryImpl(
    private val apiService: AppApiService,
) : AppRepository {
    override suspend fun getApps(): List<App> =
        apiService.getApps().map { it.toDomain() }
}