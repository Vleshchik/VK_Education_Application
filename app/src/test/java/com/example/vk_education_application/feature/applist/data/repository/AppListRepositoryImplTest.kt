package com.example.vk_education_application.feature.applist.data.repository

import com.example.vk_education_application.core.model.Category
import com.example.vk_education_application.feature.applist.data.mapper.AppDtoMapper
import com.example.vk_education_application.feature.applist.data.source.AppLocalDataSource
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class AppListRepositoryImplTest {

    private val dataSource = AppLocalDataSource(
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build(),
    )

    private val repository = AppListRepositoryImpl(
        dataSource = dataSource,
        mapper = AppDtoMapper(),
    )

    @Test
    fun `getApps maps every dto to domain model`() = runTest {
        val dtos = dataSource.getApps()

        val apps = repository.getApps()

        assertEquals(dtos.size, apps.size)
        assertEquals(dtos.map { it.id }, apps.map { it.id })
        assertEquals(dtos.map { it.name }, apps.map { it.name })
    }

    @Test
    fun `getApps converts string categories to enum`() = runTest {
        val apps = repository.getApps()

        assertEquals(Category.GAME, apps.first { it.id == 1 }.category)
        assertEquals(Category.APP, apps.first { it.id == 2 }.category)
        assertTrue(apps.all { it.category == Category.APP || it.category == Category.GAME })
    }

    @Test
    fun `getApps keeps icon urls`() = runTest {
        assertTrue(repository.getApps().all { it.iconUrl.startsWith("https://") })
    }
}
