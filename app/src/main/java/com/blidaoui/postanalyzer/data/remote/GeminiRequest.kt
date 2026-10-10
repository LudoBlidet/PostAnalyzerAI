package com.blidaoui.postanalyzer.data.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GeminiRequest(
    @Json(name = "contents")
    val contents: List<ContentItem>,
    @Json(name = "generationConfig")
    val generationConfig: GenerationConfig = GenerationConfig()
)

@JsonClass(generateAdapter = true)
data class ContentItem(
    @Json(name = "parts")
    val parts: List<PartItem>
)

@JsonClass(generateAdapter = true)
data class PartItem(
    @Json(name = "text")
    val text: String
)

@JsonClass(generateAdapter = true)
data class GenerationConfig(
    @Json(name = "responseMimeType")
    val responseMimeType: String = "application/json"
)
