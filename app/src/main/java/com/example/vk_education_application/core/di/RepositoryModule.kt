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

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindAppRepository(
        impl: AppListRepositoryImpl,
    ): AppListRepository
}

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideAppApiService(retrofit: Retrofit): AppApiService =
        retrofit.create(AppApiService::class.java)
}

@Module
@InstallIn(SingletonComponent::class)
abstract class AppDetailsModule {

    @Binds
    @Singleton
    abstract fun bindAppDetailsRepository(
        impl: AppDetailsRepositoryImpl,
    ): AppDetailsRepository
}