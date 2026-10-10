package com.blidaoui.postanalyzer.domain.usecase

import com.blidaoui.postanalyzer.data.repository.AnalysisRepository
import com.blidaoui.postanalyzer.domain.model.AnalysisResult
import com.blidaoui.postanalyzer.domain.model.Platform
import javax.inject.Inject

class AnalyzeImageUseCase @Inject constructor(
    private val repository: AnalysisRepository
) {
    suspend operator fun invoke(platform: Platform, imageDescription: String): AnalysisResult {
        return repository.analyzeImage(platform, imageDescription)
    }
}
