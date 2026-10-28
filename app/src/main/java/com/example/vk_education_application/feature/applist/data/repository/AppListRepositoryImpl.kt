package com.example.vk_education_application.feature.applist.data.repository

import com.example.vk_education_application.core.model.App
import com.example.vk_education_application.feature.applist.data.mapper.AppDtoMapper
import com.example.vk_education_application.feature.applist.data.source.AppLocalDataSource
import com.example.vk_education_application.feature.applist.domain.repository.AppListRepository
import javax.inject.Inject

class AppListRepositoryImpl @Inject constructor(
    private val dataSource: AppLocalDataSource,
    private val mapper: AppDtoMapper,
) : AppListRepository {

    override suspend fun getApps(): List<App> {
        val dtos = dataSource.getApps()      // 1) получаем DTO
        return mapper.map(dtos)              // 2) конвертируем в Domain
    }
}