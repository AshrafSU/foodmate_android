package com.food.foodmate.views.restaurantDetails

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.food.foodmate.R
import com.food.foodmate.dataModel.products.Food
import com.food.foodmate.viewModel.AuthViewModel
import com.food.foodmate.viewModel.CartItem
import com.food.foodmate.viewModel.CartViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun RestaurantDetailsScreen(
    navController: NavController,
    restaurantId: String,
    viewModel: AuthViewModel = hiltViewModel(),
    cartViewModel: CartViewModel = hiltViewModel()
) {
    val shopDetails by viewModel.shopDetails.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val foodList by viewModel.foodList.collectAsState()
    val popularFoodList = foodList.filter { it.isPopular }
    val cartItems by cartViewModel.cartItems.collectAsState(initial = emptyList())
    val cartItemTotal = cartItems.sumOf { it.sellPrice * it.quantity }
    val cartItemCount = cartItems.sumOf { it.quantity }

    val categories = foodList.map { it.category.name }.distinct()
    var selectedCategory by remember { mutableStateOf(categories.firstOrNull() ?: "") }
    val listState = rememberLazyListState()

    var isBannerVisible by remember { mutableStateOf(true) }

    var selectedProduct by remember { mutableStateOf<Food?>(null) }
    var showProductSheet by remember { mutableStateOf(false) }

    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )


    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(restaurantId) {
        viewModel.fetchShopDetails(restaurantId)
        viewModel.fetchRestaurantFoods(restaurantId)
    }
    LaunchedEffect(showProductSheet) {
        if (showProductSheet) {
            sheetState.show()
        } else {
            sheetState.hide()
        }
    }

    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .collect { index ->
                isBannerVisible = index == 0
                val visibleCategory = categories.getOrNull(index)
                if (visibleCategory != null) {
                    selectedCategory = visibleCategory
                }
            }
    }

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {
            selectedProduct?.let { product ->
                ProductDetailsBottomSheet(
                    product = product,
                    onClose = {
                        showProductSheet = false
                    },
                    cartViewModel = cartViewModel
                )
            } ?: Spacer(modifier = Modifier.height(1.dp))
        },
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            if (isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else {
                shopDetails?.let { shop ->
                    Box {
                        Column(modifier = Modifier.fillMaxSize()) {
                            LazyColumn(
                                state = listState,
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxWidth()
                                    .background(Color(0xFFF8F8F8)),
                                contentPadding = PaddingValues(bottom = 70.dp)
                            ) {
                                // Banner Section
                                item {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(180.dp)
                                    ) {
                                        AsyncImage(
                                            model = shop.banner,
                                            contentDescription = "Restaurant Banner",
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier.fillMaxSize()
                                        )
                                    }
                                }

                                // Sticky Header – Restaurant Info & Category Tabs
                                stickyHeader {
                                    Column(
                                        modifier = Modifier
                                            .background(Color.White)
                                    ) {
                                        Column(
                                            modifier = Modifier
                                                .padding(horizontal = 16.dp, vertical = 10.dp)
                                        ) {
                                            Row(
                                                modifier = Modifier.fillMaxWidth()
                                                    .padding(start = if (!isBannerVisible) 40.dp else 0.dp),
                                                verticalAlignment = Alignment.Bottom
                                            ) {
                                                if (isBannerVisible) {
                                                    AsyncImage(
                                                        model = shop.logo,
                                                        contentDescription = "Restaurant Logo",
                                                        modifier = Modifier
                                                            .size(58.dp)
                                                            .clip(CircleShape)
                                                            .background(Color.Gray),
                                                        contentScale = ContentScale.Crop
                                                    )
                                                    Spacer(modifier = Modifier.width(12.dp))
                                                }

                                                Column(modifier = Modifier.weight(1f)) {
                                                    Text(
                                                        shop.name,
                                                        fontSize = 16.sp,
                                                        fontWeight = FontWeight.Bold
                                                    )
                                                    Spacer(modifier = Modifier.height(5.dp))
                                                    Text(
                                                        shop.address,
                                                        fontSize = 12.sp,
                                                        color = Color.Black,
                                                        maxLines = 1
                                                    )
                                                }

                                                IconButton(
                                                    onClick = {
                                                        cartViewModel.clearCart()
                                                    },
                                                    modifier = Modifier
                                                        .clip(RoundedCornerShape(8.dp))
                                                        .background(Color(0xFFD32F2F))
                                                        .width(60.dp)
                                                        .height(28.dp)
                                                ) {
                                                    Text(
                                                        "Follow",
                                                        color = Color.White,
                                                        fontSize = 12.sp,
                                                        fontWeight = FontWeight.SemiBold
                                                    )
                                                }
                                            }
                                        }

                                        // Horizontal Category Tabs
                                        LazyRow(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .background(Color.White)
                                                .padding(vertical = 10.dp),
                                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                                        ) {
                                            items(categories) { category ->
                                                Column(
                                                    horizontalAlignment = Alignment.CenterHorizontally,
                                                    modifier = Modifier.clickable {
                                                        selectedCategory = category
                                                    }
                                                ) {
                                                    Text(
                                                        text = category,
                                                        fontSize = 16.sp,
                                                        fontWeight = FontWeight.Bold,
                                                        color = if (selectedCategory == category) Color.Black else Color.Gray,
                                                        modifier = Modifier.padding(horizontal = 10.dp)
                                                    )
                                                    if (selectedCategory == category) {
                                                        Box(
                                                            modifier = Modifier
                                                                .height(3.dp)
                                                                .width(50.dp)
                                                                .background(
                                                                    Color.Red,
                                                                    shape = RoundedCornerShape(50)
                                                                )
                                                        )
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }

                                // Scrollable Content Section
                                item {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(top = 10.dp)
                                    ) {
                                        Text(
                                            "Popular Items \uD83D\uDD25",
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier.padding(horizontal = 16.dp)
                                        )
                                        Spacer(modifier = Modifier.height(10.dp))

                                        LazyVerticalGrid(
                                            columns = GridCells.Fixed(2),
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .heightIn(max = 600.dp)
                                                .padding(horizontal = 16.dp),
                                            horizontalArrangement = Arrangement.spacedBy(14.dp),
                                            verticalArrangement = Arrangement.spacedBy(10.dp)
                                        ) {
                                            items(popularFoodList) { food ->
                                                // Pass the shop info from RestaurantDetailsScreen
                                                ProductCard(
                                                    product = food,
                                                    cartViewModel = cartViewModel,
                                                    shopId = restaurantId,
                                                    shopName = shop.name,
                                                    shopLogo = shop.logo,
                                                    deliveryCharge = shop.deliveryCharge,
                                                    allowMealOrder = shop.allowMealOrder,
                                                    allowDine = shop.allowDine,
                                                    onProductClick = {
                                                        selectedProduct = food
                                                        showProductSheet = true
                                                    }
                                                )
                                            }
                                        }

                                        Spacer(modifier = Modifier.height(15.dp))
                                        // Optionally, you can enable the categorized list below.
                                        val groupedFoodList = foodList.groupBy { it.category.name }
                                        Column(
                                            modifier = Modifier.fillMaxWidth(),
                                            verticalArrangement = Arrangement.spacedBy(10.dp)
                                        ) {
                                            groupedFoodList.forEach { (categoryName, categoryFoods) ->
                                                Text(
                                                    text = categoryName,
                                                    fontSize = 18.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 5.dp).padding(top = 3.dp)
                                                )
                                                categoryFoods.forEach { food ->
                                                    ProductCell(
                                                        product = food,
                                                        cartViewModel = cartViewModel,
                                                        shopId = food.shopId,
                                                        shopName = food.shop.name,
                                                        shopLogo = food.shop.logo,
                                                        deliveryCharge = food.shop.deliveryCharge,
                                                        allowMealOrder = food.shop.allowMealOrder,
                                                        allowDine = food.shop.allowDine,
                                                        onProductClick = {
                                                            selectedProduct = food
                                                            showProductSheet = true
                                                        }
                                                    )
                                                }
                                            }
                                        }
                                        /*
                                        CategorizedFoodList(
                                            product = foodList,
                                            cartViewModel = cartViewModel,
                                            shopId = restaurantId,
                                            shopName = shop.name,
                                            shopLogo = shop.logo,
                                            deliveryCharge = shop.deliveryCharge,
                                            allowMealOrder = shop.allowMealOrder,
                                            allowDine = shop.allowDine,
                                            onProductClick = {
                                                selectedProduct = food
                                                showProductSheet = true
                                            }
                                        )

                                         */
                                    }
                                }
                            }

                            // Checkout Bar
                            if (cartItems.isNotEmpty()) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 10.dp)
                                        .padding(horizontal = 16.dp)
                                        .background(
                                            Color(0xFFE00101),
                                            shape = RoundedCornerShape(4.dp)
                                        )
                                        .padding(12.dp)
                                        .clickable {
                                            navController.navigate("cart")
                                        },
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.ic_cart_tab),
                                            contentDescription = "Delivery Fee",
                                            tint = Color.White,
                                            modifier = Modifier.size(16.dp)
                                        )
                                        Spacer(modifier = Modifier.width(10.dp))
                                        Text(
                                            text = "${cartItemTotal.toInt()} Tk",
                                            fontSize = 14.sp,
                                            color = Color.White
                                        )
                                        Spacer(modifier = Modifier.width(10.dp))
                                        Text(
                                            text = "$cartItemCount items",
                                            fontSize = 12.sp,
                                            color = Color.White
                                        )
                                    }

                                    Spacer(modifier = Modifier.weight(1f))

                                    Text(
                                        text = "CHECKOUT",
                                        style = MaterialTheme.typography.titleMedium,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                }
                            }
                        }

                        // Back Button (Top Left)
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
                } ?: run {
                    Text(
                        "Shop details not found",
                        color = Color.Red,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}

@Composable
fun ProductCard(
    product: Food,
    cartViewModel: CartViewModel,
    shopId: String,
    shopName: String,
    shopLogo: String,
    deliveryCharge: Double,
    allowMealOrder: Boolean,
    allowDine: Boolean,
    onProductClick: () -> Unit
) {
    var quantity by remember { mutableStateOf(0) }
    var showQuantitySelector by remember { mutableStateOf(false) }
    // State to store a pending item when shop conflict occurs
    var pendingCartItem by remember { mutableStateOf<CartItem?>(null) }
    // State to show the clear-cart dialog
    var showClearCartDialog by remember { mutableStateOf(false) }

    val cartItems by cartViewModel.cartItems.collectAsState(initial = emptyList())
    val cartItem = cartItems.find { it.foodId == product.id }

    LaunchedEffect(cartItem) {
        quantity = cartItem?.quantity ?: 0
        showQuantitySelector = quantity > 0
        if (showQuantitySelector) {
            delay(3000)
            showQuantitySelector = false
        }
    }

    // Show dialog if there's a shop conflict
    if (showClearCartDialog && pendingCartItem != null) {
        AlertDialog(
            onDismissRequest = { showClearCartDialog = false },
            title = { Text("Clear Cart?") },
            text = {
                Text("Your cart contains items from another shop. " +
                        "Do you want to clear the cart and add items from this shop?")
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
                    // Cancel the operation
                    pendingCartItem = null
                    showClearCartDialog = false
                }) {
                    Text("No")
                }
            }
        )
    }

    // UI for the product card
    Card(
        modifier = Modifier
            .height(210.dp)
            .padding(bottom = 4.dp)
            .shadow(elevation = 3.dp, shape = RoundedCornerShape(5.dp)),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(5.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.End
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .clip(RoundedCornerShape(5.dp))
            ) {
                Box(
                    modifier = Modifier.clickable { onProductClick() }
                ) {
                AsyncImage(
                    model = product.imageUrl,
                    contentDescription = product.name,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )
                }
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(horizontal = 10.dp)
                        .padding(bottom = 10.dp)
                        .background(Color.White, shape = RoundedCornerShape(25.dp))
                ) {
                    if (showQuantitySelector) {
                        Row(
                            modifier = Modifier
                                .background(Color.White, shape = RoundedCornerShape(20.dp))
                                .padding(2.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(
                                onClick = {
                                    if (quantity > 1) {
                                        cartViewModel.addToCart(
                                            cartItem!!.copy(quantity = quantity - 1),
                                            onShowClearCartDialog = {} // won't trigger here if same shop
                                        )
                                    } else {
                                        cartViewModel.removeFromCart(cartItem!!)
                                        showQuantitySelector = false
                                    }
                                },
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(Color(0xFFCD113B))
                                    .size(35.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_remove),
                                    contentDescription = "Remove",
                                    tint = Color.White,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                text = "$quantity",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            IconButton(
                                onClick = {
                                    cartItem?.let {
                                        cartViewModel.addToCart(
                                            it.copy(quantity = it.quantity + 1),
                                            onShowClearCartDialog = {} // same shop, so no conflict
                                        )
                                    }
                                },
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(Color(0xFFCD113B))
                                    .size(35.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = android.R.drawable.ic_input_add),
                                    contentDescription = "Add",
                                    tint = Color.White,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                    } else {
                        // When adding an item
                        IconButton(
                            onClick = {
                                // Create a new CartItem
                                val newItem = CartItem(
                                    foodId = product.id,
                                    name = product.name,
                                    quantity = 1,
                                    price = product.price,
                                    sellPrice = product.sellPrice,
                                    imageUrl = product.imageUrl,
                                    shopId = shopId,
                                    shopName = shopName,
                                    shopLogo = shopLogo,
                                    deliveryCharge = deliveryCharge,
                                    allowMealOrder = allowMealOrder,
                                    allowDine = allowDine
                                )
                                cartViewModel.addToCart(newItem, onShowClearCartDialog = {
                                    // If item belongs to another shop, trigger dialog
                                    pendingCartItem = newItem
                                    showClearCartDialog = true
                                })
                                // If added successfully (same shop), the LaunchedEffect will update quantity
                            },
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(Color(0xFFCD113B))
                                .size(35.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = android.R.drawable.ic_input_add),
                                contentDescription = "Add",
                                tint = Color.White,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(2.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(product.name, fontSize = 14.sp, fontWeight = FontWeight.Bold, maxLines = 1)
                    Row(
                        modifier = Modifier.offset(y = -3.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "৳ ${product.sellPrice}",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFD32F2F)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            "৳ ${product.price}",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF838383),
                            style = TextStyle(textDecoration = TextDecoration.LineThrough),
                            modifier = Modifier.offset(y = 1.dp)
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun CategorizedFoodList(
    product: List<Food>,
    cartViewModel: CartViewModel,
    shopId: String,
    shopName: String,
    shopLogo: String,
    deliveryCharge: Double,
    allowMealOrder: Boolean,
    allowDine: Boolean,
    onProductClick: () -> Unit
) {
    val groupedFoodList = product.groupBy { it.category.name }
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        groupedFoodList.forEach { (categoryName, categoryFoods) ->
            Text(
                text = categoryName,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 5.dp).padding(top = 3.dp)
            )
            categoryFoods.forEach { food ->
                ProductCell(
                    product = food,
                    cartViewModel = cartViewModel,
                    shopId = shopId,
                    shopName = shopName,
                    shopLogo = shopLogo,
                    deliveryCharge = deliveryCharge,
                    allowMealOrder = allowMealOrder,
                    allowDine = allowDine, onProductClick = onProductClick
                )
            }
        }
    }
}

@Composable
fun ProductCell(
    product: Food,
    cartViewModel: CartViewModel,
    shopId: String,
    shopName: String,
    shopLogo: String,
    deliveryCharge: Double,
    allowMealOrder: Boolean,
    allowDine: Boolean,
    onProductClick: () -> Unit
) {
    var quantity by remember { mutableStateOf(0) }
    var showQuantitySelector by remember { mutableStateOf(false) }

    // Holds the item we tried to add when there's a shop conflict
    var pendingCartItem by remember { mutableStateOf<CartItem?>(null) }
    // Controls whether the clear-cart dialog is visible
    var showClearCartDialog by remember { mutableStateOf(false) }

    val cartItems by cartViewModel.cartItems.collectAsState(initial = emptyList())
    val cartItem = cartItems.find { it.foodId == product.id }

    LaunchedEffect(cartItem) {
        quantity = cartItem?.quantity ?: 0
        showQuantitySelector = quantity > 0
        if (showQuantitySelector) {
            // Auto-hide the quantity selector after 3 seconds
            delay(3000)
            showQuantitySelector = false
        }
    }

    // Show dialog if there's a pending item from a different shop
    if (showClearCartDialog && pendingCartItem != null) {
        AlertDialog(
            onDismissRequest = { showClearCartDialog = false },
            title = { Text("Clear Cart?") },
            text = {
                Text("Your cart contains items from another shop. " +
                        "Do you want to clear the cart and add items from this shop?")
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
        modifier = Modifier.clickable { onProductClick() }
            .fillMaxSize()
            .height(120.dp)
            .padding(bottom = 4.dp)
            .padding(horizontal = 16.dp)
            .shadow(elevation = 3.dp, shape = RoundedCornerShape(5.dp)),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(5.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),

    ) {
        Row {
            // Product Image
            Box(
                modifier = Modifier
                    .height(120.dp)
                    .width(125.dp)
                    .clip(RoundedCornerShape(5.dp))
            ) {
                AsyncImage(
                    model = product.imageUrl,
                    contentDescription = product.name,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                )

                // Quantity Selector or "Add" Button over the image
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(horizontal = 8.dp, vertical = 8.dp)
                        .background(Color.White, shape = RoundedCornerShape(25.dp))
                ) {
                    if (showQuantitySelector) {
                        // If item is already in the cart
                        Row(
                            modifier = Modifier
                                .background(Color.White, shape = RoundedCornerShape(20.dp))
                                .padding(2.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Decrease quantity button
                            IconButton(
                                onClick = {
                                    if (quantity > 1) {
                                        cartViewModel.addToCart(
                                            cartItem!!.copy(quantity = quantity - 1),
                                            onShowClearCartDialog = {
                                                // Same shop conflict logic is not triggered here
                                                // since it's the same item.
                                            }
                                        )
                                    } else {
                                        cartViewModel.removeFromCart(cartItem!!)
                                        showQuantitySelector = false
                                    }
                                },
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(Color(0xFFCD113B))
                                    .size(28.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_remove),
                                    contentDescription = "Remove",
                                    tint = Color.White,
                                    modifier = Modifier.size(18.dp)
                                )
                            }

                            Spacer(modifier = Modifier.weight(1f))

                            // Quantity text
                            Text(
                                text = "$quantity",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )

                            Spacer(modifier = Modifier.weight(1f))

                            // Increase quantity button
                            IconButton(
                                onClick = {
                                    cartItem?.let {
                                        cartViewModel.addToCart(
                                            it.copy(quantity = it.quantity + 1),
                                            onShowClearCartDialog = {
                                                // If different shop conflict is detected
                                                pendingCartItem = it.copy(quantity = it.quantity + 1)
                                                showClearCartDialog = true
                                            }
                                        )
                                    }
                                },
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(Color(0xFFCD113B))
                                    .size(28.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = android.R.drawable.ic_input_add),
                                    contentDescription = "Add",
                                    tint = Color.White,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                    } else {
                        // If item is not in the cart, show "Add" button
                        IconButton(
                            onClick = {
                                val newItem = CartItem(
                                    foodId = product.id,
                                    name = product.name,
                                    quantity = 1,
                                    price = product.price,
                                    sellPrice = product.sellPrice,
                                    imageUrl = product.imageUrl,
                                    shopId = shopId,
                                    shopName = shopName,
                                    shopLogo = shopLogo,
                                    deliveryCharge = deliveryCharge,
                                    allowMealOrder = allowMealOrder,
                                    allowDine = allowDine
                                )
                                cartViewModel.addToCart(
                                    newItem,
                                    onShowClearCartDialog = {
                                        // Different shop conflict triggered
                                        pendingCartItem = newItem
                                        showClearCartDialog = true
                                    }
                                )
                                showQuantitySelector = true
                            },
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(Color(0xFFCD113B))
                                .size(28.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = android.R.drawable.ic_input_add),
                                contentDescription = "Add",
                                tint = Color.White,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.width(10.dp))

            // Product Info
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
            ) {
                Text(product.name, fontSize = 16.sp, fontWeight = FontWeight.Bold, maxLines = 1)
                product.description?.let {
                    Text(
                        it,
                        fontSize = 12.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Row(
                    modifier = Modifier.padding(bottom = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "৳ ${product.sellPrice}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFD32F2F)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        "৳ ${product.price}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF838383),
                        style = TextStyle(textDecoration = TextDecoration.LineThrough)
                    )
                }
            }
        }
    }
}


@Composable
fun ProductDetailsBottomSheet(
    product: Food,
    cartViewModel: CartViewModel,
    onClose: () -> Unit
) {
    val redColor = colorResource(R.color.customRed)
    val cartItems by cartViewModel.cartItems.collectAsState(initial = emptyList())
    val existingCartItem = cartItems.find { it.foodId == product.id }
    var quantity by remember { mutableStateOf(existingCartItem?.quantity ?: 1) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = product.imageUrl,
                contentDescription = product.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            IconButton(
                onClick = { onClose() },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .size(32.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close",
                    tint = Color.Black,
                    modifier = Modifier.size(18.dp)
                )
            }
        }


        Spacer(modifier = Modifier.height(12.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(16.dp)
        ) {
            // Product Title and Description
            Text(
                text = product.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            product.description?.let {
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = it,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "$quantity item",
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Price Row
                Text(
                    text = "৳ ${product.sellPrice * quantity}",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFE00101)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "৳ ${product.price}",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.LightGray,
                    style = TextStyle(textDecoration = TextDecoration.LineThrough)
                )

                Spacer(modifier = Modifier.weight(1f))


                Row(
                    modifier = Modifier
                        .background(Color.Red, shape = RoundedCornerShape(20.dp))
                        .padding(3.dp),
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = {
                            if (quantity > 1) quantity--
                        },
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(Color.White)
                            .size(35.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_remove),
                            contentDescription = "Decrease",
                            tint = Color(0xFFE00101),
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    // Current quantity
                    Text(
                        text = "$quantity",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 5.dp)
                    )

                    IconButton(
                        onClick = { quantity++ },
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(Color.White)
                            .size(35.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = android.R.drawable.ic_input_add),
                            contentDescription = "Increase",
                            tint = Color(0xFFE00101),
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }


            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val newCartItem = CartItem(
                        foodId = product.id,
                        name = product.name,
                        quantity = quantity,
                        price = product.price,
                        sellPrice = product.sellPrice,
                        imageUrl = product.imageUrl,
                        shopId = product.shopId, // Use appropriate shop fields
                        shopName = product.shop.name,
                        shopLogo = product.shop.logo,
                        deliveryCharge = product.shop.deliveryCharge,
                        allowMealOrder = product.shop.allowMealOrder,
                        allowDine = product.shop.allowDine
                    )
                    cartViewModel.addToCart(newCartItem, onShowClearCartDialog = {})
                    onClose()  // Close the bottom sheet after adding to cart
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE00101)),
                shape = RoundedCornerShape(6.dp)
            ) {
                Text(
                    text = "Add to cart",
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
        }
    }
}









/*

@Composable
fun ProductCard(
    product: Food,
    cartViewModel: CartViewModel,
    onShowClearCartDialog: () -> Unit,
    cartShopId: String?
) {
    Card(
        modifier = Modifier
            .height(210.dp)
            .padding(bottom = 4.dp)
            .shadow(elevation = 3.dp, shape = RoundedCornerShape(5.dp)),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(5.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier.fillMaxWidth().height(160.dp).clip(RoundedCornerShape(5.dp))
            ) {
                AsyncImage(
                    model = product.imageUrl,
                    contentDescription = product.name,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )
            }
            Spacer(modifier = Modifier.height(2.dp))

            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(product.name, fontSize = 14.sp, fontWeight = FontWeight.Bold, maxLines = 1)
                    Text("৳ ${product.sellPrice}", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFFD32F2F))
                }
            }

            Button(
                onClick = {
                    if (cartShopId != null && cartShopId != product.shop.id) {
                        onShowClearCartDialog()
                    } else {
                        cartViewModel.addToCart(product.toCartItem(), {})
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Add to Cart")
            }
        }
    }
}

@Composable
fun CategorizedFoodList(
    product: List<Food>,
    cartViewModel: CartViewModel,
    cartShopId: String?,
    onShowClearCartDialog: (Food) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(product) { food ->
            ProductCell(food, cartViewModel, cartShopId, { onShowClearCartDialog(food) })
        }
    }
}

@Composable
fun ProductCell(
    product: Food,
    cartViewModel: CartViewModel,
    cartShopId: String?,
    onShowClearCartDialog: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .shadow(elevation = 3.dp, shape = RoundedCornerShape(5.dp)),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(5.dp)
    ) {
        Row(modifier = Modifier.padding(10.dp)) {
            AsyncImage(
                model = product.imageUrl,
                contentDescription = product.name,
                modifier = Modifier.size(90.dp).clip(RoundedCornerShape(5.dp)),
                contentScale = ContentScale.FillBounds
            )

            Spacer(modifier = Modifier.width(10.dp))

            Column {
                Text(product.name, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Text("৳ ${product.sellPrice}", fontSize = 14.sp, color = Color(0xFFD32F2F))

                Button(onClick = { cartViewModel.addToCart(product.toCartItem(), {}) }) {
                    Text("Add to Cart")
                }
            }
        }
    }
}

 */

