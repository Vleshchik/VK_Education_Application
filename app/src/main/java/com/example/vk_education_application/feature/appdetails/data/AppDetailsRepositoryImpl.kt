package com.example.vk_education_application.feature.appdetails.data

import com.example.vk_education_application.core.data.AppCatalog
import com.example.vk_education_application.core.model.App
import javax.inject.Inject

class AppDetailsRepositoryImpl @Inject constructor() : AppDetailsRepository {

    override suspend fun getAppDetails(appId: Int): App? {
        return AppCatalog.getById(appId)
    }

    override suspend fun getShowcaseApps(): List<App> {
        return AppCatalog.getShowcaseApps()
    }
}