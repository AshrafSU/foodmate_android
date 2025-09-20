package com.food.foodmate.views.orderScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.food.foodmate.network.Order
import com.food.foodmate.utility.Helper
import com.food.foodmate.viewModel.CartViewModel
import com.food.foodmate.views.customViews.BottomNavigationBar
import com.food.foodmate.views.customViews.pageHeader.PageHeader


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrdersScreen(orderViewModel: CartViewModel = hiltViewModel(), navController: NavHostController) {
    var selectedTab by remember { mutableStateOf(0) }

    val isLoading by orderViewModel.isLoading.collectAsState()
    val orders by orderViewModel.myOrderList.collectAsState()
    val ongoingOrders = orders.filter { it.status != "COMPLETED" }
    val historyOrders = orders.filter { it.status == "COMPLETED" }


    LaunchedEffect(Unit){
        orderViewModel.getMyOrders()
    }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ){
        Column(modifier = Modifier.fillMaxSize()) {
            // Header
            PageHeader(title = "Orders") { navController.popBackStack() }

            // Tabs
            TabRow(
                selectedTabIndex = selectedTab,
                containerColor = Color(0xFFF8F8F8),
                contentColor = Color(0xFFCD113B),
                modifier = Modifier.background(Color(0xFFF8F8F8))
            ) {
                Tab(
                    selected = selectedTab == 0,
                    onClick = {
                        selectedTab = 0
                        println("Orders: ${orders}\n Ongoing: ${ongoingOrders}\nCompleted: ${historyOrders}")},
                    text = { Text("Ongoing", fontWeight = FontWeight.Bold, fontSize = 14.sp) }
                )
                Tab(
                    selected = selectedTab == 1,
                    onClick = {
                        selectedTab = 1
                              },
                    text = { Text("History", fontWeight = FontWeight.Bold, fontSize = 14.sp) }
                )
            }

            // Order Lists
            Column(
                modifier = Modifier
                    .background(Color(0xFFF8F8F8))
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)

            ) {
                val ordersToShow = if (selectedTab == 0) ongoingOrders else historyOrders

                if (ordersToShow.isEmpty()) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("No orders found!", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            "Start ordering your favorite meals.",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }
                } else {
                    ordersToShow.forEach { order ->
                        OrderCard(order = order, navController = navController)
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun OrderCard(order: Order, navController: NavHostController) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 3.dp, shape = RoundedCornerShape(12.dp))
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = "${order.shop.logo}",
                    contentDescription = "Restaurant Logo",
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(order.shop.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Spacer(modifier = Modifier.height(4.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("৳ ${order.total}", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                        Spacer(modifier = Modifier.weight(1f))
                        Text("#${order.orderId}", color = Color(0xFF717284), fontSize = 12.sp)
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val statusColor = when (order.status) {
                            "ONGOING" -> Color(0xFFE67E22) // Orange for ongoing
                            "COMPLETED" -> Color(0xFF27AE60) // Green for completed
                            else -> {
                                Color.Black
                            }
                        }

                        Text("${Helper.getDate(order.createdAt)}", fontSize = 12.sp, color = Color.Gray)
                        Spacer(modifier = Modifier.weight(1f))
                        Text(order.status, color = statusColor, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row {
                    if (order.status != "COMPLETED"){
                        ActionButton(navController = navController, orderId = order.orderId ,label = "Track Order", filled = true)
                    }
//                    else{
//                        ActionButton(navController = navController, orderId = order.orderId ,label = "Rate", filled = false)
//                    }


                    Spacer(modifier = Modifier.weight(1f))

                    if (order.status == "COMPLETED"){
//                        ActionButton(navController = navController, orderId = order.orderId ,label = "Cancel", filled = false)
//                    }else{
                        ActionButton(navController = navController, orderId = order.orderId ,label = "Order Details", filled = true)
                    }
                }
            }
        }
    }
}


@Composable
fun ActionButton(navController: NavHostController, orderId: String, label: String, filled: Boolean, modifier: Modifier = Modifier) {
    val backgroundColor = if (filled) Color(0xFFE00101) else Color.Transparent
    val textColor = if (filled) Color.White else Color(0xFFD32F2F)
    val border = if (!filled) BorderStroke(1.dp, Color(0xFFD32F2F)) else null

    Button(
        onClick = {
//            if (label == "Track Order") {
                navController.navigate("orderDetailsView/${orderId}")
//            }
//            if (label == "Re-Order") {
//
//                navController.navigate("orderDetailsView/${orderId}")
//            }
        },
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
        border = border,
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .height(40.dp)
            .fillMaxWidth()
//            .width(140.dp)
    ) {
        Text(label, color = textColor, fontWeight = FontWeight.Bold, fontSize = 14.sp)
    }
}