package com.example.composeapp.views.dashBoard

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.example.composeapp.R
import com.example.composeapp.appUtils.Outfit
import com.example.composeapp.coustom.GradientBorderButtonWithImage
import com.example.composeapp.coustom.GradientButtonWithIcon
import com.example.composeapp.ui.theme.BlueGradient
import com.example.composeapp.ui.theme.PinkGradient
import com.example.composeapp.ui.theme.YellowGradient

@Composable
fun UpLoadScreen(navController: NavHostController){

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
                .systemBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            UploadScreenCommonTool(
                stringResource(R.string.upload_promos), navController

            )
            Spacer(modifier = Modifier.size(120.dp))
            Image(
                modifier = Modifier.size(100.dp),
                painter = painterResource(R.drawable.ic_upload),
                contentDescription = null
            )
            Spacer(modifier = Modifier.size(30.dp))
            Text(
                text = stringResource(R.string.upload_promo_title),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                fontFamily = Outfit,
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
                color = colorResource(R.color.white_txt),
                textAlign = TextAlign.Center
            )
            Text(
                text = stringResource(R.string.upload_promo_subtitle),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(vertical = 5.dp),
                fontFamily = Outfit,
                fontSize = 15.sp,
                fontWeight = FontWeight.Light,
                color = colorResource(R.color.trans_white_60),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.size(30.dp))
            GradientBorderButtonWithImage(
                text = stringResource(R.string.upload_video),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(horizontal = 15.dp),
                radius = 50,
                textSize = 15.sp,
                image = R.drawable.ic_upload_video,
                colorList = arrayListOf(BlueGradient, PinkGradient, YellowGradient),
                onClick = {
 //                   navController.navigate(HomeNavRoutes.UploadVideoRoute.route)

                }
            )
            GradientButtonWithIcon(
                text = stringResource(R.string.photo_slideshow),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                onClick = {
//                    navController.navigate(HomeNavRoutes.UploadPhotoSlideRoute.route)

//                    if (selectedImages.size < 3) {
//                        launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
//
//                    }
                },
                buttonIcon = R.drawable.ic_slide_img,
                gradient = Brush.linearGradient(
                    colors = listOf(BlueGradient, PinkGradient, YellowGradient),
                    start = Offset(0f, 0f),
                    end = Offset(
                        Float.POSITIVE_INFINITY,
                        Float.POSITIVE_INFINITY
                    )
                ),
                height = 50.dp,
                fontSize = 15.sp,
                fontFamily = Outfit,
                fontWeight = FontWeight.Medium
            )
        }
        // BottomSheetWithVisibleCorners()
    }

}


@Composable
fun UploadScreenCommonTool(toolTitle: String, navController: NavHostController) {
    var showDialog by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            modifier = Modifier.size(35.dp).clickable {
                navController.popBackStack()
            },
            painter = painterResource(R.drawable.back_arrow),
            contentDescription = null
        )

        Text(
            text = toolTitle,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .weight(1.5f),
            fontFamily = Outfit,
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp,
            color = colorResource(R.color.white_txt),
            textAlign = TextAlign.Start
        )

        GradientButtonWithIcon(
            text = stringResource(R.string.free_plan),
            modifier = Modifier
                .wrapContentWidth(),
            onClick = {
                showDialog = true

            },
            buttonIcon = R.drawable.ic_subscription,
            gradient = Brush.linearGradient(
                colors = listOf(BlueGradient, PinkGradient, YellowGradient),
                start = Offset(0f, 0f),
                end = Offset(
                    Float.POSITIVE_INFINITY,
                    Float.POSITIVE_INFINITY
                )
            ),
            height = 35.dp,
            fontSize = 13.sp,
            fontFamily = Outfit,
            fontWeight = FontWeight.Medium,
            borderThickness = 0.5.dp
        )
    }

    HorizontalDivider(
        modifier = Modifier.fillMaxWidth(),
        thickness = 1.dp,
        color = colorResource(R.color.trans_white_20)
    )
    if (showDialog) {
        Dialog(onDismissRequest = { showDialog = false }) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .height(450.dp)
                    .background(
                        color = Color.Gray, // Or use a theme color
                        shape = RoundedCornerShape(5) // Circular corners
                    )

            ) {
                CardSubscriptionPlan(
                    title = "Free Plan",
                    subtitle1 = "No monthly subscription (Pay-per-visit)",
                    subtitle2 = "\$0.99 per click-through ( redirect to visit links )",
                    subtitle3 = "Add Up to 3 Photos per Slideshow"
                )
            }
        }
    }
}