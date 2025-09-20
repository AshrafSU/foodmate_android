package com.food.foodmate.views.campaignDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.food.foodmate.R
import com.food.foodmate.viewModel.AuthViewModel
import com.food.foodmate.views.home.RestaurantCard
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun CampaignDetailsView(navController: NavController, campaignId: String, viewModel: AuthViewModel = hiltViewModel()) {
    val campaignDetails by viewModel.campaignDetails.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val restaurants by viewModel.campaignShopList.collectAsState()



    LaunchedEffect(campaignId) {
        viewModel.getCampaignShopList(campaignId)
    }
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color(0xFFAD3203),
            darkIcons = false
        )
    }

    Column(modifier = Modifier.fillMaxSize()) {
        if (isLoading) {
            // Show loading indicator
            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            campaignDetails?.let { campaign ->
                Column(
//                    modifier = Modifier.fillMaxSize()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                            .drawBehind {
                                drawRect(Color.Transparent)
                            }

                    ) {
                        AsyncImage(
                            model = campaign.banner,
                            contentDescription = "Campaign Banner",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize().zIndex(-1f)
                        )

                        IconButton(
                            onClick = { navController.popBackStack() },
                            modifier = Modifier
                                .padding(12.dp)
                                .clip(RoundedCornerShape(50))
                                .background(Color.White.copy(alpha = 0.7f))
                                .size(36.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_back),
                                contentDescription = "Back Button",
                                tint = Color.Black,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }

                    //Info
                    Card(
                        shape = RoundedCornerShape(6.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .offset(y = -25.dp),

                        elevation = CardDefaults.cardElevation(2.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White)
                                .padding(horizontal = 15.dp, vertical = 15.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = campaign.name,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp,
                                maxLines = 1
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                text = "Show Details",
                                fontWeight = FontWeight.Bold,
                                fontSize = 10.sp,
                                color = colorResource(R.color.customRed)
                            )
                        }
                    }

                    //shops
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .verticalScroll(rememberScrollState())
                            .fillMaxSize()
                            .background(Color(0xFFF8F8F8))
                            .padding(horizontal = 16.dp)
                    ) {
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
                                Text(
                                    "No restaurants found.",
                                    fontSize = 16.sp,
                                    color = Color.Gray
                                )
                            }
                        } else {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(15.dp),
                                modifier = Modifier.padding(horizontal = 4.dp)
                            ) {
                                restaurants.forEach { restaurant ->
                                    restaurant.shop?.let {
                                        RestaurantCard(
                                            restaurant = it,
                                            navController = navController
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

            } ?: run {
                Text("Details not found", color = Color.Red, modifier = Modifier.fillMaxSize())
            }
        }
    }
}


