package com.example.vk_education_application.feature.applist.data.repository

import android.util.Log
import com.example.vk_education_application.core.model.App
import com.example.vk_education_application.feature.applist.data.mapper.AppDtoMapper
import com.example.vk_education_application.feature.applist.data.remote.AppCatalogApi
import com.example.vk_education_application.feature.applist.data.source.AppLocalDataSource
import com.example.vk_education_application.feature.applist.data.source.AppRatingDataSource
import com.example.vk_education_application.feature.applist.domain.repository.AppListRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class AppListRepositoryImpl @Inject constructor(
    private val api: AppCatalogApi,
    private val localDataSource: AppLocalDataSource,
    private val mapper: AppDtoMapper,
    private val ratingDataSource: AppRatingDataSource,
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

    override suspend fun loadRatingsSequentially(ids: List<String>): Map<String, Float> {
        val startTime = System.currentTimeMillis()
        val ratings = mutableMapOf<String, Float>()

        for (id in ids) {
            ratings[id] = ratingDataSource.getRating(id)
        }

        val duration = System.currentTimeMillis() - startTime
        Log.d("RatingsTest", "Последовательно загружено за: $duration мс")
        return ratings
    }

    override suspend fun loadRatingsInParallel(ids: List<String>): Map<String, Float> = coroutineScope {
        val startTime = System.currentTimeMillis()

        val ratings = ids.map { id ->
            async { id to ratingDataSource.getRating(id) }
        }.awaitAll().toMap()

        val duration = System.currentTimeMillis() - startTime
        Log.d("RatingsTest", "Параллельно загружено за: $duration мс")
        ratings
    }
}