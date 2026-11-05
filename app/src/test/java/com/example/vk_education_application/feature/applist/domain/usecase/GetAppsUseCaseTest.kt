package com.example.vk_education_application.feature.applist.domain.usecase

import com.example.vk_education_application.core.testing.FakeAppListRepository
import com.example.vk_education_application.core.testing.testApp
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class GetAppsUseCaseTest {

    @Test
    fun `invoke returns apps from repository`() = runTest {
        val apps = listOf(testApp(1), testApp(2))
        val useCase = GetAppsUseCase(FakeAppListRepository(apps = apps))

        assertEquals(apps, useCase())
    }

    @Test
    fun `invoke returns empty list when repository is empty`() = runTest {
        val useCase = GetAppsUseCase(FakeAppListRepository())

        assertTrue(useCase().isEmpty())
    }

    @Test
    fun `invoke propagates repository exception`() = runTest {
        val useCase = GetAppsUseCase(
            FakeAppListRepository(error = IllegalStateException("network error")),
        )

        val thrown = runCatching { useCase() }.exceptionOrNull()

        assertTrue(thrown is IllegalStateException)
        assertEquals("network error", thrown?.message)
    }
}
