package com.example.vk_education_application.feature.applist.presentation

import com.example.vk_education_application.core.testing.FakeAppListRepository
import com.example.vk_education_application.core.testing.MainDispatcherRule
import com.example.vk_education_application.core.testing.testApp
import com.example.vk_education_application.feature.applist.domain.usecase.GetAppsUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class AppListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private fun createViewModel(repository: FakeAppListRepository) =
        AppListViewModel(GetAppsUseCase(repository))

    @Test
    fun `initial load exposes content state`() = runTest {
        val apps = listOf(testApp(1), testApp(2))

        val viewModel = createViewModel(FakeAppListRepository(apps = apps))

        assertEquals(AppListUiState.Content(apps), viewModel.uiState.value)
    }

    @Test
    fun `load failure exposes error state with message`() = runTest {
        val viewModel = createViewModel(
            FakeAppListRepository(error = IllegalStateException("Сеть недоступна")),
        )

        val state = viewModel.uiState.value

        assertTrue(state is AppListUiState.Error)
        assertEquals("Сеть недоступна", (state as AppListUiState.Error).message)
    }

    @Test
    fun `load failure without message uses fallback text`() = runTest {
        val viewModel = createViewModel(FakeAppListRepository(error = RuntimeException()))

        val state = viewModel.uiState.value as AppListUiState.Error

        assertEquals("Не удалось загрузить приложения", state.message)
    }

    @Test
    fun `empty catalog is exposed as content with empty list`() = runTest {
        val viewModel = createViewModel(FakeAppListRepository())

        assertEquals(AppListUiState.Content(emptyList()), viewModel.uiState.value)
    }

    @Test
    fun `toolbar click emits snackbar event`() = runTest {
        val viewModel = createViewModel(FakeAppListRepository())

        viewModel.onToolbarClick()

        assertEquals(
            AppListEvent.ShowSnackbar("Show Snackbar"),
            viewModel.events.first(),
        )
    }
}
