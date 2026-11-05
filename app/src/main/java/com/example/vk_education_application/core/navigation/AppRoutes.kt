package com.example.vk_education_application.core.navigation

/**
 * Маршруты навигации приложения.
 *
 * Содержит константы для `NavHost`, имя аргумента `appId` и хелпер
 * для построения маршрута карточки приложения.
 */
object AppRoutes {

    /** Маршрут экрана списка приложений. */
    const val APP_LIST = "app_list"

    /** Имя аргумента навигации с идентификатором приложения. */
    const val ARG_APP_ID = "appId"

    /** Базовый маршрут экрана карточки приложения. */
    const val APP_DETAILS = "app_details"

    /** Шаблон маршрута карточки приложения с аргументом `{appId}`. */
    const val APP_DETAILS_ROUTE = "$APP_DETAILS/{$ARG_APP_ID}"

    /**
     * Строит конкретный маршрут карточки приложения.
     *
     * @param appId идентификатор приложения.
     * @return маршрут вида `app_details/<appId>`.
     */
    fun appDetails(appId: Int) = "$APP_DETAILS/$appId"
}