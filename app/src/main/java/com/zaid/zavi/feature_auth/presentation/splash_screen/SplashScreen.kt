package com.zaid.zavi.feature_auth.presentation.splash_screen

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zaid.zavi.R
import com.zaid.zavi.theme.ZaviDecorTheme


@Composable
fun SplashScreen() {

    var targetSize by remember { mutableStateOf(150.dp) }

    LaunchedEffect(Unit) {
        targetSize = 200.dp
    }

    val logoSize by animateDpAsState(
        targetValue = targetSize,
        animationSpec = tween(
            durationMillis = 2000,
            easing = LinearOutSlowInEasing
        ), label = ""
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "")
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null,
            modifier = Modifier
                .clip(shape = CircleShape)
                .size(logoSize)
        )
    }
}

@Composable
@Preview
fun SplashScreenPreview() {
    ZaviDecorTheme {
        SplashScreen()
    }
}