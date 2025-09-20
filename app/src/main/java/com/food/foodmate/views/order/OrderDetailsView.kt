package com.food.foodmate.views.order.orderDetailsView


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.food.foodmate.R
import com.food.foodmate.network.OrderData
import com.food.foodmate.viewModel.CartViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState


@Composable
fun OrderDetailsScreen(
    orderId: String,
    navController: NavHostController,
    orderViewModel: CartViewModel = hiltViewModel()
) {
    LaunchedEffect(orderId) {
        orderViewModel.getOrderDetailsByOrderId(orderId)
    }

    val isLoading by orderViewModel.isLoading.collectAsState()
    val orderDetails by orderViewModel.orderDetails.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F8F8))
    ) {
//        PageHeader(title = "Track Order") {
//            navController.popBackStack()
//        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                navController.navigate("orderScreen")
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Track Order", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        }

        Box(modifier = Modifier.fillMaxSize()) {
            when {
                isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                orderDetails != null -> {
                    OrderDetailsView(order = orderDetails!!)
                }
                else -> {
                    Text(
                        text = "No order details found.",
                        modifier = Modifier.align(Alignment.Center),
                        fontSize = 16.sp,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}

@Composable
fun OrderDetailsView(
    order: OrderData
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .height(290.dp)
            .fillMaxWidth()
        ) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = rememberCameraPositionState {
                    position = CameraPosition.fromLatLngZoom(
                        LatLng(order.shop.location.latitude, order.shop.location.longitude),
                        14f
                    )
                }
            ) {
                // Shop Marker
                Marker(
                    state = MarkerState(
                        position = LatLng(
                            order.shop.location.latitude,
                            order.shop.location.longitude
                        )
                    ),
                    title = order.shop.name
                )

                // User Marker
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp)
        ) {
            Column(modifier = Modifier){
                if (order.status != "COMPLETED"){
                    Text(
                        text = "20 min",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "ESTIMATED DELIVERY TIME",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Text("Your order has been received", color = Color.Black)
                    Text("The restaurant is preparing your food", color = Color.Black)
                    Spacer(modifier = Modifier.height(16.dp))
                }else{
                    Text(
                        text = "Delivered",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "DELIVERY TIME 20 MIN",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Text("Your order has been delivered", color = Color.Black)
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            // Shop & Order Items
            Card(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = order.shop.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = order.shop.address,
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    // Amount details (example)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Sub-Total", color = Color.Gray)
                        Text("৳ ${order.subTotal}", fontWeight = FontWeight.SemiBold)
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Delivery Charge", color = Color.Gray)
                        Text("৳ ${order.deliveryCharge}", fontWeight = FontWeight.SemiBold)
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Discount", color = Color.Gray)
                        Text("৳ ${order.discountAmount}", fontWeight = FontWeight.SemiBold, color = Color.Red)
                    }
                    Divider(modifier = Modifier.padding(vertical = 8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Total", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        Text("৳ ${order.total}", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))


            RiderInfoCardStatic()
        }
    }
}

@Composable
fun RiderInfoCardStatic() {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // A placeholder for rider image
            Image(
                painter = painterResource(id = R.drawable.ic_person),
                contentDescription = "Rider Photo",
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = "Ashraful Islam",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Delivery Hero",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = R.drawable.ic_contact_us),
                contentDescription = "Call Icon",
                modifier = Modifier.size(48.dp)
                    .clickable {
//                        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:01712589458"))
//                        context.startActivity(intent)
                }
            )
        }
    }
}



