package com.example.vk_education_application.feature.applist.data.mapper

import com.example.vk_education_application.core.model.App
import com.example.vk_education_application.core.model.Category
import com.example.vk_education_application.feature.applist.data.dto.AppDto
import javax.inject.Inject


/**
 * Маппер между сетевыми/локальными DTO и доменной моделью [App].
 *
 * Отвечает за преобразование строкового значения категории в типобезопасный
 * enum [Category].
 */
class AppDtoMapper @Inject constructor() {

    /**
     * Преобразует один DTO в доменную модель.
     *
     * @param dto исходный DTO приложения.
     * @return доменная модель [App].
     */
    fun map(dto: AppDto): App = App(
        id = dto.id,
        name = dto.name,
        description = dto.description,
        category = mapCategory(dto.category),
        iconUrl = dto.iconUrl,
    )

    /**
     * Преобразует список DTO в список доменных моделей.
     *
     * @param list список DTO приложений.
     * @return список моделей [App] в том же порядке.
     */
    fun map(list: List<AppDto>): List<App> = list.map(::map)

    /**
     * Приводит строковую категорию к [Category] без учёта регистра.
     *
     * Неизвестное значение трактуется как [Category.APP].
     */
    private fun mapCategory(rawCategory: String): Category {
        // Приводим к нижнему регистру, чтобы "GAME", "game" и "Game" работали одинаково
        return when (rawCategory.lowercase()) {
            "game" -> Category.GAME
            "app" -> Category.APP
            else -> Category.APP // Значение по умолчанию, если пришло что-то неизвестное
        }
    }
}