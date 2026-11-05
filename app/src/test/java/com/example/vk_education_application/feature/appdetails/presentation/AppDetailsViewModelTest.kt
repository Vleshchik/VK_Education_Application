package com.example.vk_education_application.feature.appdetails.presentation

import androidx.lifecycle.SavedStateHandle
import com.example.vk_education_application.core.testing.FakeAppDetailsRepository
import com.example.vk_education_application.core.testing.MainDispatcherRule
import com.example.vk_education_application.core.testing.testApp
import com.example.vk_education_application.feature.appdetails.domain.GetAppDetailsUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class AppDetailsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private fun createViewModel(
        appId: Int,
        repository: FakeAppDetailsRepository,
    ) = AppDetailsViewModel(
        savedStateHandle = SavedStateHandle(mapOf("appId" to appId)),
        getAppDetails = GetAppDetailsUseCase(repository),
    )

    @Test
    fun `initial load exposes content with app and showcase`() = runTest {
        val app = testApp(1)
        val showcase = listOf(testApp(2), testApp(3))

        val viewModel = createViewModel(
            appId = 1,
            repository = FakeAppDetailsRepository(
                apps = listOf(app),
                showcaseApps = showcase,
            ),
        )

        assertEquals(
            AppDetailsUiState.Content(app = app, showcaseApps = showcase),
            viewModel.uiState.value,
        )
    }

    @Test
    fun `missing app exposes error state`() = runTest {
        val viewModel = createViewModel(
            appId = 99,
            repository = FakeAppDetailsRepository(apps = listOf(testApp(1))),
        )

        val state = viewModel.uiState.value

        assertTrue(state is AppDetailsUiState.Error)
        assertEquals("Приложение не найдено", (state as AppDetailsUiState.Error).message)
    }

    @Test
    fun `repository failure exposes error message`() = runTest {
        val viewModel = createViewModel(
            appId = 1,
            repository = FakeAppDetailsRepository(error = IllegalStateException("Ошибка сети")),
        )

        val state = viewModel.uiState.value as AppDetailsUiState.Error

        assertEquals("Ошибка сети", state.message)
    }

    @Test
    fun `repository failure without message uses fallback text`() = runTest {
        val viewModel = createViewModel(
            appId = 1,
            repository = FakeAppDetailsRepository(error = RuntimeException()),
        )

        val state = viewModel.uiState.value as AppDetailsUiState.Error

        assertEquals("Не удалось загрузить данные", state.message)
    }
}
