package com.example.movierater

import androidx.navigation.navArgument
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.example.movierater.ui.MainScreen
import com.example.movierater.ui.MovieDetailScreen
import com.example.movierater.ui.theme.MovieRaterTheme
import com.example.movierater.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieRaterTheme {
                val navController = rememberNavController()
                val viewModel: MainViewModel = viewModel()

                NavHost(navController = navController, startDestination = Screen.Main.route) {
                    composable(Screen.Main.route) {
                        MainScreen(viewModel = viewModel, navController = navController)
                    }
                    composable(
                        route = Screen.Detail.route,
                        arguments = listOf(navArgument("imdbID") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val imdbID = backStackEntry.arguments?.getString("imdbID") ?: ""
                        MovieDetailScreen(imdbID = imdbID, viewModel = viewModel)
                    }
                }
            }
        }
    }
}
