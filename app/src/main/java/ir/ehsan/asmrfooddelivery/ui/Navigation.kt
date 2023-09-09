package ir.ehsan.asmrfooddelivery.ui

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ir.ehsan.asmrfooddelivery.ui.screens.AuthScreen
import ir.ehsan.asmrfooddelivery.ui.screens.FoodScreen
import ir.ehsan.asmrfooddelivery.ui.screens.HomeScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("main", Context.MODE_PRIVATE)
    val userLoggedIn = sharedPreferences.getBoolean("loggedIn",false)
    NavHost(navController = navController, startDestination = if (userLoggedIn) "home" else "auth"){
        composable("auth"){
            AuthScreen(navController = navController)
        }
        composable("home"){
            HomeScreen(navController = navController)
        }
        composable("food"){
            FoodScreen(navController = navController)
        }
    }
}