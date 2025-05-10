package com.example.movierater

sealed class Screen(val route: String) {
    object Main : Screen("main")
    object Detail : Screen("detail/{imdbID}") {
        fun createRoute(imdbID: String) = "detail/$imdbID"
    }
}