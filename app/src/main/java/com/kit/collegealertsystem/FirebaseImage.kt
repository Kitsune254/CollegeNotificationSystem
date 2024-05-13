import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberImagePainter
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

// Composable function to display an image from Firebase Storage
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun FirebaseImage(imagePath: String, modifier: Modifier = Modifier) {
    // State variable to hold the image URL
    val imageUrl = remember { mutableStateOf<String?>(null) }

    // Coroutine scope for background tasks
    val coroutineScope = rememberCoroutineScope()

    // Load image URL asynchronously
    coroutineScope.launch(Dispatchers.IO) {
        try {
            val storageRef = Firebase.storage.reference
            val imageRef = storageRef.child(imagePath)
            val url = imageRef.downloadUrl.await().toString()

            // Update the imageUrl state with the retrieved URL
            imageUrl.value = url
        } catch (e: Exception) {
            e.printStackTrace()
            // Handle error if needed
        }
    }

    // Display the image using Image composable when imageUrl is not null
    imageUrl.value?.let { url ->
        Image(
            painter = rememberImagePainter(
                data = url,
                // You can specify additional options here, such as transformations or placeholders
            ),
            contentDescription = null, // Provide appropriate content description
            modifier = modifier,
            contentScale = ContentScale.Fit
        )
    }
}

