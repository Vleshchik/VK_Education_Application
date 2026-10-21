package com.example.vk_education_application.feature.applist.data.mapper

import com.example.vk_education_application.feature.applist.data.remote.dto.AppDto
import com.example.vk_education_application.core.model.App
import com.example.vk_education_application.core.model.Category

fun AppDto.toDomain(): App = App(
    id = id,
    name = name,
    description = description,
    category = when (category.uppercase()) {
        "GAME" -> Category.GAME
        else -> Category.APP
    },
    iconUrl = iconUrl,
)