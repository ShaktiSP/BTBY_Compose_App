package com.example.composeapp.appUtils

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.systemGestureExclusion
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.composeapp.ui.theme.PinkGradient
import com.example.composeapp.ui.theme.YellowGradient
import com.example.composeapp.R
import com.example.composeapp.views.dashBoard.HomeDashBoard

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomNavigationBar(navController: NavHostController) {

    val bottomNavDestinations = setOf(
        BottomNavRoutes.Home.route,
        BottomNavRoutes.Explore.route,
        BottomNavRoutes.Inbox.route,
        BottomNavRoutes.Profile.route,

    )

    val items = BottomNavigationItem().bottomNavigationItems()
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination?.route

    if (currentDestination in bottomNavDestinations) {
        NavigationBar(
            modifier = Modifier.systemGestureExclusion().height(105.dp),
            containerColor = colorResource(R.color.trans_black),
            tonalElevation = 0.dp
        ) {
            items.forEachIndexed { index, navigationItem ->
                val isSelected = navigationItem.route == currentDestination
                NavigationBarItem(
                    selected = isSelected,
                    label = {
                        if (index != 2) GradientTextLabel(
                            text = navigationItem.label,
                            isSelected = isSelected
                        ) else null
                    },
                    alwaysShowLabel = index != 2,
                    icon = {
                        Image(
                            painter = if (isSelected) painterResource(
                                id = navigationItem.selectedIcon
                            ) else painterResource(id = navigationItem.unselectedIcon),
                            contentDescription = navigationItem.label
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color.Transparent
                    ),
                    onClick = {
                        if (currentDestination != navigationItem.route) {
                            navController.navigate(navigationItem.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun GradientTextLabel(text: String, isSelected: Boolean) {
    if (isSelected) {
        // Gradient Text for selected label
        GradientText(
            text = text,
            gradient = Brush.horizontalGradient(
                colors = listOf(PinkGradient, PinkGradient, YellowGradient)
            )
        )
    } else {
        // White text for unselected label
        Text(
            text = text,
            color = Color.White
        )
    }
}

@Composable
fun GradientText(
    text: String,
    gradient: Brush
) {
    Text(
        text = text,
        modifier = Modifier
            .graphicsLayer(alpha = 0.99f) // Necessary to apply shader
            .drawWithContent {
                drawContent()
                drawRect(brush = gradient, blendMode = BlendMode.SrcIn)
            }
    )
}