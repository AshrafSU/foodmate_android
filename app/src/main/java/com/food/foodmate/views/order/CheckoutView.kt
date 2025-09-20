package com.food.foodmate.views.order.checkout

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.food.foodmate.viewModel.CartViewModel
import com.food.foodmate.viewModel.OrderState
import com.food.foodmate.views.customViews.pageHeader.PageHeader

@Composable
fun CheckoutView(
    navController: NavHostController,
    cartViewModel: CartViewModel = hiltViewModel(),
    selectedDays: List<String>,
    selectedMealTime: String
) {
    val orderState by cartViewModel.orderState.collectAsState()

    val cartItems by cartViewModel.cartItems.collectAsState(initial = emptyList())
    val cartItemSubTotal = cartItems.sumOf { it.price * it.quantity }
    val cartItemTotal = cartItems.sumOf { it.sellPrice * it.quantity }
    val deliveryCharge = cartViewModel.getDeliveryCharge()
    val discount = cartItemSubTotal - cartItemTotal
    val totalAmount = cartItemSubTotal + deliveryCharge - discount

    val shopName = cartItems.firstOrNull()?.shopName ?: "Restaurant"
    val shopAddress = cartItems.firstOrNull()?.shopName ?: "Restaurant Address"
    val shopLogo = cartItems.firstOrNull()?.shopLogo ?: ""
    val allowMealOrder = cartItems.firstOrNull()?.allowMealOrder

    val paymentMethods = listOf("COD", "Online Pay")
    var selectedPaymentMethod by remember { mutableStateOf("COD") }

    var selectedAddress by remember { mutableStateOf("Office") }
    var selectedRadioOption by remember { mutableStateOf("Once") }

    var additionalInstructions by remember { mutableStateOf("") }
    val weekDays = listOf("Sat", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri")

    val isLoading by cartViewModel.isLoading.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    var dialogMessage by remember { mutableStateOf("") }

    LaunchedEffect(orderState) {
        when (orderState) {
            is OrderState.Success -> {
                cartViewModel.clearCart()
                navController.navigate("orderSuccess")
            }
            is OrderState.Error -> {
                val errorMsg = (orderState as OrderState.Error).errorMessage
                println("Order failed: $errorMsg")
                showDialog = true
                dialogMessage = (orderState as OrderState.Error).errorMessage
            }
            else -> {}
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F8F8))
    ) {
        PageHeader(title = "Checkout") { navController.popBackStack() }

            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = { Text("Order Failed") },
                    text = { Text(dialogMessage) },
                    confirmButton = {
                        TextButton(onClick = { showDialog = false }) {
                            Text("OK")
                        }
                    }
                )
            }

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp)
            ) {
                // Restaurant Info
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 10.dp)
                ) {
                    AsyncImage(
                        model = shopLogo,
                        contentDescription = "Restaurant Logo",
                        modifier = Modifier
                            .size(45.dp)
                            .clip(CircleShape)
                            .background(Color.Gray)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(text = shopName, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        Text(text = shopAddress, fontSize = 14.sp, color = Color.Gray)
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "PAYMENT METHOD",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(6.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 5.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    paymentMethods.forEach { method ->
                        val isSelected = (method == selectedPaymentMethod)

                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = Color(0xFFF5F5F5),
                            border = BorderStroke(
                                1.dp,
                                if (isSelected) Color(0xFFE00101) else Color.LightGray
                            ),
                            modifier = Modifier
                                .weight(1f)
                                .clickable { selectedPaymentMethod = method }
                        ) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .padding(vertical = 12.dp)
                            ) {
                                Text(
                                    text = method,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = if (isSelected) Color(0xFFE00101) else Color.DarkGray
                                )
                            }
                        }
                    }
                }



                Spacer(modifier = Modifier.height(10.dp))

                // Delivery Address Section
                Text("Delivery Address", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(6.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(6.dp))
                        .border(1.dp, Color.Gray, RoundedCornerShape(6.dp))
                        .clickable {

                        }
                        .padding(16.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = selectedAddress, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                        Text(text = "Edit Address", fontSize = 14.sp, color = Color(0xFFE00101))
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                if (allowMealOrder == true) {
                    if (selectedMealTime.isNotEmpty()) {
                        Text("Selected Meal", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                        Spacer(modifier = Modifier.height(5.dp))
                        Row(
                            modifier = Modifier,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .border(1.dp, Color.Red, RoundedCornerShape(4.dp))
                                    .background(Color.White)
                                    .padding(horizontal = 16.dp, vertical = 6.dp)
                            ) {
                                Text(selectedMealTime, color = Color.Red, fontSize = 14.sp)
                            }

                            Spacer(modifier = Modifier.weight(1f))

//                            Row(
//                                verticalAlignment = Alignment.CenterVertically,
//                                modifier = Modifier.clickable { selectedRadioOption = "Once" }
//                            ) {
//                                RadioButton(
//                                    selected = (selectedRadioOption == "Once"),
//                                    onClick = { selectedRadioOption = "Once" }
//                                )
//                                Text("Once", fontSize = 14.sp)
//                            }
//
//                            Spacer(modifier = Modifier.width(10.dp))
//
//                            Row(
//                                verticalAlignment = Alignment.CenterVertically,
//                                modifier = Modifier.clickable { selectedRadioOption = "Repeated" }
//                            ) {
//                                RadioButton(
//                                    selected = (selectedRadioOption == "Repeated"),
//                                    onClick = { selectedRadioOption = "Repeated" }
//                                )
//                                Text("Repeated", fontSize = 14.sp)
//                            }
                        }
                        Spacer(modifier = Modifier.height(5.dp))
                    }

                    if (selectedDays.isNotEmpty()) {
                        Text("Selected Days", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                        Spacer(modifier = Modifier.height(5.dp))
                        Row(
                            modifier = Modifier.horizontalScroll(rememberScrollState()),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            selectedDays.forEach { day ->
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(4.dp))
                                        .border(1.dp, Color(0xFFE00101), RoundedCornerShape(4.dp))
                                        .background(Color.White)
                                        .padding(horizontal = 16.dp, vertical = 6.dp)
                                ) {
                                    Text(day, color = Color(0xFFE00101), fontSize = 14.sp)
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }


                Text("Additional Instruction", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(6.dp))
                OutlinedTextField(
                    value = additionalInstructions,
                    onValueChange = { additionalInstructions = it },
                    placeholder = { Text("E.g., Please call on arrival") },
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 3,
                    shape = RoundedCornerShape(6.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text("ORDER SUMMARY", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Sub-total", fontSize = 14.sp)
                    Text("৳ $cartItemSubTotal", fontSize = 14.sp)
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Delivery Charge", fontSize = 14.sp)
                    Text("৳ $deliveryCharge", fontSize = 14.sp)
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Discount", fontSize = 14.sp)
                    Text("৳ $discount", fontSize = 14.sp)
                }
                Divider(modifier = Modifier.padding(vertical = 8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Total", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Text("৳ $totalAmount", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Place Order Button
                Button(
                    onClick = {
                        cartViewModel.placeOrder(
                            deliveryType = "DELIVERY",
                            paymentMethod = selectedPaymentMethod,
                            reOrderDays = selectedDays
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE00101)),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text("Place Order", fontSize = 16.sp, color = Color.White)
                }

                Spacer(modifier = Modifier.height(16.dp))
            }

            if (isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

