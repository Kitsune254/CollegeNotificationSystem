package com.kit.collegealertsystem.ui.theme.screens.profile

import android.app.ProgressDialog
import android.content.Context
import android.graphics.drawable.Icon
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.kit.collegealertsystem.ProfilePhoto
import com.kit.collegealertsystem.R
import com.kit.collegealertsystem.data.AuthViewModel
import com.kit.collegealertsystem.data.UploadViewModel

@Composable
fun ProfileHeader(modifier: Modifier,
                  navController: NavHostController) {
    var context= LocalContext.current
    val auth = FirebaseAuth.getInstance()
    val storageViewModel: UploadViewModel = viewModel()
    val imageBitmap: ImageBitmap? = storageViewModel.imageBitmap
    val uploading = storageViewModel.uploading
    BackHandler {
        navController.popBackStack()
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .background(MaterialTheme.colorScheme.secondary)
            .padding()
            .fillMaxWidth()
    ) {
        Row {
            IconButton(
                onClick = {
                    navController.popBackStack()
                },

                ) {

                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back page",

                    )
            }
            Spacer(modifier.width(250.dp))
            IconButton(

                onClick = {
                    val mylogout= AuthViewModel(navController, context )
                    mylogout.logout()
                }
            ) {
                Icon(

                    imageVector = Icons.Default.ExitToApp,
                    contentDescription = "Logout",

                    )
            }
        }
        Row (
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
        ){
            imageBitmap?.let { bitmap ->
                Image(
                    bitmap = bitmap,
                    contentDescription = null,
                    modifier = Modifier.size(200.dp)
                )
            }
            Text(
                text = stringResource(id = R.string.app_name),
                //textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onPrimary,
            )
        }
    }
}
@Composable
fun ProfileScreen(navController: NavHostController,
                  modifier: Modifier = Modifier) {
    val auth = FirebaseAuth.getInstance()
    val storageViewModel: UploadViewModel = viewModel()
    val imageBitmap: ImageBitmap? = storageViewModel.imageBitmap
    val uploading = storageViewModel.uploading
    var context = LocalContext.current
    Scaffold (
        topBar = {
            ProfileHeader(modifier, navController)

        },
        content = {

            Column (Modifier.padding(it),
                horizontalAlignment = Alignment.CenterHorizontally){
                Text(text = "Edit Profile")

                ImagePicker(Modifier,context)
                Button(
                    onClick = { storageViewModel.uploadImage(auth.currentUser?.uid) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    if (uploading) {
                        CircularProgressIndicator()
                    } else {
                        Text("Upload Image")
                    }
                }
            }
        }
    )
}

//@Composable
//fun ProfileScreen() {
//    // Replace "profile_images/user123.jpg" with the actual path to the user's profile photo
//    val imagePath = "profile_images/user123.jpg"
//
//    // Display the profile photo
//    ProfilePhoto(imagePath = imagePath)
//}

@Composable
fun ImagePicker(modifier: Modifier = Modifier, context: Context ) {
    var hasImage by remember { mutableStateOf(false) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }


    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            hasImage = uri != null
            imageUri = uri
        }
    )

    Column(modifier = modifier,) {
        if (hasImage && imageUri != null) {
            val bitmap = MediaStore.Images.Media.
            getBitmap(context.contentResolver,imageUri)
            Image(bitmap = bitmap.asImageBitmap(), contentDescription = "Selected image")
        }
        Column(
            modifier = Modifier.fillMaxWidth().padding(bottom = 32.dp), horizontalAlignment = Alignment.CenterHorizontally,) {
            Button(
                onClick = {
                    imagePicker.launch("image/*")
                },
            ) {
                Text(
                    text = "Select Image"
                )
            }
            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = {
                //-----------WRITE THE UPLOAD LOGIC HERE---------------//
                var productRepository = UploadViewModel()
                productRepository.uploadImage(imageUri!!.toString())

            }
            ) {
                Text(text = "Upload")

            }
        }
    }
}


@Preview
@Composable
private fun ProfilePrev() {
    ProfileScreen(rememberNavController())
}