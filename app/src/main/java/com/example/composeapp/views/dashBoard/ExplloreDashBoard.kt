package com.example.composeapp.views.dashBoard

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.composeapp.R
import com.example.composeapp.appUtils.GradientBorderText
import com.example.composeapp.appUtils.Outfit
import com.example.composeapp.views.item.ItemExploreCards

@Composable
fun ExploreDashBoard(navController: NavHostController) {

    var isDrawerOpen by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.systemBars)
    ) {

        Image(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.TopStart), // important
            painter = painterResource(R.drawable.btby_theme),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        // ✅ Foreground UI
        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
        ) {
            // HEADER
            Image(
                modifier = Modifier
                    .wrapContentWidth()
                    .height(40.dp)
                    .padding(horizontal = 15.dp)
                    .align(Alignment.CenterHorizontally),
                painter = painterResource(R.drawable.explore_text),
                contentDescription = null
            )

            Spacer(modifier = Modifier.size(20.dp))

            // SEARCH BAR
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
                    .height(55.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                GradientBorderText(
                    text = stringResource(R.string.explore_search_hint),
                    radius = 50,
                    colorList = arrayListOf(
                        Color.Transparent,
                        colorResource(R.color.trans_white_5),
                        colorResource(R.color.trans_white_30)
                    ),
                    textColor = colorResource(R.color.trans_gray_70),
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clickable {},
                    backgroundColor = colorResource(R.color.trans_white_10)
                )

                Spacer(modifier = Modifier.width(10.dp))

                Image(
                    painter = painterResource(R.drawable.ic_explore_more),
                    contentDescription = null,
                    modifier = Modifier
                        .size(45.dp)
                        .clickable {
                            isDrawerOpen = true
                        },
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.size(15.dp))

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(bottom = 90.dp)
            ) {

                Text(
                    text = stringResource(R.string.explore_page_title_2),
                    style = TextStyle(
                        fontFamily = Outfit, fontWeight = FontWeight.SemiBold, fontSize = 20.sp
                    ),
                    modifier = Modifier.padding(horizontal = 15.dp),
                    color = colorResource(R.color.trans_gray_80)
                )

                Spacer(modifier = Modifier.size(15.dp))

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    contentPadding = PaddingValues(horizontal = 15.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(5) { item ->

                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            AsyncImage(
                                model = "S3Utils.generateS3ShareUrl(context, item.profile_pic)",
                                contentDescription = "Circular Image",
                                modifier = Modifier
                                    .size(70.dp)
                                    .clip(CircleShape)
                                    .clickable {

                                    },
                                error = painterResource(id = R.drawable.pic_placeholder),
                                placeholder = painterResource(id = R.drawable.pic_placeholder),
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = Modifier.size(5.dp))
                            Text(
                                text = "Popular", style = TextStyle(
                                    fontFamily = Outfit,
                                    fontWeight = FontWeight.Light,
                                    fontSize = 15.sp
                                ), color = colorResource(R.color.trans_gray_80)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.size(25.dp))

                Text(
                    text = stringResource(R.string.explore_page_title_3),
                    style = TextStyle(
                        fontFamily = Outfit, fontWeight = FontWeight.SemiBold, fontSize = 20.sp
                    ),
                    modifier = Modifier.padding(horizontal = 15.dp),
                    color = colorResource(R.color.trans_gray_80)
                )

                Spacer(modifier = Modifier.size(15.dp))

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    contentPadding = PaddingValues(horizontal = 15.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(5) { item ->
                        ItemExploreCards()
                    }
                }
                Spacer(modifier = Modifier.size(25.dp))

                Text(
                    text = stringResource(R.string.explore_page_title_4),
                    style = TextStyle(
                        fontFamily = Outfit, fontWeight = FontWeight.SemiBold, fontSize = 20.sp
                    ),
                    modifier = Modifier.padding(horizontal = 15.dp),
                    color = colorResource(R.color.trans_gray_80)
                )

                Spacer(modifier = Modifier.size(15.dp))

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    contentPadding = PaddingValues(horizontal = 15.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(5) { item ->
                        ItemExploreCards()
                    }
                }

                Spacer(modifier = Modifier.size(25.dp))

                Text(
                    text = stringResource(R.string.explore_page_title_5),
                    style = TextStyle(
                        fontFamily = Outfit, fontWeight = FontWeight.SemiBold, fontSize = 20.sp
                    ),
                    modifier = Modifier.padding(horizontal = 15.dp),
                    color = colorResource(R.color.trans_gray_80)
                )
                Spacer(modifier = Modifier.size(15.dp))

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    contentPadding = PaddingValues(horizontal = 15.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(5) { item ->
                        ItemExploreCards()
                    }
                }
            }
        }
    }
    ExploreReelFilter(
        navController = navController,
        isDrawerOpen = isDrawerOpen,
        onCloseDrawer = { isDrawerOpen = false },
        modifier = Modifier
            .fillMaxSize()
            .zIndex(2f) // This ensures it's above other content
    )
}

@Composable
fun ExploreReelFilter(
    navController: NavHostController,
    isDrawerOpen: Boolean,
    modifier: Modifier = Modifier,
    onCloseDrawer: () -> Unit
) {
    var filterApplied by remember { mutableStateOf(false) }

    Box(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()) {

        AnimatedVisibility(
            visible = isDrawerOpen, enter = slideInHorizontally(
                initialOffsetX = { it }, animationSpec = tween(300)
            ), exit = slideOutHorizontally(
                targetOffsetX = { it }, animationSpec = tween(300)
            ), modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(300.dp)
                    .background(Color(0xFF2C1C3C))
            ) {
                FilterDrawer(
                    onReset = { }, onClose = onCloseDrawer
                )
            }
        }
    }
}