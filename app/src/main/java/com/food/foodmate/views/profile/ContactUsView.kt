package com.food.foodmate.views.profile.contactUsView

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.food.foodmate.R
import com.food.foodmate.ui.theme.FoodMateTheme
import com.food.foodmate.views.customViews.pageHeader.PageHeader

@Composable
fun ContactUsView(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize()) {
        PageHeader(title = "Contact Us"){ navController.popBackStack() }
        Divider()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(20.dp)
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


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
                        title = "Corporate Office",
                        value = "IIT, University of Dhaka, Suhrawardi Udyan Rd, Dhaka 1200.",
                        iconColor = Color(0xFFE0171D)
                    )
                    Divider(modifier = Modifier.padding(vertical = 15.dp))

                    InfoRow(
                        icon = android.R.drawable.stat_sys_phone_call,
                        title = "Hotline",
                        value = "+880 1912980022",
                        iconColor = Color(0xFFE0171D)
                    )
                    Divider(modifier = Modifier.padding(vertical = 15.dp))

                    InfoRow(
                        icon = android.R.drawable.ic_dialog_email,
                        title = "EMAIL",
                        value = "support@foodmate.com",
                        iconColor = Color(0xFFE0171D)
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ContactUsViewPreview() {
    FoodMateTheme {
        ContactUsView(navController = rememberNavController())
    }
}