package com.example.vk_education_application.feature.applist.data.remote.dto

/**
 * DTO приложения для работы с удалённым API.
 *
 * @property id уникальный идентификатор приложения.
 * @property name название приложения.
 * @property description краткое описание.
 * @property category категория в виде строки (`"APP"` или `"GAME"`).
 * @property iconUrl URL иконки.
 */
data class AppDto(
    val id: Int,
    val name: String,
    val description: String,
    val category: String,
    val iconUrl: String,
)