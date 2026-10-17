package com.example.vk_education_application.feature.applist.data.remote

import com.example.vk_education_application.feature.applist.data.remote.dto.AppDto
import retrofit2.http.GET

interface AppApiService {
    @GET("apps")
    suspend fun getApps(): List<AppDto>
}