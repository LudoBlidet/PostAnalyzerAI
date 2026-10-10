package com.blidaoui.postanalyzer.presentation.text

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.blidaoui.postanalyzer.domain.model.Platform
import com.blidaoui.postanalyzer.presentation.common.PlatformDropdown
import com.blidaoui.postanalyzer.presentation.viewmodel.AnalysisViewModel

@Composable
fun TextAnalysisScreen(
    navController: NavController,
    viewModel: AnalysisViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    var text by remember { mutableStateOf("") }
    var selectedPlatform by remember { mutableStateOf(Platform.TIKTOK) }

    if (uiState.result != null) {
        navController.navigate("result") {
            popUpTo("text") { inclusive = true }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text("تحليل نص", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp),
            label = { Text("اكتب المنشور") }
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text("اختر المنصة")
        PlatformDropdown(selectedPlatform) { selectedPlatform = it }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { viewModel.analyzeText(selectedPlatform, text) },
            enabled = !uiState.isLoading && text.isNotBlank()
        ) {
            if (uiState.isLoading) {
                CircularProgressIndicator(modifier = Modifier.size(18.dp), strokeWidth = 2.dp)
                Spacer(modifier = Modifier.width(8.dp))
                Text("جارٍ التحليل...")
            } else {
                Text("تحليل")
            }
        }

        if (!uiState.error.isNullOrBlank()) {
            Spacer(modifier = Modifier.height(12.dp))
            Text(uiState.error, color = MaterialTheme.colorScheme.error)
        }
    }
}
