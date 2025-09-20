package com.food.foodmate.views.customViews

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.food.foodmate.R
import com.food.foodmate.viewModel.AuthViewModel
import com.food.foodmate.viewModel.CartViewModel
import com.food.foodmate.views.campaignDetails.CampaignDetailsView
import com.food.foodmate.views.cartScreen.CartScreen
import com.food.foodmate.views.featureDetailViews.dineIn.DineInView
import com.food.foodmate.views.featureDetailViews.meal.MealOrderView
import com.food.foodmate.views.home.HomeScreen
import com.food.foodmate.views.login.LoginScreen
import com.food.foodmate.views.mapView.addressList.SaveAddressList
import com.food.foodmate.views.mapView.googleMap.SaveAddressMapScreen
import com.food.foodmate.views.offersScreen.OffersScreen
import com.food.foodmate.views.order.checkout.CheckoutView
import com.food.foodmate.views.order.orderDetailsView.OrderDetailsScreen
import com.food.foodmate.views.order.orderSuccess.OrderSuccessView
import com.food.foodmate.views.orderScreen.OrdersScreen
import com.food.foodmate.views.profile.contactUsView.ContactUsView
import com.food.foodmate.views.profile.editProfileView.EditProfileView
import com.food.foodmate.views.profile.personalInfoView.PersonalInfoView
import com.food.foodmate.views.profile.privacyPolicyView.PrivacyPolicyView
import com.food.foodmate.views.restaurantDetails.RestaurantDetailsScreen
import com.food.foodmate.views.signup.SignUpScreen

@Composable
fun BottomNavigationBar(navController: NavHostController,  cartViewModel: CartViewModel = hiltViewModel()) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val cartItems by cartViewModel.cartItems.collectAsState(emptyList())

    // Calculate how many items are in the cart
    val cartItemCount = cartItems.size

    NavigationBar(
        containerColor = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 3.dp, shape = RoundedCornerShape(12.dp))
            .heightIn(max = 80.dp)
    ) {
        /*
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_home),
                    contentDescription = "Home",
                    modifier = Modifier.size(20.dp)
                        .weight(1f),
                    tint = if (currentDestination?.route == "home") colorResource(R.color.customRed) else Color(0xFF888888)

                )
                   },
            label = { Text("Home") },
            selected = currentDestination?.hierarchy?.any { it.route == "home" } == true,
            onClick = {
                if (currentDestination?.route != "home") {
                    navController.navigate("home") {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }
        )
        */

        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_home),
                    contentDescription = "Home",
                    modifier = Modifier.size(20.dp),
                    tint = if (currentDestination?.route == "home") colorResource(R.color.customRed) else Color(0xFF888888)
                )
            },
            label = { Text("Home") },
            selected = currentDestination?.hierarchy?.any { it.route == "home" } == true,
            onClick = {
                if (currentDestination?.route != "home") {
                    navController.navigate("home") {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = if (currentDestination?.route == "home") Color(0xFFFCD6DB) else Color.Transparent // Light red overlay or transparent
            )
        )

        NavigationBarItem(
            icon = { Icon(painter = painterResource(id = R.drawable.ic_orders), contentDescription = "Orders", modifier = Modifier.size(20.dp).weight(1f),
                tint = if (currentDestination?.route == "orders") colorResource(R.color.customRed) else Color(0xFF888888)) },
            label = { Text("Orders") },
            selected = currentDestination?.hierarchy?.any { it.route == "orders" } == true,
            onClick = {
                if (currentDestination?.route != "orders") {
                    navController.navigate("orders") {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }
            ,
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = if (currentDestination?.route == "orders") Color(0xFFFCD6DB) else Color.Transparent // Light red overlay or transparent
            )
        )

        NavigationBarItem(
            icon = { Icon(painter = painterResource(id = R.drawable.ic_offers), contentDescription = "Offers", modifier = Modifier.size(20.dp).weight(1f),
                tint = if (currentDestination?.route == "offers") colorResource(R.color.customRed) else Color(0xFF888888)) },
            label = { Text("Offers") },
            selected = currentDestination?.hierarchy?.any { it.route == "offers" } == true,
            onClick = {
                if (currentDestination?.route != "offers") {
                    navController.navigate("offers") {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }
            ,
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = if (currentDestination?.route == "offers") Color(0xFFFCD6DB) else Color.Transparent // Light red overlay or transparent
            )
        )

        NavigationBarItem(
            icon = {
                Box {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_cart_tab),
                        contentDescription = "Cart",
                        modifier = Modifier
                            .size(20.dp),
                        tint = if (currentDestination?.route == "cart") colorResource(id = R.color.customRed) else Color(0xFF888888)
                    )
                    if (cartItemCount > 0) {
                        Box(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .offset(x = 2.dp, y = (-4).dp)
                                .size(12.dp)
                                .background(Color.Red, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = cartItemCount.toString(),
                                fontSize = 8.sp,
                                color = Color.White,
                                textAlign = TextAlign.Center, modifier = Modifier.offset(y = (-4).dp)
                            )
                        }
                    }
                }
            },
            label = { Text("Cart") },
            selected = currentDestination?.hierarchy?.any { it.route == "cart" } == true,
            onClick = {
                if (currentDestination?.route != "cart") {
                    navController.navigate("cart") {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = if (currentDestination?.route == "cart") Color(0xFFFCD6DB) else Color.Transparent
            )
        )

    }
}


@Preview(showBackground = true)
@Composable
fun PreviewBottomNavigationBar() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) {
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(it)
        ) {
            composable("home") { Text("Home Screen", modifier = Modifier.fillMaxSize().wrapContentSize(
                Alignment.Center)) }
            composable("orders") { Text("Orders Screen", modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)) }
            composable("offers") { Text("Offers Screen", modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)) }
            composable("cart") { Text("Cart Screen", modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)) }
        }
    }
}

@Composable
fun MainNavigation(viewModel: AuthViewModel, orderViewModel: CartViewModel, navController: NavHostController) {

    val isLoggedIn by viewModel.isLoggedIn.collectAsState(initial = false)

    Scaffold(
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") { HomeScreen(viewModel, navController) }
            composable("orders") { OrdersScreen(orderViewModel, navController) }
            composable("offers") { OffersScreen(navController) }
            composable("cart") { CartScreen(navController) }
            composable("meal") {  MealOrderView(viewModel, navController) }
            composable("dineIn") { DineInView(viewModel, navController) }
            composable("personalInfoView") { PersonalInfoView(viewModel, navController) }
            composable("editProfileView") { EditProfileView(viewModel, navController) }

            composable("contactUsView") { ContactUsView(navController) }
            composable("privacyPolicyView") { PrivacyPolicyView(navController) }
            composable("login") { LoginScreen(navController) }
            composable("signup") { SignUpScreen(navController) }
            composable("orderSuccess") { OrderSuccessView(navController) }
            composable("googleMap") { SaveAddressMapScreen(navController) }
            composable("addressList") { SaveAddressList(navController)  }

            composable("orderDetailsView/{orderId}") { backStackEntry ->
                val orderId = backStackEntry.arguments?.getString("orderId") ?: ""
                OrderDetailsScreen(orderId, navController)
            }


            composable("restaurantDetails/{restaurantId}") { backStackEntry ->
                val restaurantId = backStackEntry.arguments?.getString("restaurantId") ?: ""
                RestaurantDetailsScreen(
                    navController = navController, restaurantId = restaurantId
                )
            }

            composable("campaignDetails/{campaignId}") { backStackEntry ->
                val campaignId = backStackEntry.arguments?.getString("campaignId") ?: ""
                CampaignDetailsView(
                    navController = navController, campaignId = campaignId
                )
            }

            composable(
                route = "checkout?days={days}&mealTime={mealTime}",
                arguments = listOf(
                    navArgument("days") {
                        type = NavType.StringType
                        defaultValue = ""
                    },
                    navArgument("mealTime") {
                        type = NavType.StringType
                        defaultValue = ""
                    }
                )
            ) { backStackEntry ->
                val daysArg = backStackEntry.arguments?.getString("days") ?: ""
                val mealTimeArg = backStackEntry.arguments?.getString("mealTime") ?: ""
                val selectedDays = if (daysArg.isNotEmpty()) daysArg.split(",") else emptyList()

                CheckoutView(
                    navController = navController,
                    selectedDays = selectedDays,
                    selectedMealTime = mealTimeArg
                )
            }


        }
    }
}

