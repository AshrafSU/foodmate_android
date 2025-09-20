package com.food.foodmate.views.profile.personalInfoView

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.food.foodmate.R
import com.food.foodmate.viewModel.AuthViewModel

@Composable
fun PersonalInfoView(viewModel: AuthViewModel, navController: NavHostController) {
    val userName by viewModel.userName.collectAsState(initial = "Guest")
    val userEmail by viewModel.userEmail.collectAsState(initial = "Not logged in")
    val userMobile  by viewModel.userMobile.collectAsState(initial = "Not logged in")
    val userBio by viewModel.userBio.collectAsState(initial = "Not logged in")
    val userProfile by viewModel.userProfile.collectAsState(initial = null)
println("profile: ${userProfile}")
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton( { navController.popBackStack() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text("Personal Info", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                onClick = {
                    navController.navigate("editProfileView")
                },
            ) {
                Text("Edit", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color(0xFFFF7622))
            }
        }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Profile Image
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


//            if (userProfile != null) {
//                AsyncImage(
//                    model = userProfile,
//                    contentDescription = "Profile Picture",
//                    modifier = Modifier.size(80.dp).clip(CircleShape)
//                )
//            }else {
//                Image(
//                    painter = painterResource(id = R.drawable.ic_vector_person),
//                    contentDescription = "Profile Image",
//                    modifier = Modifier
//                        .size(72.dp)
//                        .clip(RoundedCornerShape(50)),
//                    contentScale = ContentScale.Crop
//                )
//            }

            Spacer(modifier = Modifier.height(12.dp))

            // User Name
            Text(userName, fontSize = 20.sp, fontWeight = FontWeight.Bold)

            // User Bio
            Text(userBio, fontSize = 14.sp, color = Color.Gray)

            Spacer(modifier = Modifier.height(20.dp))

            // Personal Info Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF5F7FA)),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F7FA)),
                elevation = CardDefaults.cardElevation(0.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    InfoRow(
                        icon = R.drawable.ic_vector_person,
                        title = "FULL NAME",
                        value = userName,
                        iconColor = Color(0xFFFF5722)
                    )
                    Divider(modifier = Modifier.padding(vertical = 8.dp))

                    InfoRow(
                        icon = android.R.drawable.ic_dialog_email,
                        title = "EMAIL",
                        value = userEmail,
                        iconColor = Color(0xFF3B82F6)
                    )
                    Divider(modifier = Modifier.padding(vertical = 8.dp))

                    InfoRow(
                        icon = android.R.drawable.stat_sys_phone_call,
                        title = "PHONE NUMBER",
                        value = userMobile,
                        iconColor = Color(0xFF00BFA5)
                    )
                }
            }
        }
    }
}

@Composable
fun InfoRow(icon: Int, title: String, value: String, iconColor: Color) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        // Icon Background
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(iconColor.copy(alpha = 0.1f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = title,
                tint = iconColor,
                modifier = Modifier.size(18.dp)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        // Text Information
        Column {
            Text(title, fontSize = 12.sp, color = Color.Gray, fontWeight = FontWeight.SemiBold)
            Text(value, fontSize = 14.sp, fontWeight = FontWeight.Medium)
        }
    }
}
