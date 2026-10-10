package com.blidaoui.postanalyzer.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AnalysisResult(
    @Json(name = "overallScore")
    val overallScore: Int,
    @Json(name = "platformFocus")
    val platformFocus: String,
    @Json(name = "strengths")
    val strengths: List<String>,
    @Json(name = "weaknesses")
    val weaknesses: List<String>,
    @Json(name = "improvementSuggestions")
    val improvementSuggestions: List<String>,
    @Json(name = "predictedPerformance")
    val predictedPerformance: String,
    @Json(name = "reasoning")
    val reasoning: String,
    val timestamp: Long = System.currentTimeMillis()
)
