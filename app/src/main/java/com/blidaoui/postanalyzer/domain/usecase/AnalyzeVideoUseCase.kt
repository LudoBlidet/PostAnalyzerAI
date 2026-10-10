package com.blidaoui.postanalyzer.domain.usecase

import com.blidaoui.postanalyzer.data.repository.AnalysisRepository
import com.blidaoui.postanalyzer.domain.model.AnalysisResult
import com.blidaoui.postanalyzer.domain.model.Platform
import javax.inject.Inject

class AnalyzeVideoUseCase @Inject constructor(
    private val repository: AnalysisRepository
) {
    suspend operator fun invoke(platform: Platform, videoDescription: String): AnalysisResult {
        return repository.analyzeVideo(platform, videoDescription)
    }
}
