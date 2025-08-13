package com.example.composeapp.views.authScreen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.composeapp.coustom.CustomTextField
import com.example.composeapp.R
import com.example.composeapp.appUtils.BottomNavRoutes
import com.example.composeapp.appUtils.Resource
import com.example.composeapp.appUtils.isNetworkAvailable
import com.example.composeapp.coustom.GradientButton
import com.example.composeapp.dataStore.preference.PrefKeys
import com.example.composeapp.dataStore.preference.PreferenceDataStoreModule
import com.example.composeapp.viewModel.AuthViewModel

@Composable
fun LogInScreen(navController: NavHostController, ) {

    val context = LocalContext.current
    val viewModel: AuthViewModel = hiltViewModel()
    val preferenceDataStoreModule= PreferenceDataStoreModule(context)

    var email by remember { mutableStateOf("") }
    val isEmailEmpty = email.isEmpty()
    var password by remember { mutableStateOf("") }
    var passwordVisibility: Boolean by remember { mutableStateOf(false) }

    val loginState by viewModel.loginState.collectAsState()


    var loginInitiated by remember { mutableStateOf(false) }

    val gradient = Brush.horizontalGradient(
        colors = listOf(
            Color(color = 0xFF4C57CF),
            Color(color = 0xFFC850C0),
            Color(color = 0xFFFFCC70)
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF3C2041),
                        Color(0xFFCBC6C7),
                        Color(0xFF184C65)
                    )
                )
            )
    ) {
        Text(
            modifier = Modifier
                .windowInsetsPadding(WindowInsets.systemBars)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = "Brought To You By",
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                brush = Brush.linearGradient(
                    colors = listOf(Color.Red, Color.Yellow)
                )
            )
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, start = 14.dp),
            textAlign = TextAlign.Start,
            text = "Sign in to your\nAccount",
            letterSpacing = 2.sp,
            style = TextStyle(
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 14.dp),
            textAlign = TextAlign.Start,
            text = "Enter your email and password to login ",
            letterSpacing = 2.sp,
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color.White,
            )
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, bottom = 10.dp, start = 14.dp) ,
            textAlign = TextAlign.Start,
            text = "Email address",
            letterSpacing = 2.sp,
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )
        )

        CustomTextField(
            value = email,
            onValueChange = { email = it },
            placeholder = "Enter email address",
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next,
            leadingIcon = {
                Icon(
                    painter = painterResource(
                        id = if (isEmailEmpty) R.drawable.email_icon else R.drawable.email_filled_icon
                    ),
                    tint = Color.White.copy(alpha = 0.3f),
                    contentDescription = null,
                    modifier = Modifier.size(21.dp)
                )
            },
            modifier = Modifier
                .height(55.dp)
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            singleLine = true
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, bottom = 10.dp, start = 14.dp),
            textAlign = TextAlign.Start,
            text = "Password",
            letterSpacing = 2.sp,
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )
        )

        CustomTextField(
            value = password,
            onValueChange = { password = it },
            placeholder = "Enter your password",
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done,
            leadingIcon = {
                Icon(
                    painter = painterResource(
                        id = if (isEmailEmpty) R.drawable.lock_icon else R.drawable.lock_filled_icon
                    ),
                    tint = Color.White.copy(alpha = 0.5f),
                    contentDescription = null,
                    modifier = Modifier.size(21.dp)
                )
            },
            modifier = Modifier
                .height(55.dp)
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            singleLine = true
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 14.dp, end = 14.dp),
            textAlign = TextAlign.End,
            text = "Forgot Password?",
            letterSpacing = 2.sp,
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )
        )

        Box(
            modifier = Modifier
                //      .windowInsetsPadding(WindowInsets.navigationBars)
                .fillMaxSize()
                .padding(bottom = 10.dp) // optional bottom spacing
        ) {
            GradientButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .padding(horizontal = 10.dp)
                    .align(Alignment.BottomCenter),
                text = "Log In",
                onClick = {
                    loginInitiated = true
                    viewModel.login(email, password)
                },
                gradient = gradient
            )
            {
                if (loginInitiated) {
                    CircularProgressIndicator(
                        color = Color.White,
                        strokeWidth = 2.dp,
                        modifier = Modifier.size(20.dp)
                    )
                } else {
                    Text(
                        text = "Log In",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily(Font(R.font.outfit_bold))
                    )
                }
            }
        }

        LaunchedEffect(loginState) {
            if (!loginInitiated) return@LaunchedEffect
            when (loginState) {
                is Resource.Success -> {
                    val response = loginState as Resource.Success
                    if (response.code == 200) {
                        preferenceDataStoreModule.putPreference(PrefKeys.IS_LOGIN, true)
                        navController.navigate(BottomNavRoutes.Home.route) {
                            popUpTo("login") { inclusive = true }
                        }
                        viewModel.savePref(response.data!!.userData[0].auth_key)

                        Toast.makeText(context, response.message, Toast.LENGTH_SHORT).show()
                    } else if (response.code == 400) {
                        Toast.makeText(context, response.message, Toast.LENGTH_SHORT).show()
                    }
                    loginInitiated = false
                }

                is Resource.Error -> {
                    Toast.makeText(context, loginState.message, Toast.LENGTH_SHORT).show()
                    loginInitiated = false
                }

                is Resource.InternetError -> {
                    if (!isNetworkAvailable(context)) {
                        Toast.makeText(context, "Check your internet connection.", Toast.LENGTH_SHORT).show()
                    }
                    loginInitiated = false
                }
                else -> Unit
            }
        }
    }
}