package com.example.composeapp.appUtils

import android.content.Context
import android.net.ConnectivityManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.composeapp.R
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return connectivityManager.activeNetworkInfo?.isConnectedOrConnecting == true
}

@Composable
fun CommonToolbar(toolTitle: String, navController: NavHostController){
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
                .padding(horizontal = 15.dp)
                .weight(1f),
            fontFamily = Outfit,
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            color = colorResource(R.color.white_txt),
            textAlign = TextAlign.Start
        )
    }

    HorizontalDivider(
        modifier = Modifier.fillMaxWidth(),
        thickness = 1.dp,
        color = colorResource(R.color.trans_white_20)
    )
}

@Composable
fun CommonToolbarCenter(toolTitle: String, navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Image(
            modifier = Modifier
                .size(35.dp)
                .align(Alignment.CenterStart)
                .clickable {
                    navController.popBackStack()
                },
            painter = painterResource(R.drawable.back_arrow),
            contentDescription = null
        )

        Text(
            text = toolTitle,
            modifier = Modifier.align(Alignment.Center),
            fontFamily = Outfit,
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            color = colorResource(R.color.white_txt),
            textAlign = TextAlign.Center
        )
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewCommonToolbar() {
    CommonToolbar(toolTitle = "Back Title", navController = rememberNavController())
}

@Preview(showBackground = true)
@Composable
fun PreviewCommonToolbarCenter() {
    CommonToolbarCenter(toolTitle = "Center Title", navController = rememberNavController())
}

@Composable
fun getTimeOffset(): String {
    val calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"), Locale.getDefault())
    val currentLocalTime: Date = calendar.time
    //val date: DateFormat = SimpleDateFormat("z", Locale.getDefault())
    val date: DateFormat = SimpleDateFormat("ZZZZZ", Locale.getDefault())

    return date.format(currentLocalTime).removePrefix("GMT")
}