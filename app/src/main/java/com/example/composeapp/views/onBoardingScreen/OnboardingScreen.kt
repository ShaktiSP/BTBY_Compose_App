package com.example.composeapp.views.onBoardingScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.composeapp.appUtils.Outfit
import com.example.composeapp.R

@Composable
fun OnboardingScreen(navController: NavController) {

    Image(
        painter = painterResource(id = R.drawable.btby_theme),
        contentDescription = "btby theme",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )

    Column(
        modifier = Modifier
            .shadow(2.dp)
            .padding(bottom = 50.dp)
            .background(color = Color.Transparent)
            .windowInsetsPadding(WindowInsets.systemBars)
    ) {
        val pagerState = rememberPagerState(pageCount = {
            3
        })
        val currentPage = pagerState.currentPage

        val gradientList = listOf(
            Brush.horizontalGradient(listOf(Color(color = 0xFFFBDA61), Color(color = 0xFFFF5ACD))),
            Brush.horizontalGradient(
                listOf(
                    Color(color = 0xFF4C57CF),
                    Color(color = 0xFFC850C0),
                    Color(color = 0xFFFFCC70)
                )
            ),
            Brush.horizontalGradient(listOf(Color(color = 0xFFFBDA61), Color(color = 0xFFFF5ACD)))
        )

        val listItem = listOf(
            "Turning commercials into content",
            "Earning and growth opportunities",
            "Connect with your community"
        )

        val item = listItem[currentPage]

        val signInButtonGradient = gradientList[currentPage]



        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) { page ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Gray.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                when (page) {
                    0 -> TurningCommercialScreen()
                    1 -> EarningOpportunityScreen()
                    2 -> ConnectWithCommunityScreen()
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(pagerState.pageCount) { index ->
                Canvas(
                    modifier = Modifier
                        .size(20.dp)
                        .padding(horizontal = 4.dp)
                        .clickable {
//                            CoroutineScope(Dispatchers.Main).launch {
//                                pagerState.scrollToPage(index)
//                            }
                        }
                ) {
                    if (pagerState.currentPage == 0 && pagerState.currentPage == index) {
                        drawCircle(
                            brush = Brush.linearGradient(
                                colors = listOf(Color(0xFFFBDA61), Color(0xFFFF5ACD))
                            ),
                            radius = size.minDimension / 2
                        )
                    } else if (pagerState.currentPage == 1 && pagerState.currentPage == index) {
                        drawCircle(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    Color(0xFF4C57CF),
                                    Color(0xFFC850C0),
                                    Color(0xFFFFCC70)
                                )
                            ),
                            radius = size.minDimension / 2
                        )
                    } else if (pagerState.currentPage == 2 && pagerState.currentPage == index) {
                        drawCircle(
                            brush = Brush.linearGradient(
                                colors = listOf(Color(0xFFFBDA61), Color(0xFFFF5ACD))
                            ),
                            radius = size.minDimension / 2
                        )
                    }
                    if (pagerState.currentPage != index) {
                        drawCircle(
                            color = Color.White,
                            radius = (size.minDimension / 2) - 1.dp.toPx(),
                            style = Stroke(width = 1.5.dp.toPx())
                        )
                    }
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(25.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            Text(
                text = item,
                modifier = Modifier
                    .padding(bottom = 16.dp, start = 30.dp, end = 30.dp),
                style = TextStyle(
                    fontSize = 32.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontFamily = Outfit, fontWeight = FontWeight.Bold
                )
            )

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        signInButtonGradient, shape = ButtonDefaults.shape
                    )
                    .height(height = 50.dp),
                onClick = {
                    navController.navigate("signUp") {
                        popUpTo("onboarding") { inclusive = false }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
            ) {
                Text(
                    text = "Sign up with email",
                    color = Color.White,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = Outfit
                    )
                )
            }
            Row(modifier = Modifier.padding(top = 12.dp)) {
                HorizontalDivider(
                    thickness = 1.5.dp,
                    modifier = Modifier
                        .weight(0.38f)
                        .align(Alignment.CenterVertically),
                    color = Color(color = 0x33FFFFFF)
                )
                Text(
                    text = "or",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 17.sp,
                        fontFamily = Outfit,
                        fontWeight = FontWeight.W400
                    ),
                    modifier = Modifier.padding(start = 14.dp, end = 14.dp)
                )
                HorizontalDivider(
                    thickness = 1.5.dp,
                    modifier = Modifier
                        .weight(0.38f)
                        .align(Alignment.CenterVertically),
                    color = Color(color = 0x33FFFFFF)
                )

            }

            val borderGradientList = listOf(
                Brush.horizontalGradient(
                    listOf(
                        Color(color = 0xFFFBDA61),
                        Color(color = 0xFFFF5ACD)
                    )
                ),
                Brush.horizontalGradient(
                    listOf(
                        Color(color = 0xFF4C57CF),
                        Color(color = 0xFFC850C0),
                        Color(color = 0xFFFFCC70)
                    )
                ),
                Brush.horizontalGradient(
                    listOf(
                        Color(color = 0xFFFBDA61),
                        Color(color = 0xFFFF5ACD)
                    )
                )
            )
            val borderGradientItem = borderGradientList[currentPage]
            Button(
                onClick = {}, modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
                    .height(height = 50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ),
                shape = ButtonDefaults.shape,
                border = BorderStroke(
                    width = 2.dp,
                    brush = borderGradientItem
                )
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.google_icon),
                        contentDescription = "Google Icon",
                        modifier = Modifier.size(20.dp),
                        tint = Color.Unspecified
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Google",
                        color = Color.White,
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = Outfit
                        )
                    )
                }

            }
        }
    }
    LoginText(navController)


}

@Composable
fun LoginText(navController: NavController) {

    val gradientBrush = Brush.verticalGradient(
        colors = listOf(
            Color(color = 0xFFC850C0),
            Color(color = 0xFFC850C0),
            Color(color = 0xFF4C57CF)
        )
    )
    val annotatedString = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.outfit_regular)), fontSize = 17.sp
            )
        ) {
            append("Already have an account? ")
        }
        pushStringAnnotation(tag = "LOGIN", annotation = "login")
        withStyle(
            style = SpanStyle(
                brush = gradientBrush,
                fontWeight = FontWeight.Medium,
                fontFamily = Outfit,
                fontSize = 17.sp
            )
        ) {
            append("Login")
        }
        pop()
    }
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Text(
            text = annotatedString,
            modifier = Modifier
                .padding(16.dp)
                .windowInsetsPadding(WindowInsets.navigationBars)
                .clickable {
                    annotatedString
                        .getStringAnnotations(
                            tag = "LOGIN",
                            start = 0,
                            end = annotatedString.length
                        )
                        .firstOrNull()
                        ?.let {
                            navController.navigate("login") {
                                popUpTo("splash") { inclusive = true }
                            }
                        }
                },
            style = TextStyle(fontSize = 16.sp),
        )
    }
}
