package com.food.foodmate.views.profile.editProfileView

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.food.foodmate.R
import com.food.foodmate.viewModel.AuthViewModel
import java.io.File

@SuppressLint("RememberReturnType")
@Composable
fun EditProfileView(viewModel: AuthViewModel, navController: NavHostController) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }
    val selectedImageUri = remember { mutableStateOf<Uri?>(null) }

    val storedUserName by viewModel.userName.collectAsState(initial = "Not Set")
    val storedUserEmail by viewModel.userEmail.collectAsState(initial = "Not Set")
    val storedUserMobile by viewModel.userMobile.collectAsState(initial = "01*******")
    val storedUserBio by viewModel.userBio.collectAsState(initial = "Not update")
    val storedUserProfile by viewModel.userProfile.collectAsState(initial = null)

    // 🔹 Local editable state (for TextFields)
    var newUserName by remember { mutableStateOf(storedUserName) }
    var newUserEmail by remember { mutableStateOf(storedUserEmail) }
    var newUserMobile by remember { mutableStateOf(storedUserMobile) }
    var newUserBio by remember { mutableStateOf(storedUserBio) }

    LaunchedEffect(storedUserName, storedUserEmail, storedUserMobile, storedUserBio) {
        newUserName = storedUserName
        newUserEmail = storedUserEmail
        newUserMobile = storedUserMobile
        newUserBio = storedUserBio
    }

    val launcherGallery = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let { selectedImageUri.value = it }
    }

    val launcherCamera = rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
        bitmap?.let {
            val imageUri = saveBitmapToCache(context, it)
            selectedImageUri.value = imageUri
        }
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            launcherCamera.launch()
        } else {
            Toast.makeText(context, "Camera permission denied", Toast.LENGTH_SHORT).show()
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text("Edit Profile", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF8F8F8))
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Profile Image with Edit Button
            Box(contentAlignment = Alignment.BottomEnd) {
                AsyncImage(
                    model = selectedImageUri.value ?: storedUserProfile ?: R.drawable.ic_vector_person,
                    contentDescription = "Profile Picture",
                    modifier = Modifier.size(90.dp).clip(CircleShape).background(Color.LightGray),
                    contentScale = ContentScale.Crop
                )
                IconButton(
                    onClick = { showDialog = true },
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color(0xFFFF7622))
                        .size(32.dp)
                ) {
                    Icon(
                        painter = painterResource(id = android.R.drawable.ic_menu_edit),
                        contentDescription = "Edit Picture",
                        tint = Color.White,
                        modifier = Modifier.size(15.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = { Text("Choose Option") },
                    text = { Text("Select an image from Camera or Gallery") },
                    confirmButton = {
                        Button(onClick = {
                            showDialog = false
                            if (ContextCompat.checkSelfPermission(
                                    context,
                                    Manifest.permission.CAMERA
                                ) == PackageManager.PERMISSION_GRANTED
                            ) {
                                launcherCamera.launch()
                            } else {
                                permissionLauncher.launch(Manifest.permission.CAMERA)
                            }
                        }) {
                            Text("Camera")
                        }
                    },
                    dismissButton = {
                        Button(onClick = {
                            showDialog = false
                            launcherGallery.launch("image/*")
                        }) {
                            Text("Gallery")
                        }
                    }
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Editable Fields
            Column {
                Text(
                    "FULL NAME",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.SemiBold
                )
                OutlinedTextField(
                    value = newUserName,
                    onValueChange = { newUserName = it },
                    label = { Text("Name") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(
                        onDone = { keyboardController?.hide() } // Hide keyboard when Done is pressed
                    ),
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    "EMAIL",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.SemiBold
                )
                OutlinedTextField(
                    value = newUserEmail,
                    onValueChange = { newUserEmail = it },
                    label = { Text("Email") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(
                        onDone = { keyboardController?.hide() } // Hide keyboard when Done is pressed
                    ),
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    "PHONE NUMBER",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.SemiBold
                )
                OutlinedTextField(
                    value = newUserMobile,
                    onValueChange = { newUserMobile = it },
                    label = { Text("Phone Number") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone, imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(
                        onDone = { keyboardController?.hide() } // Hide keyboard when Done is pressed
                    ),
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    "BIO",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.SemiBold
                )
                OutlinedTextField(
                    value = newUserBio,
                    onValueChange = { newUserBio = it },
                    label = { Text("Write Bio") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(
                        onDone = { keyboardController?.hide() } // Hide keyboard when Done is pressed
                    ),
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    viewModel.updateUserProfile(
                        name = if (newUserName != storedUserName) newUserName else null,
                        email = if (newUserEmail != storedUserEmail) newUserEmail else null,
                        mobileNumber = if (newUserMobile != storedUserMobile) newUserMobile else null,
                        bioDescription = if (newUserBio != storedUserBio) newUserBio else null,
                        profilePicture = selectedImageUri.value?.toString(),
                        onSuccess = { navController.popBackStack() } // ✅ Navigate back on success
                    )
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F)),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth().height(48.dp)
            ) {
                Text("SUBMIT", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

fun saveBitmapToCache(context: Context, bitmap: Bitmap): Uri {
    val timeStamp = System.currentTimeMillis() // Unique filename
    val file = File(context.cacheDir, "profile_picture_$timeStamp.jpg")

    file.outputStream().use {
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
    }

    val authority = "${context.packageName}.provider"
    return FileProvider.getUriForFile(context, authority, file)
}


