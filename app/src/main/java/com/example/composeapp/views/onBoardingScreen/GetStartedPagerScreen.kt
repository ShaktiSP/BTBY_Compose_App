package com.example.composeapp.views.onBoardingScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.MimeTypes
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.example.composeapp.R

@Composable
fun TurningCommercialScreen() {
    val context = LocalContext.current
    val videoUri = "android.resource://${context.packageName}/raw/static_video"

    val player = remember {
        ExoPlayer.Builder(context).build().apply {
            val mediaItem = MediaItem.Builder()
                .setUri(videoUri)
                .setMimeType(MimeTypes.VIDEO_MP4)
                .build()
            setMediaItem(mediaItem)
            prepare()
            repeatMode = Player.REPEAT_MODE_ALL
            playWhenReady = true
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            player.release()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView(
            factory = {
                PlayerView(it).apply {
                    this.player = player
                    useController = false
                }
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun EarningOpportunityScreen() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.growth_img),
            contentDescription = "btby Logo",
            modifier = Modifier.align(Alignment.Center).width(120.dp)
        )
    }

}

@Composable
fun ConnectWithCommunityScreen() {
    val users = getUser()
    val listState = rememberLazyListState()

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(vertical = 16.dp),
            modifier = Modifier.fillMaxSize()) {
            items(count = Int.MAX_VALUE) { index ->
                val actualIndex = index % users.size
                val user = users[actualIndex]
                ConnectWithCommunityItem(user.userImage, user.userName)
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Black.copy(alpha = 0.5f), Color.Transparent)
                    )
                )
                .align(Alignment.TopCenter)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(170.dp)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color(color = 0xFF4D3744).copy(alpha = 1.3f))
                    )
                )
                .align(Alignment.BottomCenter)
        )
    }

    LaunchedEffect(listState) {
        listState.scrollToItem(users.size)
        snapshotFlow { listState.firstVisibleItemIndex }.collect { index->
        }
    }
}