package com.example.vk_education_application.feature.applist.domain.model

data class App(
    val id: Int,
    val name: String,
    val description: String,
    val category: Category,
    val iconUrl: String,
)

enum class Category {
    APP, GAME
}