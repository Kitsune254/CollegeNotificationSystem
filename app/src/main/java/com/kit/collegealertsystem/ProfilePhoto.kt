package com.kit.collegealertsystem

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// Composable function to display a profile photo from Firebase Storage
@Composable
fun ProfilePhoto(imagePath: String?, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.size(120.dp),
        contentAlignment = Alignment.Center
    ) {
        val imagePainter: Painter =
            rememberAsyncImagePainter(model = imagePath)

        Image(
            painter = imagePainter,
            contentDescription = null, // Provide appropriate content description
            modifier = Modifier.size(120.dp),
        )
    }
}

// Usage:
//@Composable
//fun ProfileScreen() {
//    // Replace "profile_images/user123.jpg" with the actual path to the user's profile photo
//    val imagePath = "profile_images/user123.jpg"
//
//    // Display the profile photo
//    ProfilePhoto(imagePath = imagePath)
//}
