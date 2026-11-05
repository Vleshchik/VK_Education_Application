package com.example.vk_education_application.feature.applist.data.source

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class AppLocalDataSourceTest {

    private val dataSource = AppLocalDataSource(
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build(),
    )

    @Test
    fun `getApps returns all catalog items`() {
        val apps = dataSource.getApps()

        assertEquals(9, apps.size)
        assertEquals(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9), apps.map { it.id })
    }

    @Test
    fun `getApps parses first item fields`() {
        val first = dataSource.getApps().first()

        assertEquals(1, first.id)
        assertTrue(first.name.contains("Castle Clash"))
        assertEquals("GAME", first.category)
        assertTrue(first.iconUrl.startsWith("https://"))
    }

    @Test
    fun `getApps contains both categories`() {
        val categories = dataSource.getApps().map { it.category }.toSet()

        assertEquals(setOf("APP", "GAME"), categories)
    }

    @Test
    fun `getApps returns stable result on repeated calls`() {
        assertEquals(dataSource.getApps(), dataSource.getApps())
    }
}
