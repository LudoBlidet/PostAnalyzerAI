package com.blidaoui.postanalyzer.domain.model

data class AnalysisResult(
    val overallScore: Int,
    val platformFocus: String,
    val strengths: List<String>,
    val weaknesses: List<String>,
    val improvementSuggestions: List<String>,
    val predictedPerformance: String,
    val reasoning: String,
    val timestamp: Long = System.currentTimeMillis()
)
