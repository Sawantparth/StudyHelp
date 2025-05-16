package com.studycompanion.app.ui.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.studycompanion.app.ui.screens.home.HomeScreen
import com.studycompanion.app.ui.screens.analysis.AnalysisScreen
import com.studycompanion.app.ui.screens.chat.ChatScreen
import com.studycompanion.app.ui.screens.progress.ProgressScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun StudyCompanionNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        enterTransition = { fadeIn(animationSpec = tween(300)) },
        exitTransition = { fadeOut(animationSpec = tween(300)) }
    ) {
        composable(
            route = Screen.Home.route,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { 1000 },
                    animationSpec = tween(300)
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -1000 },
                    animationSpec = tween(300)
                )
            }
        ) {
            HomeScreen(navController = navController)
        }

        composable(
            route = Screen.Analysis.route,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { 1000 },
                    animationSpec = tween(300)
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -1000 },
                    animationSpec = tween(300)
                )
            }
        ) {
            AnalysisScreen(navController = navController)
        }

        composable(
            route = Screen.Chat.route,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { 1000 },
                    animationSpec = tween(300)
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -1000 },
                    animationSpec = tween(300)
                )
            }
        ) {
            ChatScreen(navController = navController)
        }

        composable(
            route = Screen.Progress.route,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { 1000 },
                    animationSpec = tween(300)
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -1000 },
                    animationSpec = tween(300)
                )
            }
        ) {
            ProgressScreen(navController = navController)
        }
    }
}

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Analysis : Screen("analysis")
    object Chat : Screen("chat")
    object Progress : Screen("progress")
}