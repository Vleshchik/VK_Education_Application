package com.example.vk_education_application.feature.applist.data.remote

import com.example.vk_education_application.feature.applist.data.remote.dto.AppDto
import retrofit2.http.GET

/**
 * Retrofit-интерфейс API каталога приложений.
 *
 * Сейчас не используется основным источником данных (`AppLocalDataSource`),
 * но оставлен для перехода на реальный бэкенд.
 */
interface AppApiService {

    /**
     * Запрашивает список приложений.
     *
     * @return список DTO приложений.
     */
    @GET("apps")
    suspend fun getApps(): List<AppDto>
}