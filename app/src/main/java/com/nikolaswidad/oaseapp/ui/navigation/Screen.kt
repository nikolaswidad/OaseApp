package com.nikolaswidad.oaseapp.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Detail : Screen("detail")
    object Welcome : Screen("welcome")
    object DetailNews : Screen("home/{newsId}") {
        fun createRoute(newsId: Long) = "home/$newsId"
    }
}
