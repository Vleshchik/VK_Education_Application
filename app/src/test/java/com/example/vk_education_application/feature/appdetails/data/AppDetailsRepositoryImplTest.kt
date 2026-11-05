package com.example.vk_education_application.feature.appdetails.data

import com.example.vk_education_application.core.model.Category
import com.example.vk_education_application.feature.applist.data.mapper.AppDtoMapper
import com.example.vk_education_application.feature.applist.data.source.AppLocalDataSource
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class AppDetailsRepositoryImplTest {

    private val dataSource = AppLocalDataSource(
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build(),
    )

    private val repository = AppDetailsRepositoryImpl(
        dataSource = dataSource,
        mapper = AppDtoMapper(),
    )

    @Test
    fun `getAppDetails returns matching app`() = runTest {
        val app = repository.getAppDetails(2)

        assertEquals(2, app?.id)
        assertEquals("VK Мессенджер", app?.name)
        assertEquals(Category.APP, app?.category)
    }

    @Test
    fun `getAppDetails returns null for unknown id`() = runTest {
        assertNull(repository.getAppDetails(999))
    }

    @Test
    fun `getShowcaseApps returns first nine catalog items`() = runTest {
        val showcase = repository.getShowcaseApps()

        assertEquals(9, showcase.size)
        assertEquals((1..9).toList(), showcase.map { it.id })
    }

    @Test
    fun `getShowcaseApps maps categories to enum`() = runTest {
        val showcase = repository.getShowcaseApps()

        assertTrue(showcase.all { it.category == Category.APP || it.category == Category.GAME })
        assertEquals(Category.GAME, showcase.first { it.id == 1 }.category)
    }
}
