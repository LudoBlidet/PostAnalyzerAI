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
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "تطوير: BliDaoui",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = { navController.navigate("text") }) {
            Text("تحليل نص")
        }
        Spacer(modifier = Modifier.height(12.dp))

        Button(onClick = { navController.navigate("image") }) {
            Text("تحليل صورة")
        }
        Spacer(modifier = Modifier.height(12.dp))

        Button(onClick = { navController.navigate("video") }) {
            Text("تحليل فيديو")
        }
        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = { navController.navigate("history") }) {
            Text("السجل")
        }
    }
}
