package com.blidaoui.postanalyzer.data.repository

import com.blidaoui.postanalyzer.BuildConfig
import com.blidaoui.postanalyzer.data.local.AnalysisDao
import com.blidaoui.postanalyzer.data.local.AnalysisEntity
import com.blidaoui.postanalyzer.data.remote.ContentItem
import com.blidaoui.postanalyzer.data.remote.GeminiApiService
import com.blidaoui.postanalyzer.data.remote.GeminiRequest
import com.blidaoui.postanalyzer.data.remote.PartItem
import com.blidaoui.postanalyzer.domain.model.AnalysisResult
import com.blidaoui.postanalyzer.domain.model.Platform
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AnalysisRepository @Inject constructor(
    private val geminiApiService: GeminiApiService,
    private val analysisDao: AnalysisDao
) {

    private val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    private val resultAdapter: JsonAdapter<AnalysisResult> = moshi.adapter(AnalysisResult::class.java)

    suspend fun analyzeText(platform: Platform, text: String): AnalysisResult {
        return analyze(platform, text)
    }

    suspend fun analyzeImage(platform: Platform, imageDescription: String): AnalysisResult {
        return analyze(platform, imageDescription)
    }

    suspend fun analyzeVideo(platform: Platform, videoDescription: String): AnalysisResult {
        return analyze(platform, videoDescription)
    }

    private suspend fun analyze(platform: Platform, content: String): AnalysisResult {
        val prompt = buildPrompt(platform, content)
        
        try {
            val response = geminiApiService.generateContent(
                apiKey = BuildConfig.GEMINI_API_KEY,
                body = GeminiRequest(
                    contents = listOf(
                        ContentItem(
                            parts = listOf(PartItem(text = prompt))
                        )
                    )
                )
            )

            val rawJson = response.candidates
                ?.firstOrNull()
                ?.content
                ?.parts
                ?.firstOrNull()
                ?.text
                ?: throw IllegalStateException("استجابة Gemini فارغة")

            val cleaned = rawJson
                .trim()
                .removePrefix("```json")
                .removePrefix("```")
                .removeSuffix("```")
                .trim()

            val result = resultAdapter.fromJson(cleaned)
                ?: throw IllegalStateException("فشل في تحليل JSON")

            val entity = AnalysisEntity(
                overallScore = result.overallScore,
                platformFocus = result.platformFocus,
                strengths = result.strengths.joinToString("||"),
                weaknesses = result.weaknesses.joinToString("||"),
                improvementSuggestions = result.improvementSuggestions.joinToString("||"),
                predictedPerformance = result.predictedPerformance,
                reasoning = result.reasoning,
                timestamp = System.currentTimeMillis()
            )

            analysisDao.insert(entity)
            return result
        } catch (e: Exception) {
            throw Exception("خطأ في الاتصال بـ Gemini API: ${e.message}", e)
        }
    }

    private fun buildPrompt(platform: Platform, content: String): String {
        return """
أنت محلل محتوى تسويقي متخصص في تقييم منشورات وسائل التواصل الاجتماعي.
المنصة: ${platform.label}

المعايير الأساسية:
- إذا كانت المنصة TikTok: قوة الخطاف في أول 1-3 ثواني، معدل الإكمال، إعادة المشاهدة، قابلية المشاركة والحفظ.
- إذا كانت المنصة Instagram: معدل الإكمال، مشاهدة الفيديو كاملاً، إعادة المشاهدة، المشاركة عبر الرسائل الخاصة (DM)، جودة الخطاف.
- إذا كانت المنصة Facebook: التفاعل النوعي، وقت المشاهدة الكلي، ارتباط المحتوى باهتمامات الجمهور.

المحتوى المراد تقييمه:
$content

أعد النتيجة بصيغة JSON فقط بدون نص إضافي:
{
  "overallScore": 0-100,
  "platformFocus": "TikTok/Instagram/Facebook",
  "strengths": ["نقطة قوة 1", "نقطة قوة 2", "نقطة قوة 3"],
  "weaknesses": ["نقطة ضعف 1", "نقطة ضعف 2", "نقطة ضعف 3"],
  "improvementSuggestions": ["اقتراح 1", "اقتراح 2", "اقتراح 3", "اقتراح 4", "اقتراح 5"],
  "predictedPerformance": "عالي/متوسط/منخفض",
  "reasoning": "شرح مختصر للتقييم"
}
        """.trimIndent()
    }

    fun getHistory(): Flow<List<AnalysisEntity>> = analysisDao.getAll()

    suspend fun deleteResult(entity: AnalysisEntity) {
        analysisDao.delete(entity)
    }
}
