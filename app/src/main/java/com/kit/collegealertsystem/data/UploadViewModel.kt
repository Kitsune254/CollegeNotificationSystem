package com.kit.collegealertsystem.data

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.kit.collegealertsystem.models.Upload
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.net.URL
import android.content.Context

@SuppressLint("StaticFieldLeak")
class UploadViewModel: ViewModel() {
    private val storage = FirebaseStorage.getInstance()
    private val storageReference: StorageReference = storage.reference.child("images")

    var imageBitmap: ImageBitmap? by mutableStateOf(null)
    var uploading: Boolean by mutableStateOf(false)
    lateinit var context: Context


    fun uploadImage(userId: String?) {
        userId?.let { uid ->
            viewModelScope.launch {
                try {
                    uploading = true

                    // Sample image from resources
                    val imageStream = javaClass.classLoader?.getResourceAsStream("sample_image.jpg")
                    val imageBytes = imageStream?.readBytes()

                    // Upload the image to Firebase Storage
                    val imageRef = storageReference.child("$uid/profile.jpg")
                    imageBytes?.let { bytes ->
                        imageRef.putBytes(bytes).await()

                        // Download the uploaded image and display it
                        val downloadUrl = imageRef.downloadUrl.await().toString()
                        val downloadedBitmap = withContext(Dispatchers.IO) {
                            val stream = java.net.URL(downloadUrl).openStream()
                            BitmapFactory.decodeStream(stream)
                        }
                        imageBitmap = downloadedBitmap.asImageBitmap()
                    }
                } catch (e: Exception) {
                    // Handle error
                } finally {
                    uploading = false
                }
            }
        }
    }

}