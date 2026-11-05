package com.example.vk_education_application.feature.applist.domain.usecase

import com.example.vk_education_application.core.model.App
import com.example.vk_education_application.feature.applist.domain.repository.AppListRepository
import javax.inject.Inject

/**
 * Use case получения списка приложений каталога.
 *
 * Тонкая обёртка над [AppListRepository], изолирующая слой presentation
 * от конкретной реализации источника данных.
 *
 * @param repository репозиторий каталога приложений.
 */
class GetAppsUseCase @Inject constructor(
    private val repository: AppListRepository,
) {
    /**
     * Загружает все приложения каталога.
     *
     * @return список доменных моделей [App].
     */
    suspend operator fun invoke(): List<App> = repository.getApps()
}