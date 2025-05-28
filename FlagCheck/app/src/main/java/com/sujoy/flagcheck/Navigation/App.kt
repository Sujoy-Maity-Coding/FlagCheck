package com.sujoy.flagcheck.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sujoy.flagcheck.Screens.HomeScreen
import com.sujoy.flagcheck.Screens.QuizScreen
import com.sujoy.flagcheck.Screens.ResultScreen
import com.sujoy.flagcheck.ViewModel.QuizViewModel

@Composable
fun App(viewModel: QuizViewModel = hiltViewModel(), modifier: Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Home) {
        composable<Routes.Home> {
            HomeScreen(onStart = { navController.navigate(Routes.Quiz) })
        }
        composable<Routes.Quiz> {
            QuizScreen(
                viewModel = viewModel,
                onGameFinished = { navController.navigate(Routes.Result) }
            )
        }
        composable<Routes.Result> {
            ResultScreen(viewModel = viewModel, onRestart = {
                navController.popBackStack(Routes.Home, inclusive = false)
            })
        }
    }
}
