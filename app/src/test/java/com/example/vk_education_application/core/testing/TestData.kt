package com.example.vk_education_application.core.testing

import com.example.vk_education_application.core.model.App
import com.example.vk_education_application.core.model.Category
import com.example.vk_education_application.feature.appdetails.data.AppDetailsRepository
import com.example.vk_education_application.feature.applist.data.dto.AppDto
import com.example.vk_education_application.feature.applist.domain.repository.AppListRepository

/**
 * Создаёт доменную модель приложения для тестов.
 *
 * @param id идентификатор приложения.
 * @param name название.
 * @param category категория.
 */
fun testApp(
    id: Int,
    name: String = "App $id",
    category: Category = Category.APP,
) = App(
    id = id,
    name = name,
    description = "Description $id",
    category = category,
    iconUrl = "https://example.com/icons/$id.png",
)

/**
 * Создаёт DTO приложения для тестов маппера.
 *
 * @param id идентификатор приложения.
 * @param category строковая категория, как она приходит из JSON.
 */
fun testDto(
    id: Int,
    category: String = "APP",
) = AppDto(
    id = id,
    name = "App $id",
    description = "Description $id",
    category = category,
    iconUrl = "https://example.com/icons/$id.png",
)

/**
 * Фейковый [AppListRepository] для тестов use case и ViewModel.
 *
 * Позволяет задать возвращаемый список либо ошибку, которую нужно бросить.
 *
 * @param apps приложения, которые вернёт [getApps].
 * @param error ошибка, выбрасываемая при загрузке; `null` — без ошибки.
 */
class FakeAppListRepository(
    var apps: List<App> = emptyList(),
    var error: Throwable? = null,
) : AppListRepository {

    override suspend fun getApps(): List<App> {
        error?.let { throw it }
        return apps
    }
}

/**
 * Фейковый [AppDetailsRepository] для тестов use case и ViewModel.
 *
 * @param apps каталог приложений для поиска по `id`.
 * @param showcaseApps приложения витрины.
 * @param error ошибка, выбрасываемая при загрузке; `null` — без ошибки.
 */
class FakeAppDetailsRepository(
    var apps: List<App> = emptyList(),
    var showcaseApps: List<App> = emptyList(),
    var error: Throwable? = null,
) : AppDetailsRepository {

    override suspend fun getAppDetails(appId: Int): App? {
        error?.let { throw it }
        return apps.firstOrNull { it.id == appId }
    }

    override suspend fun getShowcaseApps(): List<App> {
        error?.let { throw it }
        return showcaseApps
    }
}
