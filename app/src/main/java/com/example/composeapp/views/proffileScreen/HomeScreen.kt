package com.example.composeapp.views.proffileScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.composeapp.dataStore.preference.PrefKeys
import com.example.composeapp.dataStore.preference.PreferenceDataStoreModule
import com.example.composeapp.viewModel.SessionViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavHostController, sessionViewModel: SessionViewModel) {

    val context = LocalContext.current
    val preferenceDataStoreModule = PreferenceDataStoreModule(context)
    var email by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Home Screen")

            Button(onClick = {
                scope.launch {
                    preferenceDataStoreModule.putPreference(PrefKeys.IS_LOGIN, false)
                    navController.navigate("login") {
                        popUpTo(0) { inclusive = true }
                    }
                }
            }) {
                Text("Logout")
            }
        }
    }
}