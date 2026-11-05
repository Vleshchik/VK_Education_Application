package com.example.vk_education_application.feature.appdetails.domain

import com.example.vk_education_application.core.testing.FakeAppDetailsRepository
import com.example.vk_education_application.core.testing.testApp
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class GetAppDetailsUseCaseTest {

    @Test
    fun `invoke returns app with showcase apps`() = runTest {
        val app = testApp(1)
        val showcase = listOf(testApp(2), testApp(3))
        val useCase = GetAppDetailsUseCase(
            FakeAppDetailsRepository(apps = listOf(app), showcaseApps = showcase),
        )

        val result = useCase(1)

        assertEquals(AppDetailsData(app = app, showcaseApps = showcase), result)
    }

    @Test
    fun `invoke returns null when app is missing`() = runTest {
        val useCase = GetAppDetailsUseCase(FakeAppDetailsRepository())

        assertNull(useCase(42))
    }

    @Test
    fun `invoke propagates repository exception`() = runTest {
        val useCase = GetAppDetailsUseCase(
            FakeAppDetailsRepository(error = IllegalStateException("boom")),
        )

        val thrown = runCatching { useCase(1) }.exceptionOrNull()

        assertTrue(thrown is IllegalStateException)
        assertEquals("boom", thrown?.message)
    }
}
