package com.food.foodmate.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.food.foodmate.ui.theme.FoodMateTheme
import com.food.foodmate.viewModel.AuthViewModel
import com.food.foodmate.viewModel.CartViewModel
import com.food.foodmate.views.customViews.MainNavigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            FoodMateTheme {
                val navController = rememberNavController()
                val viewModel: AuthViewModel = hiltViewModel()
                val orderViewModel: CartViewModel = hiltViewModel()

                MainNavigation(viewModel, orderViewModel, navController)
            }
        }
    }
}


/*

            // To make the status bar icons color light
//             statusBarStyle = SystemBarStyle.dark(Color.Transparent.toArgb())
            // If you want to make the status bar icons color dark than you can use it
//             statusBarStyle = SystemBarStyle.light(Color.Transparent.toArgb(), Color.White.toArgb())

 */