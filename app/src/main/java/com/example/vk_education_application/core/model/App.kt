package com.example.vk_education_application.core.model

/**
 * Доменная модель приложения каталога.
 *
 * Не зависит от слоя данных и используется во всех feature-модулях.
 *
 * @property id уникальный идентификатор приложения.
 * @property name название приложения.
 * @property description краткое описание.
 * @property category категория ([Category]).
 * @property iconUrl URL иконки.
 */
data class App(
    val id: Int,
    val name: String,
    val description: String,
    val category: Category,
    val iconUrl: String,
)
