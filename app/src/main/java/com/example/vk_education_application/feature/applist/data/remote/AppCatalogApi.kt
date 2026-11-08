package com.example.vk_education_application.feature.applist.data.remote

import com.example.vk_education_application.feature.applist.data.dto.AppDto
import retrofit2.http.GET
import retrofit2.http.Path

interface AppCatalogApi {

    // Получение всего каталога
    @GET("catalog")
    suspend fun getCatalog(): List<AppDto>

    // Получение информации о конкретном приложении по id
    // (Пока не используется, но создадим для выполнения задания)
    @GET("{id}")
    suspend fun getAppDetails(@Path("id") id: String): AppDto
}