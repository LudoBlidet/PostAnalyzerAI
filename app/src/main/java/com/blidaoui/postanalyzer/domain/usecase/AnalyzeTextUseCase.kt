package com.blidaoui.postanalyzer.domain.usecase

import com.blidaoui.postanalyzer.data.repository.AnalysisRepository
import com.blidaoui.postanalyzer.domain.model.AnalysisResult
import com.blidaoui.postanalyzer.domain.model.Platform
import javax.inject.Inject

class AnalyzeTextUseCase @Inject constructor(
    private val repository: AnalysisRepository
) {
    suspend operator fun invoke(platform: Platform, text: String): AnalysisResult {
        return repository.analyzeText(platform, text)
    }
}
