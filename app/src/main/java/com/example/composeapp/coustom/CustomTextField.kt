package com.example.composeapp.coustom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeapp.appUtils.Outfit
import com.example.composeapp.R

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    singleLine: Boolean = true,
    maxLine: Int = 1,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    placeholder: String = "Enter text",
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Done,
    strokeWidth:Float = 1f
){
    val keyboardController = LocalSoftwareKeyboardController.current
    val gradientBrush = Brush.linearGradient(
        colors = listOf(
            colorResource(R.color.trans_white_20), // Start color
            colorResource(R.color.trans_white_30) // End color
        )
    )

    Box(
        modifier = modifier
            .drawBehind {
                drawRoundRect(
                    brush = gradientBrush,
                    size = size.copy(width = size.width - strokeWidth, height = size.height - strokeWidth),
                    cornerRadius = CornerRadius(70f),
                    style = Stroke(width = strokeWidth)
                )
            }
            .background(color = colorResource(R.color.trans_white_15), shape = RoundedCornerShape(70f))
            .padding(2.dp) // Padding to create a border effect
    ) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            leadingIcon = leadingIcon ,
            trailingIcon = trailingIcon,
            prefix = prefix,
            placeholder = { Text(placeholder, color = Color.White.copy(0.5f)) },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent, // Background when focused
                unfocusedContainerColor = Color.Transparent, // Background when not focused
                focusedIndicatorColor = Color.Transparent, // Indicator when focused
                unfocusedIndicatorColor = Color.Transparent, // Indicator when not focused
                cursorColor = colorResource(id = R.color.white_txt), // Cursor color
            ),
            modifier = Modifier
                .fillMaxSize().padding(start = 10.dp).align(Alignment.Center),
            textStyle = TextStyle(
                color = Color.White,
                fontSize = 15.sp,
                fontFamily = Outfit,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Start
            ),
            singleLine = singleLine,
            maxLines = maxLine,
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    if (imeAction == ImeAction.Done) keyboardController?.hide()
                }
            )
        )
    }
}