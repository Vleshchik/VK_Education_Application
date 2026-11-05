package com.example.vk_education_application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Точка входа приложения.
 *
 * Аннотация [HiltAndroidApp] запускает генерацию Hilt-компонента уровня приложения
 * и делает возможным внедрение зависимостей в `MainActivity` и ViewModel-и.
 */
@HiltAndroidApp
class VkEducationApplication : Application()