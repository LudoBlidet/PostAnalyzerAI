package com.blidaoui.postanalyzer.presentation.video

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material3.Card
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.blidaoui.postanalyzer.domain.model.Platform
import com.blidaoui.postanalyzer.presentation.common.PlatformDropdown
import com.blidaoui.postanalyzer.presentation.viewmodel.AnalysisViewModel

@Composable
fun VideoAnalysisScreen(
    navController: NavController,
    viewModel: AnalysisViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    var selectedPlatform by remember { mutableStateOf(Platform.TIKTOK) }
    var pickedUri by remember { mutableStateOf<Uri?>(null) }
    var description by remember { mutableStateOf("") }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        pickedUri = uri
    }

    if (uiState.result != null) {
        navController.navigate("result") {
            popUpTo("video") { inclusive = true }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            "تحليل الفيديو",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "اختر فيديو واكتب وصف المحتوى",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.VideoOnly))
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("📁 اختيار فيديو من المعرض")
        }

        if (pickedUri != null) {
            Spacer(modifier = Modifier.height(12.dp))
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "✓ تم اختيار فيديو بنجاح",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp),
                label = { Text("اكتب وصف الفيديو أو المحتوى") },
                minLines = 4,
                maxLines = 6
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "اختر منصة التواصل",
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(8.dp))
        PlatformDropdown(selectedPlatform) { selectedPlatform = it }

        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                pickedUri?.let { uri ->
                    val analyzeText = if (description.isNotBlank()) description else uri.toString()
                    viewModel.analyzeVideo(selectedPlatform, analyzeText)
                }
            },
            enabled = !uiState.isLoading && pickedUri != null,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (uiState.isLoading) {
                CircularProgressIndicator(modifier = Modifier.size(18.dp), strokeWidth = 2.dp)
                Spacer(modifier = Modifier.width(8.dp))
                Text("جارٍ التحليل...")
            } else {
                Text("تحليل الفيديو")
            }
        }

        if (!uiState.error.isNullOrBlank()) {
            Spacer(modifier = Modifier.height(12.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.small
            ) {
                Text(
                    "⚠️ ${uiState.error}",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(12.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}
