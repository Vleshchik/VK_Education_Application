package com.example.vk_education_application.feature.applist.data.mapper

import com.example.vk_education_application.core.model.App
import com.example.vk_education_application.core.model.Category
import com.example.vk_education_application.feature.applist.data.dto.AppDto
import javax.inject.Inject


class AppDtoMapper @Inject constructor() {

    fun map(dto: AppDto): App = App(
        id = dto.id,
        name = dto.name,
        description = dto.description,
        category = mapCategory(dto.category),
        iconUrl = dto.iconUrl,
    )

    fun map(list: List<AppDto>): List<App> = list.map(::map)

    private fun mapCategory(rawCategory: String): Category {
        // Приводим к нижнему регистру, чтобы "GAME", "game" и "Game" работали одинаково
        return when (rawCategory.lowercase()) {
            "game" -> Category.GAME
            "app" -> Category.APP
            else -> Category.APP // Значение по умолчанию, если пришло что-то неизвестное
        }
    }
}