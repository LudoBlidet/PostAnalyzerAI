package com.blidaoui.postanalyzer.presentation.history

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.blidaoui.postanalyzer.data.local.AnalysisEntity
import com.blidaoui.postanalyzer.domain.model.AnalysisResult
import com.blidaoui.postanalyzer.presentation.viewmodel.AnalysisViewModel
import com.blidaoui.postanalyzer.presentation.viewmodel.HistoryViewModel

@Composable
fun HistoryScreen(
    navController: NavController,
    analysisViewModel: AnalysisViewModel,
    historyViewModel: HistoryViewModel
) {
    val historyState by historyViewModel.historyState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            "السجل",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))

        if (historyState.items.isEmpty()) {
            Text("لا توجد تحليلات سابقة")
        } else {
            LazyColumn {
                items(historyState.items) { item ->
                    HistoryRow(
                        entity = item,
                        onOpen = {
                            analysisViewModel.setResult(item.toDomainResult())
                            navController.navigate("result")
                        },
                        onDelete = { historyViewModel.deleteHistoryItem(item) }
                    )
                }
            }
        }
    }
}

@Composable
private fun HistoryRow(
    entity: AnalysisEntity,
    onOpen: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("التقييم: ${entity.overallScore}/100", fontWeight = FontWeight.SemiBold)
            Text("المنصة: ${entity.platformFocus}")
            Text("التوقع: ${entity.predictedPerformance}")

            Spacer(modifier = Modifier.height(12.dp))
            Row {
                Button(onClick = onOpen, modifier = Modifier.weight(1f)) {
                    Text("فتح")
                }
                Spacer(modifier = Modifier.width(8.dp))
                OutlinedButton(onClick = onDelete, modifier = Modifier.weight(1f)) {
                    Text("حذف")
                }
            }
        }
    }
}

private fun AnalysisEntity.toDomainResult(): AnalysisResult = AnalysisResult(
    overallScore = overallScore,
    platformFocus = platformFocus,
    strengths = strengths.split("||").filter { it.isNotBlank() },
    weaknesses = weaknesses.split("||").filter { it.isNotBlank() },
    improvementSuggestions = improvementSuggestions.split("||").filter { it.isNotBlank() },
    predictedPerformance = predictedPerformance,
    reasoning = reasoning,
    timestamp = timestamp
)
