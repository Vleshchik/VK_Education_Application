package com.example.vk_education_application.core.di

import com.example.vk_education_application.feature.applist.data.remote.AppApiService
import com.example.vk_education_application.feature.applist.data.repository.AppRepositoryImpl
import com.example.vk_education_application.feature.applist.domain.repository.AppRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindAppRepository(
        impl: AppRepositoryImpl,
    ): AppRepository
}

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideAppApiService(retrofit: Retrofit): AppApiService =
        retrofit.create(AppApiService::class.java)
}