package com.example.composeapp.views.dashBoard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.composeapp.R
import com.example.composeapp.appUtils.CommonToolbar
import com.example.composeapp.appUtils.Outfit
import com.example.composeapp.coustom.GradientButtonWithIcon
import com.example.composeapp.ui.theme.BlueGradient
import com.example.composeapp.ui.theme.PinkGradient
import com.example.composeapp.ui.theme.YellowGradient


@Composable
fun SubscriptionPlanScreen(navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(R.drawable.btby_theme),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
        ) {
            CommonToolbar(stringResource(R.string.subs_plan), navController)

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 15.dp)
            ) {
                Spacer(modifier = Modifier.size(30.dp))
                CardSubscriptionPlan(
                    stringResource(R.string.subs_plan_card1_title),
                    stringResource(R.string.subs_plan_card1_subtitle_1),
                    stringResource(R.string.subs_plan_card1_subtitle_3),
                    stringResource(R.string.subs_plan_card1_subtitle_3)
                )

                Spacer(modifier = Modifier.size(20.dp))
                CardSubscriptionPlan(
                    stringResource(R.string.subs_plan_card2_title),
                    stringResource(R.string.subs_plan_card2_subtitle_1),
                    stringResource(R.string.subs_plan_card2_subtitle_3),
                    stringResource(R.string.subs_plan_card2_subtitle_3)
                )

                Spacer(modifier = Modifier.size(20.dp))
                CardSubscriptionPlan(
                    stringResource(R.string.subs_plan_card3_title),
                    stringResource(R.string.subs_plan_card3_subtitle_1),
                    stringResource(R.string.subs_plan_card3_subtitle_3),
                    stringResource(R.string.subs_plan_card3_subtitle_3)
                )

                Spacer(modifier = Modifier.size(20.dp))
            }
        }
    }
}

@Composable
fun CardSubscriptionPlan(title: String, subtitle1: String, subtitle2: String, subtitle3: String) {
    val gradientBrush = Brush.linearGradient(
        colors = listOf(BlueGradient, PinkGradient, YellowGradient)
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .border(
                width = 1.dp,
                brush = gradientBrush,
                shape = RoundedCornerShape(5)
            )
            .padding(1.dp) // Adds space inside the border
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(40.dp))
            Image(
                modifier = Modifier
                    .size(70.dp),
                painter = painterResource(R.drawable.subs_top_icon),
                contentDescription = null
            )
            Spacer(modifier = Modifier.size(20.dp))
            Text(
                text = title,
                fontFamily = Outfit,
                fontWeight = FontWeight.SemiBold,
                fontSize = 25.sp,
                color = colorResource(R.color.white_txt)
            )
            Spacer(modifier = Modifier.size(20.dp))

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(1.dp)
                    .background(color = colorResource(R.color.trans_white_30))
            )
            Spacer(modifier = Modifier.size(20.dp))

            ImageWithText(subtitle1)
            Spacer(modifier = Modifier.size(15.dp))
            ImageWithText(subtitle2)
            Spacer(modifier = Modifier.size(15.dp))
            ImageWithText(subtitle3)
            Spacer(modifier = Modifier.size(15.dp))
            GradientButtonWithIcon(
                text = stringResource(R.string.photo_slideshow),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                onClick = {},
                buttonIcon = R.drawable.ic_subscription,
                gradient = Brush.linearGradient(
                    colors = listOf(BlueGradient, PinkGradient, YellowGradient),
                    start = Offset(0f, 0f),
                    end = Offset(
                        Float.POSITIVE_INFINITY,
                        Float.POSITIVE_INFINITY
                    )
                )
            )
            Spacer(modifier = Modifier.size(25.dp))
        }
    }
}

@Composable
fun ImageWithText(text: String, image: Painter = painterResource(R.drawable.success_icon)) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 15.dp)) {
        Image(modifier = Modifier.size(30.dp), painter = image, contentDescription = null)
        Spacer(modifier = Modifier.size(10.dp))
        Text(
            text = text,
            color = colorResource(R.color.trans_white_60),
            fontFamily = Outfit,
            fontWeight = FontWeight.Light,
            fontSize = 16.sp
        )
    }
}