package com.example.vk_education_application.feature.appdetails.data

import com.example.vk_education_application.core.model.App

interface AppDetailsRepository {
    suspend fun getAppDetails(appId: Int): App?
    suspend fun getShowcaseApps(): List<App>
}