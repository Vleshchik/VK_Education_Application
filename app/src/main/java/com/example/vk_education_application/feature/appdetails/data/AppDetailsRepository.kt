package com.example.vk_education_application.feature.appdetails.data

import com.example.vk_education_application.core.model.App

/**
 * Репозиторий карточки приложения (уровень domain).
 */
interface AppDetailsRepository {

    /**
     * Находит приложение по идентификатору.
     *
     * @param appId идентификатор приложения.
     * @return доменная модель [App] или `null`, если приложение не найдено.
     */
    suspend fun getAppDetails(appId: Int): App?

    /**
     * Возвращает приложения для блока «Витрина».
     *
     * @return список приложений витрины.
     */
    suspend fun getShowcaseApps(): List<App>
}