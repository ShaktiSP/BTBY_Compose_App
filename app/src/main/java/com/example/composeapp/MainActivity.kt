package com.example.composeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composeapp.appUtils.BottomNavRoutes
import com.example.composeapp.appUtils.BottomNavigationBar
import com.example.composeapp.viewModel.SessionViewModel
import com.example.composeapp.views.authScreen.LogInScreen
import com.example.composeapp.views.authScreen.SignUpScreen
import com.example.composeapp.views.authScreen.SplashScreen
import com.example.composeapp.views.dashBoard.ExploreDashBoard
import com.example.composeapp.views.onBoardingScreen.OnboardingScreen
import com.example.composeapp.dataStore.preference.PreferenceDataStoreModule
import com.example.composeapp.ui.theme.ComposeAppTheme
import com.example.composeapp.views.dashBoard.HomeDashBoard
import com.example.composeapp.views.dashBoard.InboxDashBoard
import com.example.composeapp.views.dashBoard.ProfileDashBoard
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import com.example.composeapp.views.dashBoard.UpLoadScreen

import kotlin.collections.contains
import kotlin.getValue

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val sessionViewModel by viewModels<SessionViewModel>()

    @Inject
    lateinit var storeModule: PreferenceDataStoreModule

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ComposeAppTheme {
                val navController = rememberNavController()
                val navBackStackEntry = navController.currentBackStackEntryAsState().value
                val currentRoute = navBackStackEntry?.destination?.route

                val bottomBarScreens = listOf(
                    BottomNavRoutes.Home.route,
                    BottomNavRoutes.Explore.route,
                    BottomNavRoutes.Upload.route,
                    BottomNavRoutes.Inbox.route,
                    BottomNavRoutes.Profile.route
                )

                val showBottomBar = currentRoute in bottomBarScreens

                Scaffold(
                    bottomBar = {
                        if (showBottomBar) {
                            BottomNavigationBar(navController = navController)
                        }
                    }
                ) { padding ->
                    NavHost(
                        navController = navController,
                        startDestination = "splash",
                        modifier = Modifier.padding()
                    ) {
                        composable("splash") {
                            SplashScreen(navController, sessionViewModel)
                        }
                        composable("onboarding") {
                            OnboardingScreen(navController)
                        }
                        composable("login") {
                            LogInScreen(navController)
                        }
                        composable("signUp") {
                            SignUpScreen(navController, sessionViewModel)
                        }
                        composable(BottomNavRoutes.Home.route) {
                            HomeDashBoard(navController)
                        }
                        composable(BottomNavRoutes.Explore.route) {
                            ExploreDashBoard(navController)
                        }
                        composable(BottomNavRoutes.Upload.route) {
                            UpLoadScreen(navController)
                        }
                        composable(BottomNavRoutes.Inbox.route) {
                            InboxDashBoard()
                        }
                        composable(BottomNavRoutes.Profile.route) {
                            ProfileDashBoard()
                        }
                    }
                }
            }
        }
    }
}
