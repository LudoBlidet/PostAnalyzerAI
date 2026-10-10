package com.blidaoui.postanalyzer.data.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GeminiResponse(
    @Json(name = "candidates")
    val candidates: List<Candidate>? = null
)

@JsonClass(generateAdapter = true)
data class Candidate(
    @Json(name = "content")
    val content: Content? = null
)

@JsonClass(generateAdapter = true)
data class Content(
    @Json(name = "parts")
    val parts: List<Part>? = null
)

@JsonClass(generateAdapter = true)
data class Part(
    @Json(name = "text")
    val text: String? = null
)
