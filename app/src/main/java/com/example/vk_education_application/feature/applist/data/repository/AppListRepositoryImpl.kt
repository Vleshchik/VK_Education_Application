package com.example.vk_education_application.feature.applist.data.repository

import com.example.vk_education_application.core.model.App
import com.example.vk_education_application.feature.applist.data.mapper.AppDtoMapper
import com.example.vk_education_application.feature.applist.data.remote.AppCatalogApi
import com.example.vk_education_application.feature.applist.data.source.AppLocalDataSource
import com.example.vk_education_application.feature.applist.domain.repository.AppListRepository
import javax.inject.Inject

class AppListRepositoryImpl @Inject constructor(
    private val api: AppCatalogApi,
    private val localDataSource: AppLocalDataSource,
    private val mapper: AppDtoMapper,
) : AppListRepository {

    /** Возвращает каталог приложений, преобразованный в доменные модели. */
    override suspend fun getApps(): List<App> {
        return try {
            // 1. Пытаемся получить данные с сервера
            val remoteDtos = api.getCatalog()

            // 2. Если данные не пустые — сохраняем их в SharedPreferences
            if (remoteDtos.isNotEmpty()) {
                localDataSource.saveCatalog(remoteDtos)
            }

            // 3. Отдаем данные в UI
            mapper.map(remoteDtos)
        } catch (e: Exception) {
            // 4. Если сеть упала, достаем данные из кэша
            val cachedDtos = localDataSource.getCatalog()
            if (cachedDtos != null) {
                mapper.map(cachedDtos)
            } else {
                throw e // Если кэша тоже нет — пробрасываем ошибку в UI
            }
        }
    }
}