package com.example.vk_education_application.feature.applist.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class AppDto(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "description") val description: String,
    @Json(name = "category") val category: String,
    @Json(name = "icon_url") val iconUrl: String,
)