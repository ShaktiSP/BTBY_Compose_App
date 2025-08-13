package com.example.composeapp.appUtils

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeapp.R
import com.example.composeapp.ui.theme.BlueGradient
import com.example.composeapp.ui.theme.PinkBorderGradient
import com.example.composeapp.ui.theme.PinkGradient
import com.example.composeapp.ui.theme.YellowBorderGradient
import com.example.composeapp.ui.theme.YellowGradient

@Composable
fun GradientBorderText(
    text: String,
    radius: Int,
    modifier: Modifier = Modifier,
    textColor: Color = colorResource(R.color.white),
    colorList: List<Color> = listOf(Color.White, Color.White),
    backgroundColor: Color = colorResource(R.color.trans_black_30),
    height: Dp = Dp.Unspecified,
    horizontalPadding: Dp = 16.dp,
    verticalPadding: Dp = 7.dp,
    textStyle: TextStyle = TextStyle(
        fontSize = 13.sp,
        color = textColor,
        fontFamily = Outfit,
        fontWeight = FontWeight.Normal
    )
) {
    val baseModifier = if (height != Dp.Unspecified) {
        modifier
            .height(height)
            .clip(RoundedCornerShape(radius))
            .background(backgroundColor, shape = RoundedCornerShape(radius))
            .border(
                width = 1.dp,
                brush = Brush.linearGradient(colors = colorList),
                shape = RoundedCornerShape(radius)
            )
    } else {
        modifier
            .clip(RoundedCornerShape(radius))
            .background(backgroundColor, shape = RoundedCornerShape(radius))
            .border(
                width = 1.dp,
                brush = Brush.linearGradient(colors = colorList),
                shape = RoundedCornerShape(radius)
            )
            .padding(horizontal = horizontalPadding, vertical = verticalPadding)
    }

    Box(
        modifier = baseModifier,
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(start = 10.dp),
            style = textStyle
        )
    }
}


@Composable
fun GradientBorderWithImageAndText(
    text: String,
    radius: Int,
    modifier: Modifier,
    image: Int,
    imageSize: Int = 25,
    colorList: ArrayList<Color> = arrayListOf(Color.White, Color.White),
    backgroundColor: Color = colorResource(R.color.trans_black_30)
) {
    Row(
        modifier = modifier
            .background(color = backgroundColor, shape = RoundedCornerShape(radius))
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(radius),
                brush = Brush.linearGradient(
                    colors = colorList
                )
            )
            .padding(horizontal = 13.dp, vertical = 3.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        Image(
            modifier = Modifier
                .size(if (imageSize != 25) imageSize.dp else 25.dp)
                .align(Alignment.CenterVertically)
                .offset(y = (-2).dp),
            painter = painterResource(image),
            contentDescription = null
        )
        Text(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(vertical = if (imageSize != 25) 4.dp else 0.dp),
            text = text,
            style = TextStyle(
                fontSize = 12.sp,
                color = Color.White
            )
        )
    }
}

@Composable
fun GradientBgWithImageAndText(
    text: String,
    radius: Int,
    modifier: Modifier = Modifier,
    image: Int,
    imageSize: Int = 25,
    colorList: List<Color> = listOf(Color.White, Color.White),
    borderColor: Color = Color.White
) {
    Box(
        modifier = modifier
            .background(
                brush = Brush.linearGradient(colors = colorList),
                shape = RoundedCornerShape(radius)
            )
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(radius),
                color = borderColor
            )
            .padding(horizontal = 13.dp, vertical = 3.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            Image(
                modifier = Modifier
                    .size(imageSize.dp)
                    .align(Alignment.CenterVertically)
                    .offset(y = (-2).dp),
                painter = painterResource(image),
                contentDescription = null
            )
            Text(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(vertical = if (imageSize != 25) 4.dp else 0.dp),
                text = text,
                style = TextStyle(
                    fontSize = 11.sp,
                    color = Color.White
                )
            )
        }
    }
}

/*
@Composable
fun GradientFollowButton(
    initialState: Boolean,
    modifier: Modifier = Modifier,
    onFollowChange: (Boolean) -> Unit
) {
    var isFollowing by remember { mutableStateOf(initialState) }

    val text = if (isFollowing) "Following" else "Follow"
    val colorList = listOf(BlueGradient, PinkGradient, YellowGradient)

    Box(
        modifier = modifier
            .width(110.dp)
            .clip(RoundedCornerShape(50.dp))
            .clickable {
                isFollowing = !isFollowing
                onFollowChange(isFollowing)
            }
            .then(
                if (isFollowing) {
                    Modifier.background(Brush.horizontalGradient(colorList))
                } else {
                    Modifier
                        .background(color = colorResource(R.color.trans_black_30))
                        .border(
                            width = 1.dp,
                            shape = RoundedCornerShape(50.dp),
                            brush = Brush.linearGradient(colorList)
                        )
                }
            )
            .padding(horizontal = 16.dp, vertical = 7.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}*/

@Composable
fun GradientFollowButton(
    modifier: Modifier = Modifier,
    isFollowed: Boolean,
    onClick: () -> Unit,
    followText: String = "Following",
    unfollowText: String = "Follow",
    followGradient: Brush = Brush.linearGradient(
        colors = listOf(BlueGradient, PinkGradient, YellowGradient),
        start = Offset(0f, 0f),
        end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
    ),
    borderGradient: Brush = Brush.linearGradient(
        colors = listOf(YellowBorderGradient, PinkBorderGradient),
        start = Offset(0f, 0f),
        end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
    ),
    unfollowColor: Color = colorResource(id = R.color.trans_black_30),
    height: Dp = 35.dp,
    fontSize: TextUnit = 13.sp,
    fontFamily: FontFamily = Outfit,
    fontWeight: FontWeight = FontWeight.Medium,
    borderThickness: Dp = 1.dp
) {
    val backgroundModifier = if (isFollowed) {
        Modifier.background(followGradient, shape = ButtonDefaults.shape)
    } else {
        Modifier.background(unfollowColor, shape = ButtonDefaults.shape)
    }

    val border = if (isFollowed) {
        BorderStroke(borderThickness, Color.White)
    } else {
        BorderStroke(borderThickness,borderGradient)
    }
    Button(
        onClick = onClick,
        modifier = modifier.then(backgroundModifier)
            .height(height)
            .width(125.dp)
            .clip(RoundedCornerShape(50.dp)),
        border = border,
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 5.dp)
    ) {
        Text(
            text = if (isFollowed) followText else unfollowText,
            color = Color.White,
            fontSize = fontSize,
            fontWeight = fontWeight,
            fontFamily = fontFamily
        )
    }
}


@Composable
fun GradientBlockButton(
    initialState: Boolean,
    modifier: Modifier = Modifier,
    onFollowChange: (Boolean) -> Unit
) {
    var isBlock by remember { mutableStateOf(initialState) }

    val text = if (isBlock) "Unblocked" else "Blocked"
    val colorList = listOf(BlueGradient, PinkGradient, YellowGradient)

    Box(
        modifier = modifier
            .width(110.dp)
            .clip(RoundedCornerShape(50.dp))
            .clickable {
                isBlock = !isBlock
                onFollowChange(isBlock)
            }
            .then(
                if (isBlock) {
                    Modifier.background(Brush.horizontalGradient(colorList))
                } else {
                    Modifier
                        .background(color = colorResource(R.color.trans_black_30))
                        .border(
                            width = 1.dp,
                            shape = RoundedCornerShape(50.dp),
                            brush = Brush.linearGradient(colorList)
                        )
                }
            )
            .padding(horizontal = 16.dp, vertical = 7.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}




@Composable
fun RoundedBgTextWithImage(
    text: String,
    modifier: Modifier,
    image: Int,
    backgroundColor: Color = colorResource(R.color.trans_white_20)
) {
    Row(
        modifier = modifier
            .background(color = backgroundColor, shape = RoundedCornerShape(50.dp))
            .padding(horizontal = 10.dp, vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        Image(
            modifier = Modifier
                .size(20.dp)
                .align(Alignment.CenterVertically),
            painter = painterResource(image),
            contentDescription = null
        )
        Text(
            modifier = Modifier.align(Alignment.CenterVertically),
            text = text,
            style = TextStyle(
                fontSize = 12.sp,
                color = Color.White
            )
        )
    }
}


@Composable
fun ExpandableText(
    text: String,
    modifier: Modifier,
    collapsedMaxLines: Int = 2,
) {
    var isExpanded by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        Text(
            text = text,
            maxLines = if (isExpanded) Int.MAX_VALUE else collapsedMaxLines,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(
                color = Color.White,
                fontSize = 15.sp,
                fontFamily = Outfit,
                fontWeight = FontWeight.Light
            )
        )
        Text(
            text = if (isExpanded) "See Less" else "See More",
            style = TextStyle(
                color = Color.White,
                fontSize = 15.sp,
                fontFamily = Outfit,
                fontWeight = FontWeight.SemiBold,
                textDecoration = TextDecoration.Underline
            ),
            modifier = Modifier
                .clickable { isExpanded = !isExpanded }
                .padding(bottom = 8.dp)
        )
    }
}

@Composable
fun TagStyledGradientText(
    fullText: String,
    radius: Int,
    modifier: Modifier = Modifier,
    highlightColor: Color = Color.Yellow,
    normalTextColor: Color = Color.White
) {
    val annotatedText = buildAnnotatedString {
        if (fullText.isNotEmpty()) {
            withStyle(SpanStyle(color = highlightColor)) {
                append(fullText[0])
            }
            withStyle(SpanStyle(color = normalTextColor)) {
                append(fullText.substring(1))
            }
        }
    }

    Box(
        modifier = modifier
            .background(color = colorResource(R.color.trans_black_30), shape = RoundedCornerShape(radius))
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(radius),
                brush = Brush.linearGradient(colors = listOf(Color.Gray, Color.Gray))
            )
            .padding(horizontal = 16.dp, vertical = 3.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = annotatedText,
            fontSize = 13.sp,
            fontFamily = Outfit,
            fontWeight = FontWeight.Normal,
            color = Color.Unspecified // Let spans apply color
        )
    }
}