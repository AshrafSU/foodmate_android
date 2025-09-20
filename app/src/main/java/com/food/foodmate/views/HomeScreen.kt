package com.food.foodmate.views.home

import android.annotation.SuppressLint
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.food.foodmate.R
import com.food.foodmate.dataModel.campaign.Campaign
import com.food.foodmate.dataModel.shop.Restaurant
import com.food.foodmate.ui.theme.FoodMateTheme
import com.food.foodmate.utility.Helper
import com.food.foodmate.viewModel.AuthViewModel
import com.food.foodmate.views.customViews.BottomNavigationBar
import com.food.foodmate.views.customViews.CustomSearchBar
import com.food.foodmate.views.customViews.HomeScreenHeader
import com.food.foodmate.views.customViews.drawerContent.DrawerContent
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: AuthViewModel, navController: NavHostController) {
//    val restaurantList = getRestaurantList()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val userName by viewModel.userName.collectAsState(initial = "Guest")
    var searchText by remember { mutableStateOf("") }
    val restaurants by viewModel.restaurantList.collectAsState()
    val campaigns by viewModel.campaignList.collectAsState()
    val isLoading by viewModel.loading.collectAsState()

    val systemUiController = rememberSystemUiController()

    LaunchedEffect(Unit) {
        viewModel.getCampaignList()
        viewModel.fetchRestaurants()
    }
    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color(0xFFFFFFFF),
            darkIcons = true
        )
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(viewModel = viewModel,navController = navController) {
                scope.launch { drawerState.close() }
            }
        }
    ) {
        Scaffold(
            topBar = {
                HomeScreenHeader(navController = navController,onMenuClick = {
                    scope.launch { drawerState.open() }
                })
            },
            bottomBar = {
                BottomNavigationBar(navController)
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                        .fillMaxSize()
                        .background(Color(0xFFF8F8F8))
                        .padding(horizontal = 16.dp, vertical = 5.dp)
                        .padding(bottom = 10.dp)
                ) {
                    GreetingSection(userName = userName)
                    Spacer(modifier = Modifier.height(8.dp))

                    CustomSearchBar(placeholder = "Search dishes, restaurants",
                        value = searchText,
                        onValueChange = { searchText = it })
                    Spacer(modifier = Modifier.height(15.dp))
                    if (searchText.isEmpty()) {
                        if (campaigns.count() > 0) {
                            ImageCarousel(
                                navController = navController,
                                campaigns = campaigns,
                                isLoading = isLoading
                            )
                            Spacer(modifier = Modifier.height(0.dp))
                        }

                        MealOptionsSection(navController = navController)
                        Spacer(modifier = Modifier.height(0.dp))

                        CuisinesSection()
                        Spacer(modifier = Modifier.height(5.dp))
                    }

                    val filteredRestaurants = if (searchText.isNotEmpty()) {
                        restaurants.filter { it.name.contains(searchText, ignoreCase = true) }
                    } else {
                        restaurants
                    }
                    NearRestaurantsSection(restaurants = filteredRestaurants, isLoading = isLoading, navController = navController)
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreen() {
    FoodMateTheme {
        HomeScreen(viewModel = hiltViewModel(), navController = rememberNavController())
    }
}

@SuppressLint("RememberReturnType")
@Composable
fun GreetingSection(userName: String) {
    val context = LocalContext.current
    val greeting = remember { Helper.getGreetingBasedOnTime() }

    Text(
        text = "Hey $userName,",
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal
    )
    Spacer(modifier = Modifier.height(0.dp))
    Text(
        text = greeting,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold
    )
}



@OptIn(ExperimentalPagerApi::class)
@Composable
fun ImageCarousel(navController: NavController, campaigns: List<Campaign>, isLoading: Boolean) {
    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        val pageState = rememberPagerState(pageCount = { campaigns.size })
        val coroutineScope = rememberCoroutineScope()

        // Auto-scroll effect
        LaunchedEffect(pageState) {
            while (true) {
                delay(3000) // Auto-slide every 3 seconds
                val nextPage = (pageState.currentPage + 1) % campaigns.size
                coroutineScope.launch {
                    pageState.animateScrollToPage(nextPage, animationSpec = tween(1000)) // Smooth animation
                }
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //Slider Image
            HorizontalPager(
                state = pageState,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(145.dp)
                    .clip(RoundedCornerShape(8.dp))
            ) { page ->
                AsyncImage(
                    model = campaigns[page].banner,
                    contentDescription = campaigns[page].name,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                        .clip(RoundedCornerShape(8.dp))
                    .clickable {
                    navController.navigate("campaignDetails/${campaigns[page].id}")
                    }
                )
            }

            //Dots Indicator
            Row(
                modifier = Modifier.padding(top = 8.dp).offset(y = -25.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(campaigns.size) { index ->
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .size(if (pageState.currentPage == index) 8.dp else 6.dp) // Bigger size for active dot
                            .clip(CircleShape)
                            .background(if (pageState.currentPage == index) Color.White else Color.Gray.copy(alpha = 0.5f))
                    )
                }
            }
        }
    }
}

@Composable
fun MealOptionsSection(navController: NavController) {
    Row(
        modifier = Modifier.offset(y = -5.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        MealOptionCard(
            title = "Meal Order",
            subtitle = "Order Your Daily Loved Meal",
            discount = "Get Extra 35% Off",
            imageRes = R.drawable.ic_meal,
            modifier = Modifier.weight(1f)
                .clickable {
                navController.navigate("meal")
            },
        )
        MealOptionCard(
            title = "Dine-in",
            subtitle = "Book your favorite dine.",
            discount = "Get Extra 20% Off",
            imageRes = R.drawable.ic_dine_in,
            modifier = Modifier.weight(1f)
                .clickable {
                    navController.navigate("dineIn")
                }
        )
    }
}

@Composable
fun MealOptionCard(title: String, subtitle: String, discount: String, imageRes: Int, modifier: Modifier = Modifier, shape: Shape = RoundedCornerShape(8.dp) ) {
    Card(
        shape = shape,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = modifier,
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical =  10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
//                    .padding(end = 8.dp)
            ) {
                Column(
                    modifier = Modifier
                    .padding(start = 8.dp)
                ) {
                    Text(
                        text = title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        lineHeight = 18.sp // Reduced line height for closer line spacing
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = subtitle,
                        fontSize = 9.sp,
                        color = Color.Gray,
                        lineHeight = 10.sp, // Reduced line height for subtitle
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))

                Box(
                    modifier = Modifier
                        .background(
                            Color(0xFFFFEBEB),
                            shape = RoundedCornerShape(topEnd = 12.dp, bottomEnd = 12.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp)

                ) {
                    Text(
                        text = discount,
                        fontSize = 8.sp,
                        color = Color.Red,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 14.sp // Reduced line height for discount text
                    )
                }
            }

            Image(
                painter = painterResource(id = imageRes),
                contentDescription = title,
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(70.dp)
            )
        }
    }
}

@Composable
fun CuisinesSection() {
    val cuisines = listOf("Burger", "Pizza", "Sushi", "Pasta", "Salad", "Steak", "Tacos")

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Cuisines 🌟", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            TextButton(onClick = { /* Navigate to Cuisines */ }) {
                Text("See All", color = Color(0xFFE00101))
                Spacer(modifier = Modifier.width(3.dp))
                Icon(
                    painter = painterResource(id = R.drawable.ic_forward),
                    contentDescription = "Go",
                    tint = Color(0xFFE00101),
                            modifier = Modifier.size(12.dp)
                )
            }
        }

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 2.dp)
        ) {
            items(cuisines) { cuisine ->
                CuisineChip(name = cuisine)
            }
        }
    }
}

@Composable
fun CuisineChip(name: String) {
    Card(
        shape = RoundedCornerShape(50),
        colors = CardDefaults.cardColors(containerColor = Color.White), // Set background color to white
        modifier = Modifier
            .height(55.dp)
            .width(120.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .background(
                        Color(0xFFFFFFFF),
                        shape = RoundedCornerShape(30.dp)
                    )
                    .padding(2.dp)

            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_cuisine),
                    contentDescription = name,
                    modifier = Modifier
                        .size(40.dp)

                )
            }

            Spacer(modifier = Modifier.width(2.dp))
            Text(name, fontSize = 12.sp)
        }
    }
}

@Composable
fun NearRestaurantsSection(navController: NavController, restaurants: List<com.food.foodmate.dataModel.shop.Restaurant>, isLoading: Boolean) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Near Restaurants", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            TextButton(onClick = { /* Navigate to Restaurants */ }) {
                Text("See All", color = Color(0xFFE00101))
                Spacer(modifier = Modifier.width(3.dp))
                Icon(
                    painter = painterResource(id = R.drawable.ic_forward),
                    contentDescription = "Go",
                    tint = Color(0xFFE00101),
                    modifier = Modifier.size(12.dp)
                )
            }
        }

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
                modifier = Modifier
            ) {
                restaurants.forEach { restaurant ->
                    RestaurantCard(restaurant = restaurant, navController = navController)
                }
            }
        }
    }
}

@Composable
fun RestaurantCard(restaurant: Restaurant, navController: NavController) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier
                .clickable {
                    navController.navigate("restaurantDetails/${restaurant.id}")
                },
        ) {
            AsyncImage(
                model = restaurant.banner,
                contentDescription = restaurant.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.height(8.dp))
            Column(
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .padding(horizontal = 15.dp)
                    .padding(end = 5.dp)
            ) {
                Row(

                ){
                    Text(
                        text = restaurant.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        lineHeight = 18.sp
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_star),
                            contentDescription = "Rating",
                            tint = Color(0xFFE00101),
                            modifier = Modifier.size(15.dp)
                        )

                        Spacer(modifier = Modifier.width(5.dp))

                        Text(
                            text = "${restaurant.rating}",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Text(text = "Delicious Food", fontSize = 12.sp, color = Color.Gray, lineHeight = 14.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_delivery),
                            contentDescription = "Delivery Fee",
                            tint = Color(0xFFE00101),
                            modifier = Modifier.size(15.dp)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(text = "Tk ${restaurant.deliveryCharge} Delivery Fee", fontSize = 12.sp)
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_time),
                            contentDescription = "Delivery Time",
                            tint = Color(0xFFE00101),
                            modifier = Modifier.size(15.dp)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(text = "${restaurant.deliveryTime} mins", fontSize = 12.sp)
                    }
                }
            }
        }
    }
}
