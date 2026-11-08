package com.example.vk_education_application.feature.appdetails.domain

import com.example.vk_education_application.core.model.App
import com.example.vk_education_application.feature.appdetails.data.AppDetailsRepository
import javax.inject.Inject

/**
 * Результат загрузки карточки приложения.
 *
 * @property app запрошенное приложение.
 * @property showcaseApps список приложений для блока «Витрина».
 */
data class AppDetailsData(
    val app: App,
    val showcaseApps: List<App>,
)

/**
 * Use case загрузки данных карточки приложения.
 *
 * Возвращает `null`, если приложение с указанным `appId` не найдено.
 *
 * @param repository репозиторий деталей приложения.
 */
class GetAppDetailsUseCase @Inject constructor(
    private val repository: AppDetailsRepository,
) {
    /**
     * Загружает приложение и список витрины.
     *
     * @param appId идентификатор открываемого приложения.
     * @return [AppDetailsData] либо `null`, если приложение не найдено.
     */
    suspend operator fun invoke(appId: String): AppDetailsData? {
        val app = repository.getAppDetails(appId) ?: return null
        val showcase = repository.getShowcaseApps()
        return AppDetailsData(app = app, showcaseApps = showcase)
    }
}