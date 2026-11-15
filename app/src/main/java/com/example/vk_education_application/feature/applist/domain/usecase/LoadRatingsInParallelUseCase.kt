package com.example.vk_education_application.feature.applist.domain.usecase

import com.example.vk_education_application.feature.applist.domain.repository.AppListRepository
import javax.inject.Inject

class LoadRatingsInParallelUseCase @Inject constructor(
    private val repository: AppListRepository,
) {
    suspend operator fun invoke(ids: List<String>): Map<String, Float> {
        return repository.loadRatingsInParallel(ids)
    }
}