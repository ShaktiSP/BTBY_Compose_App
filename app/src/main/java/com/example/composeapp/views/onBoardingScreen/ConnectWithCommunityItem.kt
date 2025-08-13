package com.example.composeapp.views.onBoardingScreen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeapp.R
import com.example.composeapp.appUtils.Outfit

@Composable
fun ConnectWithCommunityItem(userImage: Int, userName: String) {
    Card(
        modifier = Modifier
            .padding(top = 20.dp, start = 20.dp, end = 20.dp)
            .fillMaxWidth()
            .background(shape = RoundedCornerShape(20.dp), color = Color(color = 0x26FFFFFF))
            .border(
                width = 2.dp,
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(color = 0x00FFFFFF),
                        Color(color = 0x2CFFFFFF)
                    )
                ),
                shape = RoundedCornerShape(20.dp)
            ),
        colors = CardDefaults.cardColors(
            containerColor = Color(0x26FFFFFF)
        ), shape = RoundedCornerShape(20.dp)

    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val gradientBrush = Brush.horizontalGradient(
                colors = listOf(
                    Color(color = 0xFFFBDA61),
                    Color(color = 0xFFFF5ACD)
                )
            )
            Canvas(
                modifier = Modifier
                    .size(25.dp)
                    .padding(start = 15.dp)
            ) {
                drawCircle(
                    brush = gradientBrush,
                    radius = size.minDimension / 2,
                    center = center
                )
            }
            Image(
                painter = painterResource(id = R.drawable.user_image),
                contentDescription = "btby theme",
                modifier = Modifier
                    .height(60.dp)
                    .width(60.dp)
                    .padding(start = 8.dp)
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = userName, style = TextStyle(
                        fontSize = 17.sp,
                        fontFamily = Outfit,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Box(
                    modifier = Modifier
                        .height(13.dp)
                        .fillMaxWidth(0.86f)
                        .padding(top = 4.dp)
                        .background(Color(0x33FFFFFF), shape = RoundedCornerShape(15.dp))
                )

                Box(
                    modifier = Modifier
                        .height(13.dp)
                        .fillMaxWidth(0.6f)
                        .padding(top = 4.dp)
                        .background(Color(0x33FFFFFF), shape = RoundedCornerShape(15.dp))
                )
            }
            Image(
                painter = painterResource(id = R.drawable.next_arrow),
                contentDescription = "btby theme",
                modifier = Modifier
                    .height(17.dp)
                    .width(17.dp)
            )
        }
    }
}


data class UserCategory(val userImage: Int, val userName: String)

fun getUser(): MutableList<UserCategory> {
    val list = mutableListOf<UserCategory>()
    list.add(UserCategory(1, "Chloe"))
    list.add(UserCategory(1, "Chloe 2"))
    list.add(UserCategory(1, "Chloe 3"))
    list.add(UserCategory(1, "Chloe 4"))
    list.add(UserCategory(1, "Chloe 5"))
    list.add(UserCategory(1, "Chloe 6"))
    return list
}