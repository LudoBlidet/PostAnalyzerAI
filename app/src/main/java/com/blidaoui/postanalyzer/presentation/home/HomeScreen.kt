package com.blidaoui.postanalyzer.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "محلل المنشورات الذكي",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "تطوير: BliDaoui",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "حلل منشوراتك على TikTok و Instagram و Facebook",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(48.dp))

        Button(
            onClick = { navController.navigate("text") },
            modifier = Modifier.fillMaxSize(0.8f).height(48.dp)
        ) {
            Text("📝 تحليل نص", fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = { navController.navigate("image") },
            modifier = Modifier.fillMaxSize(0.8f).height(48.dp)
        ) {
            Text("🖼️ تحليل صورة", fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = { navController.navigate("video") },
            modifier = Modifier.fillMaxSize(0.8f).height(48.dp)
        ) {
            Text("🎬 تحليل فيديو", fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { navController.navigate("history") },
            modifier = Modifier.fillMaxSize(0.8f).height(48.dp)
        ) {
            Text("📊 السجل", fontSize = 16.sp)
        }
    }
}
