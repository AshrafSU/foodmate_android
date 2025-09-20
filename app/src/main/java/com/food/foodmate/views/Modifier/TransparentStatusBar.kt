package com.food.foodmate.views.Modifier

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun TransparentStatusBar() {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,  // Make status bar transparent
            darkIcons = false  // Set to `true` if using a light-colored image
        )
    }
}
