package com.food.foodmate.views.cartScreen

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.food.foodmate.R
import com.food.foodmate.viewModel.AuthViewModel
import com.food.foodmate.viewModel.CartItem
import com.food.foodmate.viewModel.CartViewModel
import com.food.foodmate.views.customViews.pageHeader.PageHeader

@Composable
fun CartScreen(
    navController: NavHostController,
    viewModel: AuthViewModel = hiltViewModel(),
    cartViewModel: CartViewModel = hiltViewModel()
) {
    val cartItems by cartViewModel.cartItems.collectAsState(initial = emptyList())

    val cartItemSubTotal = cartItems.sumOf { it.price * it.quantity }
    val cartItemTotal = cartItems.sumOf { it.sellPrice * it.quantity }
    val deliveryCharge = cartViewModel.getDeliveryCharge() // Get delivery charge dynamically
    val discount = (cartItemSubTotal - cartItemTotal)
    val totalAmount = cartItemSubTotal + deliveryCharge - discount

    val shopName = cartItems.firstOrNull()?.shopName
    val shopLogo = cartItems.firstOrNull()?.shopLogo
    val allowDine = cartItems.firstOrNull()?.allowDine
    val allowMealOrder = cartItems.firstOrNull()?.allowMealOrder

    val weekDays = listOf("Sat", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri")
    val selectedDayIndices = remember { mutableStateOf<Set<Int>>(emptySet()) }

    val mealTimes = listOf("Breakfast", "Lunch", "Dinner")
    var selectedMealTime by remember { mutableStateOf("") }


    Column(modifier = Modifier.fillMaxSize().background(Color.White)) {
        PageHeader(title = "Cart") { navController.popBackStack() }

        if (cartItems.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF8F8F8))
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Cart is empty!", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(12.dp))
                Text("Add your favorite meals.", fontSize = 14.sp, color = Color.Gray)
            }

        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF8F8F8))
                    .weight(1f)
                    .padding(horizontal = 16.dp)
            ) {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AsyncImage(
                            model = "${shopLogo}",
                            contentDescription = "Restaurant Logo",
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(Color.Gray)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                "${shopName}",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                "Order Items",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Text(
                            "Clear Cart",
                            fontSize = 14.sp,
                            color = colorResource(R.color.customRed),
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.clickable { cartViewModel.clearCart() }
                        )
                    }

                    if (allowMealOrder == true) {
                        Text(
                            text = "Select Day",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(bottom = 6.dp)
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .horizontalScroll(rememberScrollState()),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            weekDays.forEachIndexed { index, day ->
                                val isSelected = selectedDayIndices.value.contains(index)

                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(4.dp))
                                        .background(
                                            if (isSelected) Color(0xFFFFE8E8)
                                            else Color.White,
                                            shape = RoundedCornerShape(4.dp)
                                        )
                                        .border(
                                            1.dp,
                                            if (isSelected) Color(0xFFE00101) else Color.Gray,
                                            RoundedCornerShape(4.dp)
                                        )
                                        .clickable {
                                            // Step 2: Toggle day selection
                                            selectedDayIndices.value = if (isSelected) {
                                                selectedDayIndices.value - index  // Remove index
                                            } else {
                                                selectedDayIndices.value + index  // Add index
                                            }
                                        }
                                        .padding(horizontal = 16.dp, vertical = 8.dp)
                                ) {
                                    Text(
                                        text = day,
                                        fontSize = 14.sp,
                                        color = if (isSelected) Color(0xFFE00101) else Color.Black
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        // Meal Time Selection
                        Text(
                            text = "Select Meal Time",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(bottom = 6.dp)
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            mealTimes.forEach { meal ->
                                val isSelected = (meal == selectedMealTime)
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(4.dp))
                                        .background(
                                            if (isSelected) Color(0xFFFFE8E8)
                                            else Color.White,
                                            shape = RoundedCornerShape(4.dp)
                                        )
                                        .border(
                                            1.dp,
                                            if (isSelected) Color(0xFFE00101) else Color.Gray,
                                            RoundedCornerShape(4.dp)
                                        )
                                        .clickable {
                                            selectedMealTime = meal
                                        }
                                        .padding(horizontal = 16.dp, vertical = 8.dp)
                                ) {
                                    Text(
                                        text = meal,
                                        fontSize = 14.sp,
                                        color = if (isSelected) Color(0xFFE00101) else Color.Black
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }

                items(cartItems) { item ->
                    CartItemRow(cartItem = item, cartViewModel = cartViewModel)
                }

                item {
                    Text(
                        "+ Add more items",
                        color = colorResource(R.color.customRed),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(top = 8.dp, bottom = 16.dp)
                            .clickable { navController.popBackStack() }
                    )
                }
            }

            if (cartItems.isNotEmpty()) {
                CheckoutSummary(
                    subTotal = cartItemSubTotal,
                    deliveryCharge = deliveryCharge,
                    discount = discount,
                    total = totalAmount,
                    onCheckout = {
                        if (allowMealOrder == true) {
                            val selectedDaysList = selectedDayIndices.value.map { weekDays[it] }
                            val daysString = selectedDaysList.joinToString(",")
                            navController.navigate("checkout?days=$daysString&mealTime=$selectedMealTime")
                        } else {
                            navController.navigate("checkout")
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun CartItemRow(
    cartItem: CartItem,
    cartViewModel: CartViewModel
) {
    // States for handling a shop conflict
    var showClearCartDialog by remember { mutableStateOf(false) }
    var pendingCartItem by remember { mutableStateOf<CartItem?>(null) }

    // If a conflict arises, show the dialog
    if (showClearCartDialog && pendingCartItem != null) {
        AlertDialog(
            onDismissRequest = {
                showClearCartDialog = false
                pendingCartItem = null
            },
            title = { Text("Clear Cart?") },
            text = {
                Text(
                    "Your cart contains items from another shop. " +
                            "Do you want to clear the cart and add items from this shop?"
                )
            },
            confirmButton = {
                TextButton(onClick = {
                    // Clear the cart and add the new item
                    cartViewModel.replaceCartWithNewItem(pendingCartItem!!)
                    pendingCartItem = null
                    showClearCartDialog = false
                }) {
                    Text("Yes")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    // Cancel
                    pendingCartItem = null
                    showClearCartDialog = false
                }) {
                    Text("No")
                }
            }
        )
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Product Image
            AsyncImage(
                model = cartItem.imageUrl,
                contentDescription = cartItem.name,
                modifier = Modifier
                    .size(65.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.LightGray)
            )

            Spacer(modifier = Modifier.width(10.dp))

            // Product Info
            Column(modifier = Modifier.weight(1f)) {
                Text(cartItem.name, fontSize = 14.sp, fontWeight = FontWeight.Bold)

                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("TK ${cartItem.sellPrice}", fontSize = 12.sp, color = Color(0xFF2A2A2A), fontWeight = FontWeight.SemiBold)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("TK ${cartItem.price}", fontSize = 11.sp, color = Color(0xFF838383), fontWeight = FontWeight.Normal, style = TextStyle(textDecoration = TextDecoration.LineThrough))
                }
            }

            // Quantity Controls
            Row(
                modifier = Modifier
                    .border(1.dp, Color.Gray, RoundedCornerShape(5.dp))
                    .padding(horizontal = 6.dp, vertical = 3.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Decrease Button
                IconButton(
                    onClick = {
                        if (cartItem.quantity > 1) {
                            cartViewModel.addToCart(
                                cartItem.copy(quantity = cartItem.quantity - 1),
                                onShowClearCartDialog = {
                                    // Different shop conflict => Show dialog
                                    pendingCartItem = cartItem.copy(quantity = cartItem.quantity - 1)
                                    showClearCartDialog = true
                                }
                            )
                        } else {
                            // If quantity is 1 and user tries to decrease, remove item
                            cartViewModel.removeFromCart(cartItem)
                        }
                    },
                    modifier = Modifier.size(20.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_remove),
                        contentDescription = "Decrease Quantity",
                        tint = Color.Black,
                        modifier = Modifier.size(14.dp)
                    )
                }

                // Quantity Text
                Text(
                    text = "${cartItem.quantity}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )

                // Increase Button
                IconButton(
                    onClick = {
                        cartViewModel.addToCart(
                            cartItem.copy(quantity = cartItem.quantity + 1),
                            onShowClearCartDialog = {
                                // Different shop conflict => Show dialog
                                pendingCartItem = cartItem.copy(quantity = cartItem.quantity + 1)
                                showClearCartDialog = true
                            }
                        )
                    },
                    modifier = Modifier.size(20.dp)
                ) {
                    Icon(
                        painter = painterResource(id = android.R.drawable.ic_input_add),
                        contentDescription = "Increase Quantity",
                        tint = Color.Black,
                        modifier = Modifier.size(14.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Delete Button
            IconButton(
                onClick = { cartViewModel.removeFromCart(cartItem) },
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_menu_delete),
                    contentDescription = "Remove",
                    tint = colorResource(R.color.customRed),
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}


@Composable
fun CheckoutSummary(
    subTotal: Double,
    deliveryCharge: Double,
    discount: Double,
    total: Double,
    onCheckout: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Sub-Total", fontSize = 14.sp, color = Color(0xFF2a2A2A))
            Text("৳ $subTotal", fontSize = 14.sp, fontWeight = FontWeight.Bold)
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Delivery Charge", fontSize = 14.sp, color = Color(0xFF2a2A2A))
            Text("৳ $deliveryCharge", fontSize = 14.sp, fontWeight = FontWeight.Bold)
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Discount", fontSize = 14.sp, color = Color(0xFF2a2A2A))
            Text("- ৳ $discount", fontSize = 14.sp, fontWeight = FontWeight.Bold)
        }

        Divider(modifier = Modifier.padding(vertical = 8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Total", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text("৳ $total", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = onCheckout,
            colors = ButtonDefaults.buttonColors(colorResource(R.color.customRed)),
            shape = RoundedCornerShape(6.dp),
            modifier = Modifier.fillMaxWidth().height(45.dp)
        ) {
            Text("CHECKOUT", fontSize = 16.sp, color = Color.White)
        }
    }
}
