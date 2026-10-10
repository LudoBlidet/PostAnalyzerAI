package com.blidaoui.postanalyzer.presentation.result

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.blidaoui.postanalyzer.presentation.common.SaveResultDialog
import com.blidaoui.postanalyzer.presentation.common.ShareResultDialog
import com.blidaoui.postanalyzer.presentation.viewmodel.AnalysisViewModel

@Composable
fun ResultScreen(
    navController: NavController,
    viewModel: AnalysisViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    val result = uiState.result ?: return
    var showShareDialog by remember { mutableStateOf(false) }
    var showSaveDialog by remember { mutableStateOf(false) }

    if (showShareDialog) {
        val shareText = buildShareText(result)
        ShareResultDialog(text = shareText) { showShareDialog = false }
    }

    if (showSaveDialog) {
        SaveResultDialog(
            onDismiss = { showSaveDialog = false },
            onSave = { showSaveDialog = false }
        )
    }

    val scoreColor = when {
        result.overallScore >= 75 -> Color(0xFF27AE60)
        result.overallScore >= 50 -> Color(0xFFF2994A)
        else -> Color(0xFFE74C3C)
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text(
                "نتيجة التحليل",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
        }

        item {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "التقييم العام",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "${result.overallScore}/100",
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                        color = scoreColor
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    LinearProgressIndicator(
                        progress = { result.overallScore / 100f },
                        modifier = Modifier.fillMaxWidth(),
                        color = scoreColor
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text("التوقع: ${result.predictedPerformance}")
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("المنصة: ${result.platformFocus}")
                }
            }
        }

        item {
            Text(
                "نقاط القوة ✓",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF27AE60)
            )
            result.strengths.forEach { strength ->
                Text("• $strength", modifier = Modifier.padding(start = 8.dp, top = 4.dp))
            }
        }

        item {
            Text(
                "نقاط الضعف ✗",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFFE74C3C)
            )
            result.weaknesses.forEach { weakness ->
                Text("• $weakness", modifier = Modifier.padding(start = 8.dp, top = 4.dp))
            }
        }

        item {
            Text(
                "اقتراحات التحسين 💡",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFFF2994A)
            )
            result.improvementSuggestions.forEachIndexed { index, suggestion ->
                Text(
                    "${index + 1}. $suggestion",
                    modifier = Modifier.padding(start = 8.dp, top = 4.dp)
                )
            }
        }

        item {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(
                        "التفسير المفصل",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(result.reasoning, style = MaterialTheme.typography.bodySmall)
                }
            }
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { showShareDialog = true },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("مشاركة")
                }
                Button(
                    onClick = { showSaveDialog = true },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("حفظ")
                }
            }
        }

        item {
            Button(
                onClick = {
                    viewModel.clearState()
                    navController.navigate("home") {
                        popUpTo("home") { inclusive = false }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("تحليل جديد")
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

private fun buildShareText(result: com.blidaoui.postanalyzer.domain.model.AnalysisResult): String {
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
