package com.example.vk_education_application.feature.applist.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


/**
 * DTO приложения для Moshi-десериализации локального JSON.
 *
 * Имена полей в JSON заданы явно через [Json], чтобы не зависеть
 * от соглашения об именовании.
 *
 * @property id уникальный идентификатор приложения.
 * @property name название приложения.
 * @property description краткое описание.
 * @property category категория в виде строки (`"APP"` или `"GAME"`).
 * @property iconUrl URL иконки.
 */
@JsonClass(generateAdapter = true)
data class AppDto(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "description") val description: String,
    @Json(name = "category") val category: String,
    @Json(name = "icon_url") val iconUrl: String,
)