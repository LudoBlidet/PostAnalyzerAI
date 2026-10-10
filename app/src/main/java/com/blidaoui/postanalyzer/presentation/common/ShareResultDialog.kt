package com.blidaoui.postanalyzer.presentation.common

import android.content.Intent
import android.widget.Toast
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.blidaoui.postanalyzer.domain.model.AnalysisResult

@Composable
fun ShareResultDialog(
    result: AnalysisResult,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("مشاركة النتيجة") },
        text = { Text("اختر تطبيق لمشاركة النتيجة") },
        confirmButton = {
            Button(onClick = {
                val shareText = buildShareText(result)
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, shareText)
                    putExtra(Intent.EXTRA_SUBJECT, "تحليل المنشور - BliDaoui")
                }
                context.startActivity(Intent.createChooser(intent, "مشاركة عبر"))
                onDismiss()
            }) {
                Text("مشاركة")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("إلغاء")
            }
        }
    )
}

@Composable
fun SaveResultDialog(
    onDismiss: () -> Unit,
    onSave: () -> Unit
) {
    val context = LocalContext.current

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("حفظ النتيجة") },
        text = { Text("تم حفظ النتيجة محليًا في سجل التحليلات") },
        confirmButton = {
            Button(onClick = {
                onSave()
                Toast.makeText(context, "تم الحفظ بنجاح", Toast.LENGTH_SHORT).show()
                onDismiss()
            }) {
                Text("موافق")
            }
        }
    )
}

private fun buildShareText(result: AnalysisResult): String {
    return """
📊 نتيجة تحليل المنشور - بواسطة BliDaoui

التقييم العام: ${result.overallScore}/100
المنصة: ${result.platformFocus}
التوقع: ${result.predictedPerformance}

✓ نقاط القوة:
${result.strengths.joinToString("\n") { "• $it" }}

✗ نقاط الضعف:
${result.weaknesses.joinToString("\n") { "• $it" }}

💡 اقتراحات التحسين:
${result.improvementSuggestions.joinToString("\n") { "• $it" }}

📝 التفسير: ${result.reasoning}
    """.trimIndent()
}
