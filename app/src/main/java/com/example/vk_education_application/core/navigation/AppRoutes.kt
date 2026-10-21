package com.example.vk_education_application.core.navigation

object AppRoutes {
    const val APP_LIST = "app_list"

    const val ARG_APP_ID = "appId"
    const val APP_DETAILS = "app_details"
    const val APP_DETAILS_ROUTE = "$APP_DETAILS/{$ARG_APP_ID}"

    fun appDetails(appId: Int) = "$APP_DETAILS/$appId"
}