package com.food.foodmate.views.customViews.drawerContent

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.food.foodmate.R
import com.food.foodmate.utility.DataStoreManager
import com.food.foodmate.viewModel.AuthViewModel


@Composable
fun DrawerContent(viewModel: AuthViewModel, navController: NavController, onCloseDrawer: () -> Unit = {}) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val drawerWidth: Dp = screenWidth * 0.80f

    val context = LocalContext.current
    val dataStoreManager = remember { DataStoreManager(context) }

    val isLoggedIn = dataStoreManager.getIsLoggedIn().collectAsState(initial = false).value
    val userName by viewModel.userName.collectAsState(initial = "Guest")
    val userBio by viewModel.userBio.collectAsState(initial = "Not logged in")
    val userEmail by viewModel.userEmail.collectAsState(initial = "Not logged in")
    val userProfile by viewModel.userProfile.collectAsState(initial = null)


    Row(modifier = Modifier.fillMaxHeight()) {
        Box(
            modifier = Modifier
                .width(drawerWidth)
                .fillMaxHeight()
                .background(Color.White)
                .padding(16.dp)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFF9F9F9)),
                        contentAlignment = Alignment.Center
                    ) {
                        IconButton(onClick = onCloseDrawer) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_back),
                                contentDescription = "Close Drawer",
                                modifier = Modifier
                                    .size(18.dp),
                                tint = Color.Black
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(10.dp))

                    Text("Profile", fontSize = 20.sp, color = Color.Black, fontWeight = FontWeight.Bold)
                }


                Spacer(modifier = Modifier.height(2.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 16.dp)
                ) {
                    if (!userProfile.isNullOrEmpty()) {
                        AsyncImage(
                            model = userProfile,
                            contentDescription = "Profile Picture",
                            modifier = Modifier.size(80.dp).clip(CircleShape)
                        )
                    } else {
                        Image(
                            painter = painterResource(id = R.drawable.ic_vector_person),
                            contentDescription = "Profile Image",
                            modifier = Modifier
                                .size(72.dp)
                                .clip(RoundedCornerShape(50)),
                            contentScale = ContentScale.Crop
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    if (isLoggedIn) {
                        Column {
                            Text(userName, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                            Text(userBio, color = Color.Gray, fontSize = 14.sp)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
                Divider(color = Color.LightGray, thickness = 1.dp)
                Spacer(modifier = Modifier.height(10.dp))

//                DrawerMenuItem(title = "Orders", icon = R.drawable.ic_cart) {}
                if (isLoggedIn) {
                    DrawerMenuItem(
                        title = "Personal Info",
                        icon = R.drawable.ic_person,
                        navController = navController
                    ) {
                        navController.navigate("personalInfoView")

                    }
                    DrawerMenuItem(
                        title = "Addresses",
                        icon = R.drawable.ic_map,
                        navController = navController
                    ) {
                        navController.navigate("addressList")
                    }
                    /*
                    DrawerMenuItem(
                        title = "Payment Method",
                        icon = R.drawable.ic_payment,
                        navController = navController
                    ) { }
                    DrawerMenuItem(
                        title = "Favourite",
                        icon = R.drawable.ic_favorite,
                        navController = navController
                    ) { }

                     */
                }

                DrawerMenuItem(
                    title = "Notifications",
                    icon = R.drawable.ic_notification,
                    navController = navController
                ) { }
                DrawerMenuItem(
                    title = "Contact Us",
                    icon = R.drawable.ic_contact_us,
                    navController = navController
                ) {
                    navController.navigate("contactUsView")

                }
                DrawerMenuItem(
                    title = "Privacy Policy",
                    icon = R.drawable.ic_policy,
                    navController = navController
                ) {
                    navController.navigate("privacyPolicyView")

                }

                Spacer(modifier = Modifier.weight(1f))

                if (isLoggedIn) {
                    DrawerMenuItem(
                        title = "Logout",
                        icon = R.drawable.ic_logout,
                        isDestructive = true,
                        navController = navController
                    ) {
                        viewModel.logout()

                    }
                } else {
                    DrawerMenuItem(
                        title = "Login",
                        icon = R.drawable.ic_logout,
                        isDestructive = false,
                        navController = navController
                    ) {
                        navController.navigate("login")
                    }
                }
//                DrawerMenuItem(title = "Login", icon = R.drawable.ic_logout, isDestructive = true, navController = navController) { }

                Spacer(modifier = Modifier.padding(bottom = 25.dp))
            }
        }

        Spacer(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewDrawerContent() {
//    val navController = rememberNavController()
//    DrawerContent(
//        viewModel: AuthViewModel(),
//        navController = navController,
//        onCloseDrawer = { }
//    )
//}

@Composable
fun DrawerMenuItem(title: String, icon: Int, isDestructive: Boolean = false, navController: NavController, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(if (isDestructive) Color(0xFFFFEBEE) else Color(0xFFF9F9F9))
            .padding(horizontal = 16.dp)
            .padding(vertical = 12.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        val iconPainter = painterResource(id = icon)

        // Ensure the icon is correctly loaded and displayed without tint issues
        Icon(
            painter = iconPainter,
            contentDescription = title,
            modifier = Modifier
                .size(24.dp)
                .graphicsLayer {
                    rotationY =  if (isDestructive) 180f else 0f // Horizontal flip
                },
            tint = Color.Unspecified // Prevents default tinting that causes black circle issue
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = if (isDestructive) Color.Red else Color.Black
        )

        Spacer(modifier = Modifier.weight(1f))

        Icon(
            painter = painterResource(id = R.drawable.ic_forward),
            contentDescription = "Go Forward",
                    modifier = Modifier
                        .size(14.dp)
        )
    }
}