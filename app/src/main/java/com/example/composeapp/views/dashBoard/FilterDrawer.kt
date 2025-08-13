package com.example.composeapp.views.dashBoard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeapp.R
import com.example.composeapp.appUtils.Outfit
import com.example.composeapp.ui.theme.BlueGradient
import com.example.composeapp.ui.theme.Pink40
import com.example.composeapp.ui.theme.PinkBorderGradient
import com.example.composeapp.ui.theme.PinkGradient
import com.example.composeapp.ui.theme.PurpleGrey40
import com.example.composeapp.ui.theme.YellowBorderGradient
import com.example.composeapp.ui.theme.YellowGradient

enum class FilterOption {
    ANY_TIME, TODAY, THIS_WEEK, THIS_MONTH
}

enum class DurationOption {
    ANY, UNDER_30, UNDER_60, OVER_60
}

@Composable
fun FilterDrawer(
    // onApply: (ReelSearchRequest) -> Unit,
    onReset: () -> Unit,
    onClose: () -> Unit,
) {
    var mostLikes by remember { mutableStateOf(false) }
    var mostShares by remember { mutableStateOf(false) }
    var mostVisits by remember { mutableStateOf(false) }
    var mostAddToCarts by remember { mutableStateOf(false) }

    var selectedFilter by remember { mutableStateOf<FilterOption?>(null) }
    var distance = remember { mutableStateOf(50f) }
    var selectedDuration by remember { mutableStateOf<DurationOption?>(null) }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .background(
                brush = Brush.linearGradient(listOf(PurpleGrey40, PurpleGrey40, Pink40))
            )
            .padding(start = 15.dp, end = 15.dp, bottom = 80.dp)
    ) {

        // 🔹 Fixed Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 35.dp, bottom = 20.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.cancel_icon),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .size(37.dp)
                    .clickable { onClose() }
            )
            Text(
                text = "Filter",
                modifier = Modifier.align(Alignment.Center),
                style = TextStyle(
                    color = Color.White,
                    fontWeight = FontWeight.Medium,
                    fontSize = 23.sp,
                    fontFamily = Outfit
                )
            )
        }

        // 🔹 Scrollable Filter Content
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .wrapContentHeight()
                .verticalScroll(scrollState)
        ) {
            Text(
                "People's Choice", style = TextStyle(
                    color = Color.White,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    fontFamily = Outfit
                )
            )
            Spacer(Modifier.height(15.dp))

            FilterCheckbox("Most Likes", mostLikes) { mostLikes = it }
            FilterCheckbox("Most share", mostShares) { mostShares = it }
            FilterCheckbox("Most visits", mostVisits) { mostVisits = it }
            FilterCheckbox("Most add to carts", mostAddToCarts) { mostAddToCarts = it }

            Spacer(Modifier.height(30.dp))

            Text("Upload Date", style = textStyle())
            Spacer(Modifier.height(15.dp))

            FilterCheckbox("Any Time", selectedFilter == FilterOption.ANY_TIME) {
                if (it) selectedFilter = FilterOption.ANY_TIME
            }
            FilterCheckbox("Today", selectedFilter == FilterOption.TODAY) {
                if (it) selectedFilter = FilterOption.TODAY
            }
            FilterCheckbox("This Week", selectedFilter == FilterOption.THIS_WEEK) {
                if (it) selectedFilter = FilterOption.THIS_WEEK
            }
            FilterCheckbox("This Month", selectedFilter == FilterOption.THIS_MONTH) {
                if (it) selectedFilter = FilterOption.THIS_MONTH
            }

            Spacer(Modifier.height(30.dp))

            Text("Duration", style = textStyle())
            Spacer(Modifier.height(15.dp))

            FilterCheckbox("Any", selectedDuration == DurationOption.ANY) {
                selectedDuration = if (it) DurationOption.ANY else null
            }
            FilterCheckbox("Under 30 Seconds", selectedDuration == DurationOption.UNDER_30) {
                selectedDuration = if (it) DurationOption.UNDER_30 else null
            }
            FilterCheckbox("Under 60 Seconds", selectedDuration == DurationOption.UNDER_60) {
                selectedDuration = if (it) DurationOption.UNDER_60 else null
            }
            FilterCheckbox("Over 60 Seconds", selectedDuration == DurationOption.OVER_60) {
                selectedDuration = if (it) DurationOption.OVER_60 else null
            }

            Spacer(Modifier.height(20.dp))
        }

        // 🔹 Fixed Footer Buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 25.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    onReset()
                    mostLikes = false
                    mostShares = false
                    mostVisits = false
                    mostAddToCarts = false
                    selectedFilter = null
                    selectedDuration = null
                    distance.value = 0f
                },
                modifier = Modifier
                    .height(35.dp)
                    .width(100.dp)
                    .clip(RoundedCornerShape(50.dp)),
                border = BorderStroke(
                    1.dp, Brush.linearGradient(
                        listOf(YellowBorderGradient, PinkBorderGradient)
                    )
                ),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
            ) {
                Text("Reset", color = Color.White, fontSize = 13.sp, fontFamily = Outfit)
            }

            Box(
                modifier = Modifier
                    .height(35.dp)
                    .width(100.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .background(
                        Brush.horizontalGradient(
                            listOf(BlueGradient, PinkGradient, YellowGradient)

                        )
                    )

                    .clickable {
//                        val request = ReelSearchRequest(
//                            most_like = mostLikes,
//                            most_share = mostShares,
//                            most_visit = mostVisits,
//                            most_add_to_cart = mostAddToCarts,
//                            any_time = selectedFilter == FilterOption.ANY_TIME,
//                            today = selectedFilter == FilterOption.TODAY,
//                            this_week = selectedFilter == FilterOption.THIS_WEEK,
//                            this_month = selectedFilter == FilterOption.THIS_MONTH
//                        )
//                        onApply(request)
                    },
                contentAlignment = Alignment.Center
            ) {
                Text("Apply", color = Color.White, fontSize = 13.sp, fontFamily = Outfit)
            }
        }
    }
}

@Composable
fun textStyle() = TextStyle(
    color = Color.White,
    fontWeight = FontWeight.Medium,
    fontSize = 16.sp,
    fontFamily = Outfit
)


@Composable
fun FilterCheckbox(
    text: String, isChecked: Boolean = false,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(
                    brush = if (isChecked) Brush.linearGradient(
                        colorStops = arrayOf(
                            0.0f to Color(0xFF4C57CF),
                            0.5f to Color(0xFFC850C0),
                            1.0f to Color(0xFFFFCC70)
                        ),
                        start = Offset(0f, Float.POSITIVE_INFINITY),
                        end = Offset(Float.POSITIVE_INFINITY, 0f)
                    ) else Brush.linearGradient(
                        colors = listOf(Color(0x26FFFFFF), Color(0x91FFFFFF))
                    )
                )
                .then(
                    if (!isChecked) Modifier.border(
                        width = 1.5.dp,
                        brush = Brush.linearGradient(
                            colors = listOf(Color(0x00FFFFFF), Color(0x91FFFFFF))
                        ),
                        shape = RoundedCornerShape(4.dp)
                    ) else Modifier
                )
                .padding(4.dp),
            contentAlignment = Alignment.Center
        ) {
            Checkbox(
                checked = isChecked,
                onCheckedChange = {
                    onCheckedChange(it)
                },
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent),
                colors = CheckboxDefaults.colors(
                    uncheckedColor = Color.Transparent,
                    checkedColor = Color.Transparent,
                    checkmarkColor = Color.White
                )
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text,
            style = TextStyle(
                color = Color.White,
                fontWeight = FontWeight.W300,
                fontSize = 14.sp,
                fontFamily = Outfit
            )
        )
    }
}

@Composable
@Preview
private fun FilterDrawerPreview() {
    FilterDrawer(
        //   onApply = {},
        onReset = {}, onClose = {})
}