package com.food.foodmate.views.mapView.addressList

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.food.foodmate.R
import com.food.foodmate.utility.AddressData
import com.food.foodmate.viewModel.AuthViewModel
import com.food.foodmate.views.customViews.pageHeader.PageHeader
import kotlinx.coroutines.launch

@Composable
fun SaveAddressList(navController: NavController, authViewModel: AuthViewModel = hiltViewModel()) {
    val dataStoreManager = authViewModel.dataStoreManager
    val addressList by dataStoreManager.getAddressList().collectAsState(initial = emptyList())
    val redColor = colorResource(R.color.customRed)
    val scope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize()) {
        PageHeader(title = "Address") { navController.popBackStack() }

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(addressList) { address ->
                AddressCell(
                    address = address,
                    onEditClick = {
                        // TODO: Handle edit action
                    },
                    onDeleteClick = {
                        scope.launch {
                            dataStoreManager.removeAddress(address)
                        }
                    }
                )
            }
        }

        Button(
            onClick = {
                navController.navigate("googleMap")
            },
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 10.dp)
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = redColor),
            shape = RoundedCornerShape(6.dp)
        ) {
            Text("Add new +", fontSize = 16.sp, color = Color.White)
        }
    }
}



@Composable
fun AddressCell(
    address: AddressData,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF8F8F8)),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                // Label
                Text(
                    text = address.label.uppercase(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))

                val combinedAddress = buildString {
                    if (address.apartment.isNotEmpty()) append("${address.apartment}, ")
                    if (address.street.isNotEmpty()) append("${address.street}, ")
                    if (address.fullAddress.isNotEmpty()) append(address.fullAddress)
                    if (address.postCode.isNotEmpty()) append("${address.postCode}, ")
                }.trim().trimEnd(',')

                Text(
                    text = if (combinedAddress.isNotBlank()) combinedAddress else "No address details",
                    fontSize = 13.sp,
                    color = Color.Gray
                )
            }

//            IconButton(onClick = { onEditClick() }) {
//                Icon(
//                    imageVector = Icons.Default.Edit,
//                    contentDescription = "Edit",
//                    tint = Color(0xFFCD113B),
//                    modifier = Modifier.size(20.dp)
//                )
//            }
            IconButton(onClick = { onDeleteClick() }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    tint = Color(0xFFCD113B),
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}
