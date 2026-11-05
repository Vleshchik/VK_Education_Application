package com.example.vk_education_application.feature.applist.data.repository

import com.example.vk_education_application.core.model.App
import com.example.vk_education_application.feature.applist.data.mapper.AppDtoMapper
import com.example.vk_education_application.feature.applist.data.source.AppLocalDataSource
import com.example.vk_education_application.feature.applist.domain.repository.AppListRepository
import javax.inject.Inject

/**
 * Реализация [AppListRepository] на основе локального источника данных.
 *
 * Получает DTO из [AppLocalDataSource] и конвертирует их в доменные модели
 * через [AppDtoMapper].
 *
 * @param dataSource локальный источник DTO.
 * @param mapper маппер DTO → domain-модель.
 */
class AppListRepositoryImpl @Inject constructor(
    private val dataSource: AppLocalDataSource,
    private val mapper: AppDtoMapper,
) : AppListRepository {

    /** Возвращает каталог приложений, преобразованный в доменные модели. */
    override suspend fun getApps(): List<App> {
        val dtos = dataSource.getApps()      // 1) получаем DTO
        return mapper.map(dtos)              // 2) конвертируем в Domain
    }
}