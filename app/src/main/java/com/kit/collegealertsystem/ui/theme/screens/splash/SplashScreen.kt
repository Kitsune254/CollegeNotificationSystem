package com.kit.collegealertsystem.ui.theme.screens.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.kit.collegealertsystem.R
import com.kit.collegealertsystem.navigation.ROUTE_CLUBS
import com.kit.collegealertsystem.navigation.ROUTE_HOME
import com.kit.collegealertsystem.navigation.ROUTE_LOGIN
import com.kit.collegealertsystem.navigation.ROUTE_REGISTER
import com.kit.collegealertsystem.navigation.ROUTE_SPORT
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    val scale = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.7f,
            animationSpec = tween(
                durationMillis = 400,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                }
            )
        )
        delay(3000L)
        navController.navigate(ROUTE_LOGIN)
    }

    // AnimationEffect


    // Image
    Box(modifier = Modifier.fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = R.drawable.kit),
            contentDescription = "Logo",
            modifier = Modifier
                .scale(scale.value)
                .fillMaxSize()

        )

    }
}

