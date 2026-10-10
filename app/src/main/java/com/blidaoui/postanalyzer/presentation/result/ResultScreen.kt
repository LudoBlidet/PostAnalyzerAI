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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.blidaoui.postanalyzer.presentation.viewmodel.AnalysisViewModel

@Composable
fun ResultScreen(
    navController: NavController,
    viewModel: AnalysisViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    val result = uiState.result ?: return

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
            Text("نتيجة التحليل", style = MaterialTheme.typography.headlineMedium)
        }

        item {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("التقييم العام", style = MaterialTheme.typography.titleMedium)
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
                }
            }
        }

        item {
            Text("نقاط القوة", style = MaterialTheme.typography.titleMedium)
            result.strengths.forEach { item ->
                Text("• $item")
            }
        }

        item {
            Text("نقاط الضعف", style = MaterialTheme.typography.titleMedium)
            result.weaknesses.forEach { item ->
                Text("• $item")
            }
        }

        item {
            Text("اقتراحات التحسين", style = MaterialTheme.typography.titleMedium)
            result.improvementSuggestions.forEach { item ->
                Text("• $item")
            }
        }

        item {
            Text("التفسير", style = MaterialTheme.typography.titleMedium)
            Text(result.reasoning)
        }

        item {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(onClick = { /* مشاركة */ }) {
                    Text("مشاركة")
                }
                Button(onClick = { /* حفظ محليًا */ }) {
                    Text("حفظ")
                }
            }
        }

        item {
            Button(onClick = {
                viewModel.clearState()
                navController.navigate("home") {
                    popUpTo("home") { inclusive = false }
                }
            }) {
                Text("تحليل جديد")
            }
        }
    }
}
