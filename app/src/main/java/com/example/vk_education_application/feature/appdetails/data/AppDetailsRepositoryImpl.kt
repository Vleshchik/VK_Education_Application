package com.example.vk_education_application.feature.appdetails.data

import com.example.vk_education_application.core.model.App
import com.example.vk_education_application.feature.applist.data.mapper.AppDtoMapper
import com.example.vk_education_application.feature.applist.data.source.AppLocalDataSource
import javax.inject.Inject

class AppDetailsRepositoryImpl @Inject constructor(
    private val dataSource: AppLocalDataSource,
    private val mapper: AppDtoMapper,
) : AppDetailsRepository {

    override suspend fun getAppDetails(appId: Int): App? =
        dataSource.getApps()
            .firstOrNull { it.id == appId }
            ?.let(mapper::map)

    override suspend fun getShowcaseApps(): List<App> =
        mapper.map(dataSource.getApps().take(9))
}