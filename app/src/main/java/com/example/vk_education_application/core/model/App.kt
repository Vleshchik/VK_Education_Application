package com.example.vk_education_application.core.model

data class App(
    val id: Int,
    val name: String,
    val description: String,
    val category: Category,
    val iconUrl: String,
)
