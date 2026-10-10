package com.blidaoui.postanalyzer.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "analysis_results")
data class AnalysisEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val overallScore: Int,
    val platformFocus: String,
    val strengths: String,
    val weaknesses: String,
    val improvementSuggestions: String,
    val predictedPerformance: String,
    val reasoning: String,
    val timestamp: Long
)
