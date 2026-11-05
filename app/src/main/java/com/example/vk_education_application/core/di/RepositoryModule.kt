package com.example.vk_education_application.core.di

import com.example.vk_education_application.feature.appdetails.data.AppDetailsRepository
import com.example.vk_education_application.feature.appdetails.data.AppDetailsRepositoryImpl
import com.example.vk_education_application.feature.applist.data.remote.AppApiService
import com.example.vk_education_application.feature.applist.data.repository.AppListRepositoryImpl
import com.example.vk_education_application.feature.applist.domain.repository.AppListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

/**
 * Hilt-модуль привязки реализаций репозиториев к их интерфейсам.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    /** Привязывает [AppListRepositoryImpl] к [AppListRepository] как singleton. */
    @Binds
    @Singleton
    abstract fun bindAppRepository(
        impl: AppListRepositoryImpl,
    ): AppListRepository
}

/**
 * Hilt-модуль сетевых зависимостей.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /** Создаёт реализацию [AppApiService] на основе переданного Retrofit. */
    @Provides
    @Singleton
    fun provideAppApiService(retrofit: Retrofit): AppApiService =
        retrofit.create(AppApiService::class.java)
}

/**
 * Hilt-модуль привязки репозитория карточки приложения.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class AppDetailsModule {

    /** Привязывает [AppDetailsRepositoryImpl] к [AppDetailsRepository] как singleton. */
    @Binds
    @Singleton
    abstract fun bindAppDetailsRepository(
        impl: AppDetailsRepositoryImpl,
    ): AppDetailsRepository
}

/**
 * Hilt-модуль настройки Moshi.
 */
@Module
@InstallIn(SingletonComponent::class)
object MoshiModule {

    /** Создаёт singleton Moshi с поддержкой Kotlin data-классов. */
    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())   // ← обязателен для Kotlin data class
        .build()
}