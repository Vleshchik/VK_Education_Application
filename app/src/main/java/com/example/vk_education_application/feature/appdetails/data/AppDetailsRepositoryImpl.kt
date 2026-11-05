package com.example.vk_education_application.feature.appdetails.data

import com.example.vk_education_application.core.model.App
import com.example.vk_education_application.feature.applist.data.mapper.AppDtoMapper
import com.example.vk_education_application.feature.applist.data.source.AppLocalDataSource
import javax.inject.Inject

/**
 * Реализация [AppDetailsRepository] поверх локального источника данных.
 *
 * Ищет нужное приложение в общем каталоге и формирует витрину из первых
 * девяти приложений каталога.
 *
 * @param dataSource локальный источник DTO.
 * @param mapper маппер DTO → domain-модель.
 */
class AppDetailsRepositoryImpl @Inject constructor(
    private val dataSource: AppLocalDataSource,
    private val mapper: AppDtoMapper,
) : AppDetailsRepository {

    /** Ищет приложение по `id` в каталоге и маппит его в доменную модель. */
    override suspend fun getAppDetails(appId: Int): App? =
        dataSource.getApps()
            .firstOrNull { it.id == appId }
            ?.let(mapper::map)

    /** Возвращает первые девять приложений каталога в качестве витрины. */
    override suspend fun getShowcaseApps(): List<App> =
        mapper.map(dataSource.getApps().take(9))
}