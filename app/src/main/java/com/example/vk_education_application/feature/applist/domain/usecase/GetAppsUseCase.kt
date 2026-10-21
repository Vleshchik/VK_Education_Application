package com.example.vk_education_application.feature.applist.domain.usecase

import com.example.vk_education_application.core.model.App
import com.example.vk_education_application.feature.applist.domain.repository.AppListRepository
import javax.inject.Inject

class GetAppsUseCase @Inject constructor(
    private val repository: AppListRepository,
) {
    suspend operator fun invoke(): List<App> = repository.getApps()
}