package com.kit.collegealertsystem.ui.theme.screens.registration

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RadialGradientShader
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kit.collegealertsystem.data.AuthViewModel
import com.kit.collegealertsystem.navigation.ROUTE_LOGIN
import com.kit.collegealertsystem.navigation.ROUTE_REGISTER

@Composable
fun RegistrationScreen(navController: NavHostController) {
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var pass by remember { mutableStateOf(TextFieldValue("")) }
    //var name by remember { mutableStateOf(TextFieldValue("")) }
    var confirmpass by remember { mutableStateOf(TextFieldValue("")) }
    var context= LocalContext.current
    val largeRadialGradient = object : ShaderBrush() {
        override fun createShader(size: Size): Shader {
            val biggerDimension = maxOf(size.height, size.width)
            return RadialGradientShader(
                colors = listOf(Color(0xFF2be4dc), Color(0xFF243484)),
                center = size.center,
                radius = biggerDimension / 2f,
                colorStops = listOf(0f, 0.95f)
            )
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(largeRadialGradient)
    )
    Column(modifier = Modifier
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(text = "Sign Up",
            color = Color.Black,
            fontFamily = FontFamily.SansSerif,
            fontSize = 40.sp
        )

//        Spacer(modifier = Modifier.height(20.dp))

//        OutlinedTextField(
//            value = name,
//            onValueChange = { name = it },
//            leadingIcon = {
//                Icon(
//                    imageVector = Icons.Default.Person,
//                    contentDescription = "email"
//                )
//            },
//            label = { Text(
//                text = "Enter Full Name",
//                fontFamily = FontFamily.SansSerif,
//                color = Color.Black,
//                fontSize = 20.sp,
//                fontWeight = FontWeight.SemiBold
//            ) },
//
//            keyboardOptions = KeyboardOptions . Default . copy (imeAction = ImeAction.Next),
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(8.dp),
//        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "email"
                )
            },
            label = { Text(
                text = "Enter Email",
                fontFamily = FontFamily.SansSerif,
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            ) },

            keyboardOptions = KeyboardOptions . Default . copy (imeAction = ImeAction.Next),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value =pass ,
            onValueChange = {pass=it},
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "email"
                )
            },
            label = { Text(
                text = "Enter password",
                fontFamily = FontFamily.SansSerif,
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            ) },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value =confirmpass ,
            onValueChange = {
            confirmpass=it},
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "email"
                )
            },
            label = { Text(
                text = "Enter Confirm Pass",
                fontFamily = FontFamily.SansSerif,
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            ) },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            val myregister=
                AuthViewModel(navController,context)
            myregister.signup(email.text.trim(),pass.text.trim(),confirmpass.text.trim())

        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Register ")
        }

        Spacer(modifier = Modifier.height(20.dp))

//        Button(onClick = {
//            navController.navigate(ROUTE_LOGIN)
//        }, modifier = Modifier.fillMaxWidth()) {
//            Text(text = "Have an Account? Click to Login")
//        }
        Row {
            Text(text = "Have an account?",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.width(50.dp))

            Text(text = "Login ",
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                modifier = Modifier
                    .clickable{
                        navController.navigate(ROUTE_LOGIN)
                    }
            )
        }
    }
}

@Preview
@Composable
fun Register() {
    RegistrationScreen(rememberNavController())
}
