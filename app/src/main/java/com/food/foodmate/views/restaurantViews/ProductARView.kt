//package com.food.foodmate.views.restaurantViews
//
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import io.github.sceneview.Scene
//import io.github.sceneview.math.Position
//import io.github.sceneview.node.ModelNode
//import io.github.sceneview.rememberCameraNode
//import io.github.sceneview.rememberEngine
//import io.github.sceneview.rememberEnvironmentLoader
//import io.github.sceneview.rememberModelLoader
//
//@Composable
//fun ARScreen() {
//    // Create the AR engine and loaders.
//    val engine = rememberEngine()
//    val modelLoader = rememberModelLoader(engine)
//    val environmentLoader = rememberEnvironmentLoader(engine)
//
//    // Create and configure a camera node.
//    val cameraNode = rememberCameraNode(engine).apply {
//        // Position the camera 4 units away along the z-axis.
//        position = Position(0.0f, 0.0f, 4.0f)
//    }
//
//    // Build the AR scene.
//    Scene(
//        modifier = Modifier.fillMaxSize(),
//        engine = engine,
//        cameraNode = cameraNode,
//        modelLoader = modelLoader,
//        // Load an HDR environment (adjust the path as needed).
//        environment = environmentLoader.createHDREnvironment("environments/sky_2k.hdr")!!,
//        // List of child nodes added to the scene.
//        childNodes = listOf(
//            // Add your 3D model node.
//            ModelNode(
//                modelInstance = modelLoader.createModelInstance("models/damaged_helmet.glb"),
//                scaleToUnits = 1.0f
//            )
//        )
//    )
//}
//
//
//
