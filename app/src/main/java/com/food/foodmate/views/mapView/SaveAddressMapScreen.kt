package com.food.foodmate.views.mapView.googleMap

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.food.foodmate.R
import com.food.foodmate.utility.AddressData
import com.food.foodmate.utility.DataStoreManager
import com.food.foodmate.viewModel.AuthViewModel
import com.food.foodmate.views.customViews.pageHeader.PageHeader
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.launch

@SuppressLint("MissingPermission")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SaveAddressMapScreen(
    navController: NavController,
    authViewModel: AuthViewModel = hiltViewModel()
) {
    val dataStoreManager = authViewModel.dataStoreManager
    val redColor = colorResource(R.color.customRed)
    val context = LocalContext.current
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    val coroutineScope = rememberCoroutineScope()

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(23.8103, 90.4125), 12f) // Default: Dhaka
    }
    var currentLocation by remember { mutableStateOf<LatLng?>(null) }


    LaunchedEffect(Unit) {
        try {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                location?.let {
                    currentLocation = LatLng(it.latitude, it.longitude)
                    coroutineScope.launch {
                        cameraPositionState.animate(
                            update = CameraUpdateFactory.newLatLngZoom(currentLocation!!, 15f)
                        )
                    }
                }
            }
        } catch (e: Exception) {
            Log.e("SaveAddressMapScreen", "Error: ${e.localizedMessage}")
        }
    }

    // Bottom sheet state and visibility flag
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )
    var showSheet by remember { mutableStateOf(false) }

    // Show or hide the bottom sheet whenever showSheet changes
    LaunchedEffect(showSheet) {
        if (showSheet) {
            sheetState.show()
        } else {
            sheetState.hide()
        }
    }

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {
            currentLocation?.let { latLng ->
                ConfirmLocationBottomSheet(
                    latLng = latLng,
                    dataStoreManager = dataStoreManager,
                    onClose = { showSheet = false }
                )
            } ?: Spacer(modifier = Modifier.height(1.dp))
        },
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
    ) {
        // Main screen content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            PageHeader(title = "Save Address") { navController.popBackStack() }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                GoogleMap(
                    modifier = Modifier.fillMaxSize(),
                    cameraPositionState = cameraPositionState,
                    properties = MapProperties(isMyLocationEnabled = true),
                    onMapClick = { latLng ->
                        currentLocation = latLng
                    }
                ) {
                    currentLocation?.let { location ->
                        Marker(
                            state = MarkerState(position = location),
                            title = "Current Location"
                        )
                    }
                }

                Button(
                    onClick = {
                        if (currentLocation != null) {
                            showSheet = true
                        }
                    },
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(horizontal = 16.dp, vertical = 10.dp)
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = redColor),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text("Confirm Location", fontSize = 16.sp, color = Color.White)
                }
            }
        }
    }
}


@Composable
fun ConfirmLocationBottomSheet(
    latLng: LatLng,
    dataStoreManager: DataStoreManager,
    onClose: () -> Unit
) {
    val redColor = colorResource(R.color.customRed)
    val scope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    var street by remember { mutableStateOf("") }
    var postCode by remember { mutableStateOf("") }
    var apartment by remember { mutableStateOf("") }
    var label by remember { mutableStateOf("Home") }
    var fullAddress by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
    ) {
        // Top Row: Title and Close Button
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Address",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { onClose() }) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close",
                    tint = Color.Black
                )
            }
        }

        Text("Latitude: ${latLng.latitude}, Longitude: ${latLng.longitude}", fontSize = 14.sp)

        // Optionally, if you want to hide the keyboard when the user presses "Done" on the address field:
        OutlinedTextField(
            value = fullAddress,
            onValueChange = { fullAddress = it },
            label = { Text("Full Address") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                    keyboardController?.hide()
                }
            )
        )

        Spacer(modifier = Modifier.height(8.dp))
        // Street Field
        OutlinedTextField(
            value = street,
            onValueChange = { street = it },
            label = { Text("Street") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                    keyboardController?.hide()
                }
            )
        )

        Spacer(modifier = Modifier.height(8.dp))
        // Post Code Field
        OutlinedTextField(
            value = postCode,
            onValueChange = { postCode = it },
            label = { Text("Post Code") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                    keyboardController?.hide()
                }
            )
        )

        Spacer(modifier = Modifier.height(8.dp))
        // Apartment Field
        OutlinedTextField(
            value = apartment,
            onValueChange = { apartment = it },
            label = { Text("Apartment") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                    keyboardController?.hide()
                }
            )
        )

        Spacer(modifier = Modifier.height(8.dp))
        // Label Selection Row
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            listOf("Home", "Work", "Other").forEach { option ->
                val isSelected = (option == label)
                Button(
                    onClick = { label = option },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isSelected) Color(0xFFFFE8E8) else Color.White
                    ),
                    border = BorderStroke(
                        1.dp,
                        if (isSelected) Color.Red else Color.LightGray
                    ),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = option,
                        color = if (isSelected) Color.Red else Color.Black
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Save Location Button
        Button(
            onClick = {
                val addressData = AddressData(
                    latitude = latLng.latitude,
                    longitude = latLng.longitude,
                    label = label,
                    street = street,
                    postCode = postCode,
                    apartment = apartment,
                    fullAddress = fullAddress
                )

                scope.launch {
                    dataStoreManager.addAddress(addressData)
                    onClose() // Close bottom sheet after saving
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = redColor)
        ) {
            Text("Save Location", fontSize = 16.sp)
        }
    }
}










