package com.example.composeapp.views.dashBoard

import android.os.Build
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.composeapp.appUtils.GradientBorderText
import com.example.composeapp.ui.theme.BlueGradient
import com.example.composeapp.ui.theme.PinkGradient
import com.example.composeapp.ui.theme.YellowGradient
import com.example.composeapp.R
import com.example.composeapp.appUtils.Resource
import com.example.composeapp.appUtils.getTimeOffset
import com.example.composeapp.appUtils.isNetworkAvailable
import com.example.composeapp.dataStore.preference.PrefKeys
import com.example.composeapp.dataStore.preference.PreferenceDataStoreModule
import com.example.composeapp.model.UpdateDeviceTokenReq
import com.example.composeapp.viewModel.AuthViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@Composable
fun HomeDashBoard(navController: NavHostController) {

    val context = LocalContext.current
    val viewModel: AuthViewModel = hiltViewModel()

    val preferenceDataStoreModule = PreferenceDataStoreModule(context)
    var email by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

    val updateDeviceState by viewModel.updateVersionState.collectAsState()

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Home Screen")

            viewModel.updateDevice(
                UpdateDeviceTokenReq(
                    "ABC",
                    "A",
                    getTimeOffset(),
                    Build.MODEL,
                    Build.VERSION.SDK_INT.toString()
                )
            )

            Button(onClick = {
                scope.launch {
                    preferenceDataStoreModule.putPreference(PrefKeys.IS_LOGIN, false)
                    preferenceDataStoreModule.putPreference(PrefKeys.AUTH_KEY, "")
                    navController.navigate("login") {
                        popUpTo(0) { inclusive = true }
                    }
                }
            }) {
                Text("Logout")
            }
        }
    }

    LaunchedEffect(updateDeviceState) {
        when (updateDeviceState) {
            is Resource.Success -> {
                val response = updateDeviceState as Resource.Success
                if (response.code == 200) {
                    Toast.makeText(context, "Successfull Hit", Toast.LENGTH_SHORT).show()
                }
            }

            is Resource.Error -> {
                Toast.makeText(context, updateDeviceState.message, Toast.LENGTH_SHORT).show()
            }

            is Resource.InternetError -> {
                if (!isNetworkAvailable(context)) {
                    Toast.makeText(context, "Check your internet connection.", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            else -> Unit
        }
    }

}
