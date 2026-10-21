package com.example.vk_education_application.feature.appdetails.domain

import com.example.vk_education_application.core.model.App
import com.example.vk_education_application.feature.appdetails.data.AppDetailsRepository
import javax.inject.Inject

data class AppDetailsData(
    val app: App,
    val showcaseApps: List<App>,
)

class GetAppDetailsUseCase @Inject constructor(
    private val repository: AppDetailsRepository,
) {
    suspend operator fun invoke(appId: Int): AppDetailsData? {
        val app = repository.getAppDetails(appId) ?: return null
        val showcase = repository.getShowcaseApps()
        return AppDetailsData(app = app, showcaseApps = showcase)
    }
}