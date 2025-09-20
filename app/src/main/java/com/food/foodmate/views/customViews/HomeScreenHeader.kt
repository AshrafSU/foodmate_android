package com.food.foodmate.views.customViews

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.food.foodmate.R
import com.food.foodmate.viewModel.AuthViewModel

@Composable
fun HomeScreenHeader(navController: NavController, authViewModel: AuthViewModel = hiltViewModel(), onMenuClick: () -> Unit) {
    val dataStoreManager = authViewModel.dataStoreManager
    val addressList by dataStoreManager.getAddressList().collectAsState(initial = emptyList())
    val addressText = if (addressList.isNotEmpty()) {
        addressList[0].fullAddress
    } else {
        "Current Location"
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(colorResource(id = R.color.customRed))
                    .clickable {
                        onMenuClick()
                               },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_menu),
                    contentDescription = "Menu",
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier.clickable { navController.navigate("googleMap") }
            ){
                Text("DELIVER TO", fontSize = 12.sp, color = colorResource(id = R.color.redColor), fontWeight = FontWeight.Bold)
                Text(addressText, fontSize = 14.sp)
            }
        }

        Icon(
            painter = painterResource(id = R.drawable.ic_dropdown),
            contentDescription = "Dropdown",
            modifier = Modifier.size(24.dp).clickable { navController.navigate("googleMap") }
        )
    }
}