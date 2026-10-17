package com.example.vk_education_application.feature.applist.domain.usecase

import com.example.vk_education_application.feature.applist.domain.model.App
import com.example.vk_education_application.feature.applist.domain.repository.AppRepository

class GetAppsUseCase(
    private val repository: AppRepository,
) {
    suspend operator fun invoke(): List<App> = repository.getApps()
}