package com.example.composeapp.views.authScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp import androidx.navigation.NavHostController
import com.example.composeapp.appUtils.BottomNavRoutes
import com.example.composeapp.appUtils.Outfit
import com.example.composeapp.viewModel.SessionViewModel
import kotlinx.coroutines.delay
import com.example.composeapp.R
import kotlinx.coroutines.flow.first

@Composable
fun SplashScreen(navController: NavHostController, sessionViewModel: SessionViewModel) {

    LaunchedEffect(Unit) {
        delay(1000)

        val isLoggedIn = sessionViewModel.getUserLoggedIn().first()

        when {
            isLoggedIn -> navController.navigate(BottomNavRoutes.Home.route) {
                popUpTo("splash") { inclusive = true }
            }
            else -> navController.navigate("onboarding") {
                popUpTo("splash") { inclusive = true }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Background image
        Image(
            painter = painterResource(id = R.drawable.btby_theme),
            contentDescription = "btby theme",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        // Logo in the center
        Image(
            painter = painterResource(id = R.drawable.btyb_logo),
            contentDescription = "btby Logo",
            modifier = Modifier
                .align(Alignment.Center)
                .padding(16.dp)
        )

        // Bottom Text
        Text(
            text = "Brought To You By",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 20.dp)
                .windowInsetsPadding(WindowInsets.navigationBars),
            style = TextStyle(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFFFF116A),
                        Color(0xFFFED150)
                    )
                ),
                fontSize = 17.sp,
                fontFamily = Outfit,
                fontWeight = FontWeight.SemiBold
            )
        )
    }
}