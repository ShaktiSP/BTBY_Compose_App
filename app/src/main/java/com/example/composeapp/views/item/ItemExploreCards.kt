package com.example.composeapp.views.item

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.composeapp.R
import com.example.composeapp.appUtils.GradientBgWithImageAndText
import com.example.composeapp.appUtils.GradientBorderWithImageAndText
import com.example.composeapp.appUtils.Outfit
import com.example.composeapp.appUtils.RoundedBgTextWithImage
import com.example.composeapp.ui.theme.BlueGradient
import com.example.composeapp.ui.theme.PinkGradient
import com.example.composeapp.ui.theme.YellowGradient

@Preview(showBackground = true)
@Composable
fun ItemExploreCards() {

    Card(
        modifier = Modifier
            .width(200.dp)
            .wrapContentHeight(),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.trans_white_10),
            contentColor = colorResource(R.color.trans_gray_80)
        ),
        border = BorderStroke(width = 0.5.dp, color = colorResource(R.color.trans_white_30))

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(7.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(210.dp)
                    .clickable {

                    }
                    .clip(shape = RoundedCornerShape(10.dp)),
            ) {
                AsyncImage(
                    model = "S3Utils.generateS3ShareUrl(context",
                    modifier = Modifier.fillMaxSize(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    placeholder = ColorPainter(Color.LightGray),
                    error = ColorPainter(Color.LightGray),
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp)
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f))
                            )
                        )
                        .align(Alignment.BottomCenter)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(7.dp), // Padding around the row
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    RoundedBgTextWithImage(
                        text = "1",
                        modifier = Modifier
                            .wrapContentSize()
                            .clickable {

                            },
                        image = R.drawable.play_icon
                    )
                    RoundedBgTextWithImage(
                        text = "0",
                        modifier = Modifier.wrapContentSize(),
                        image = R.drawable.like_colored
                    )
                }
            }

            Spacer(modifier = Modifier.size(7.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.size(5.dp))

                AsyncImage(
                    model = "item.profile_pic",
                    contentDescription = "Circular Image",
                    modifier = Modifier
                        .size(30.dp)
                        .clip(CircleShape)
                        .border(1.dp, Color.White, CircleShape),
                    error = painterResource(id = R.drawable.pic_placeholder),
                    placeholder = painterResource(id = R.drawable.pic_placeholder),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = toString(),
                    modifier = Modifier.padding(7.dp),
                    fontFamily = Outfit,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    maxLines = 1
                )
            }

            Text(
                text = "toString()",
                fontFamily = Outfit,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .padding(5.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(7.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                GradientBorderWithImageAndText(
                    text = "Web",
                    modifier = Modifier
                        .wrapContentSize()
                        .clickable {

                        },
                    colorList = arrayListOf(BlueGradient, PinkGradient, YellowGradient),
                    image = R.drawable.web_icon,
                    radius = 50,
                    imageSize = 15
                )

                GradientBgWithImageAndText(
                    text = "Added",
                    modifier = Modifier
                        .wrapContentSize()
                        .clickable {

                        },
                    image = R.drawable.mdi_cart,
                    radius = 50,
                    imageSize = 14

                )
            }

        }

    }

}