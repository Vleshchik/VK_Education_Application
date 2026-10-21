package com.example.vk_education_application.feature.applist.data.repository

import com.example.vk_education_application.core.data.AppCatalog
import com.example.vk_education_application.core.model.App
import com.example.vk_education_application.feature.applist.domain.repository.AppListRepository
import javax.inject.Inject

class AppListRepositoryImpl @Inject constructor() : AppListRepository {

    override suspend fun getApps(): List<App> {
        return AppCatalog.allApps
    }
}