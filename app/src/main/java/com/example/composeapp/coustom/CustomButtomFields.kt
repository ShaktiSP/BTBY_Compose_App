package com.example.composeapp.coustom

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeapp.R
import com.example.composeapp.appUtils.Outfit

@Composable
fun GradientButton(
    modifier: Modifier = Modifier,
    text: String = "",
    onClick: () -> Unit,
    enabled: Boolean = true,
    gradient: Brush = Brush.horizontalGradient(
        colors = listOf(Color(0xFF6D5C7C), Color(0xFF9A84A7))
    ),
    height: Dp = 50.dp,
    fontSize: TextUnit = 18.sp,
    fontWeight: FontWeight = FontWeight.Bold,
    fontFamily: FontFamily = FontFamily(Font(R.font.outfit_bold)),
    contentColor: Color = Color.White,
    content: (@Composable RowScope.() -> Unit)? = null // ✅ Optional slot
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .background(gradient, shape = ButtonDefaults.shape)
            .height(height),
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
    ) {
        if (content != null) {
            content() // ✅ Use custom content if provided
        } else {
            Text(
                text = text,
                color = contentColor,
                style = TextStyle(
                    fontSize = fontSize,
                    fontWeight = fontWeight,
                    fontFamily = fontFamily
                )
            )
        }
    }
}

@Composable
fun GradientButtonWithIcon(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    buttonIcon: Int,
    enabled: Boolean = true,
    gradient: Brush = Brush.horizontalGradient(
        colors = listOf(Color(0xFF6D5C7C), Color(0xFF9A84A7))
    ),
    height: Dp = 50.dp,
    fontSize: TextUnit = 18.sp,
    fontWeight: FontWeight = FontWeight.Bold,
    fontFamily: FontFamily = FontFamily(Font(R.font.outfit_bold)),
    contentColor: Color = Color.White,
    borderThickness: Dp = 0.dp
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .background(gradient, shape = ButtonDefaults.shape)
            .height(height),
        enabled = enabled,
        border = BorderStroke(
            borderThickness,
            color = if (borderThickness == 0.dp) Color.Transparent else Color.White
        ),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
    ) {
        Row {
            Image(
                modifier = Modifier.size(20.dp),
                painter = painterResource(buttonIcon),
                contentDescription = null
            )
            Text(
                modifier = Modifier.padding(horizontal = 5.dp),
                text = text,
                color = contentColor,
                style = TextStyle(
                    fontSize = fontSize,
                    fontWeight = fontWeight,
                    fontFamily = fontFamily
                )
            )
        }
    }
}

@Composable
fun GradientBorderButtonWithImage(
    text: String,
    radius: Int,
    modifier: Modifier,
    image: Int,
    imageSize: Int = 25,
    onClick: () -> Unit,
    colorList: ArrayList<Color> = arrayListOf(Color.White, Color.White),
    backgroundColor: Color = colorResource(R.color.trans_black_30),
    textSize: TextUnit = 17.sp
) {
    val gradientBrush = Brush.horizontalGradient(
        colors = colorList
    )

    Box(
        modifier = modifier
            .border(
                width = 1.dp,
                brush = gradientBrush,
                shape = RoundedCornerShape(radius.dp)
            )
            .padding(1.dp) // Adds space inside the border
    ) {
        Button(
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
            shape = RoundedCornerShape(radius.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            Row {
                Image(
                    modifier = Modifier.size(imageSize.dp),
                    painter = painterResource(image),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier.padding(start = 5.dp, end = 2.dp),
                    text = text,
                    color = Color.White,
                    style = TextStyle(
                        fontSize = textSize,
                        fontWeight = FontWeight.Medium,
                        fontFamily = Outfit
                    )
                )
            }
        }
    }
}


