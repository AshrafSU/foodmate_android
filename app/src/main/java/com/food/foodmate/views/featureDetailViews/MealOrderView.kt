package com.food.foodmate.views.featureDetailViews.meal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.food.foodmate.viewModel.AuthViewModel
import com.food.foodmate.views.customViews.pageHeader.PageHeader
import com.food.foodmate.views.home.RestaurantCard

@Composable
fun MealOrderView(viewModel: AuthViewModel, navController: NavHostController
) {
    val allRestaurants by viewModel.restaurantList.collectAsState()
    val restaurants = allRestaurants.filter { (it.allowMealOrder == true) }
    val isLoading by viewModel.loading.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        PageHeader(title = "Meal") { navController.popBackStack() }

        Column(modifier = Modifier.fillMaxWidth().background(Color(0xFFF8F8F8)).padding(horizontal = 20.dp)) {
            Text("All Restaurants", fontSize = 18.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(vertical = 10.dp))

            if (isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else if (restaurants.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No restaurants found.", fontSize = 16.sp, color = Color.Gray)
                }
            } else {
                Column(
                    verticalArrangement = Arrangement.spacedBy(15.dp),
                    modifier = Modifier.verticalScroll(rememberScrollState())
                ) {
                    restaurants.forEach { restaurant ->
                        RestaurantCard(restaurant = restaurant, navController = navController)
                    }
                }
            }
        }
    }
}