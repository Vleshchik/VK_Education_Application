package com.example.vk_education_application.feature.applist.domain.repository

import com.example.vk_education_application.core.model.App

/**
 * Репозиторий каталога приложений (уровень domain).
 *
 * Абстракция над источником данных: экран не знает, откуда берутся приложения —
 * из сети, локального кэша или заглушки.
 */
interface AppListRepository {

    /**
     * Возвращает все приложения каталога.
     *
     * @return список доменных моделей [App]; пустой список, если данных нет.
     */
    suspend fun getApps(): List<App>
}