package com.blidaoui.postanalyzer.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.blidaoui.postanalyzer.presentation.history.HistoryScreen
import com.blidaoui.postanalyzer.presentation.home.HomeScreen
import com.blidaoui.postanalyzer.presentation.image.ImageAnalysisScreen
import com.blidaoui.postanalyzer.presentation.result.ResultScreen
import com.blidaoui.postanalyzer.presentation.text.TextAnalysisScreen
import com.blidaoui.postanalyzer.presentation.video.VideoAnalysisScreen
import com.blidaoui.postanalyzer.presentation.viewmodel.AnalysisViewModel
import com.blidaoui.postanalyzer.presentation.viewmodel.HistoryViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val analysisViewModel: AnalysisViewModel = hiltViewModel()
    val historyViewModel: HistoryViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(navController)
        }
        composable("text") {
            TextAnalysisScreen(navController, analysisViewModel)
        }
        composable("image") {
            ImageAnalysisScreen(navController, analysisViewModel)
        }
        composable("video") {
            VideoAnalysisScreen(navController, analysisViewModel)
        }
        composable("result") {
            ResultScreen(navController, analysisViewModel)
        }
        composable("history") {
            HistoryScreen(navController, analysisViewModel, historyViewModel)
        }
    }
}
