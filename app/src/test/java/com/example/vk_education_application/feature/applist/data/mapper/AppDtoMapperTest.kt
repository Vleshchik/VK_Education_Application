package com.example.vk_education_application.feature.applist.data.mapper

import com.example.vk_education_application.core.model.Category
import com.example.vk_education_application.core.testing.testDto
import org.junit.Assert.assertEquals
import org.junit.Test

class AppDtoMapperTest {

    private val mapper = AppDtoMapper()

    @Test
    fun `map copies all fields from dto`() {
        val dto = testDto(id = 42, category = "GAME")

        val app = mapper.map(dto)

        assertEquals(dto.id, app.id)
        assertEquals(dto.name, app.name)
        assertEquals(dto.description, app.description)
        assertEquals(dto.iconUrl, app.iconUrl)
    }

    @Test
    fun `map parses GAME category ignoring case`() {
        assertEquals(Category.GAME, mapper.map(testDto(1, category = "GAME")).category)
        assertEquals(Category.GAME, mapper.map(testDto(1, category = "game")).category)
        assertEquals(Category.GAME, mapper.map(testDto(1, category = "Game")).category)
    }

    @Test
    fun `map parses APP category ignoring case`() {
        assertEquals(Category.APP, mapper.map(testDto(1, category = "APP")).category)
        assertEquals(Category.APP, mapper.map(testDto(1, category = "app")).category)
    }

    @Test
    fun `map falls back to APP for unknown category`() {
        assertEquals(Category.APP, mapper.map(testDto(1, category = "UPDATE")).category)
        assertEquals(Category.APP, mapper.map(testDto(1, category = "")).category)
    }

    @Test
    fun `map list keeps order and size`() {
        val dtos = listOf(
            testDto(id = 3, category = "GAME"),
            testDto(id = 1, category = "APP"),
            testDto(id = 2, category = "GAME"),
        )

        val apps = mapper.map(dtos)

        assertEquals(listOf(3, 1, 2), apps.map { it.id })
        assertEquals(listOf(Category.GAME, Category.APP, Category.GAME), apps.map { it.category })
    }

    @Test
    fun `map list of empty input returns empty list`() {
        assertEquals(emptyList<Any>(), mapper.map(emptyList()))
    }
}
