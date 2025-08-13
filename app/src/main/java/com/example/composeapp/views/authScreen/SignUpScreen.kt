package com.example.composeapp.views.authScreen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.composeapp.R
import com.example.composeapp.appUtils.BottomNavRoutes
import com.example.composeapp.appUtils.Manrope
import com.example.composeapp.appUtils.Outfit
import com.example.composeapp.appUtils.Resource
import com.example.composeapp.coustom.CustomTextField
import com.example.composeapp.coustom.GradientButton
import com.example.composeapp.ui.theme.Pink40
import com.example.composeapp.ui.theme.PurpleGrey40
import com.example.composeapp.viewModel.AuthViewModel
import com.example.composeapp.viewModel.SessionViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(navController: NavHostController, sessionViewModel: SessionViewModel) {

    val viewModel: AuthViewModel = hiltViewModel()
    var showBottomSheet by remember { mutableStateOf(false) }

    val context = LocalContext.current

    val scrollState = rememberScrollState()
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val isEmailEmpty = email.isEmpty()
    var checked by remember { mutableStateOf(false) }

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
        confirmValueChange = { newState ->
            newState != SheetValue.Hidden  // Stop bottom sheet from hiding on outside press
        })

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
            .verticalScroll(scrollState)
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
                .padding(top = 20.dp, start = 14.dp),
            textAlign = TextAlign.Start,
            text = "Welcome to BTBY",
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
            text = "Please sign up to begin watching BTBY",
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
                .padding(top = 30.dp, bottom = 10.dp, start = 14.dp),
            textAlign = TextAlign.Start,
            text = "Name",
            letterSpacing = 2.sp,
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )
        )

        CustomTextField(
            value = name,
            onValueChange = { name = it },
            placeholder = "Enter your name",
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
            text = "Enter email",
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
            placeholder = "Enter email address",
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Next,
            leadingIcon = {
                Icon(
                    painter = painterResource(
                        id = if (isEmailEmpty) R.drawable.email_icon else R.drawable.email_filled_icon
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
            placeholder = "Enter Password",
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Next,
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
                .padding(top = 30.dp, bottom = 10.dp, start = 14.dp),
            textAlign = TextAlign.Start,
            text = "Confirm password",
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
            placeholder = "Enter confirm password",
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

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {

            Checkbox(
                checked = checked,
                onCheckedChange = { checked = it },
//                colors = CheckboxDefaults
            )
            Text(
                "By clicking here, I state that I have read and understood the terms of service of BTYB.",
                modifier = Modifier.padding(top = 20.dp),
                letterSpacing = 2.sp,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.White,
                )
            )
        }

        Box(
            modifier = Modifier
             //   .windowInsetsPadding(WindowInsets.navigationBars)
                .fillMaxSize()
                .padding(bottom = 10.dp, top = 40.dp) // optional bottom spacing
        ) {
            GradientButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .padding(horizontal = 10.dp)
                    .align(Alignment.BottomCenter),
                text = "Sign up",
                onClick = {
                    showBottomSheet = true
                },
                gradient = gradient
            )
        }
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
            },
            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
            containerColor = Color.Transparent,
            sheetState = sheetState,
            dragHandle = {}
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = (LocalConfiguration.current.screenHeightDp.dp * 0.8f))
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(PurpleGrey40, PurpleGrey40, Pink40)
                        ),
                        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                    )
            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {

                    Box(
                        modifier = Modifier
                            .size(width = 100.dp, height = 4.dp)
                            .align(Alignment.CenterHorizontally)
                            .background(
                                color = Color.White.copy(alpha = 0.8f),
                                shape = RoundedCornerShape(2.dp)
                            )
                    )
                    Spacer(modifier = Modifier.height(15.dp))

                    Text(
                        text = "Verification Code",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 26.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = Outfit
                        ),
                        modifier = Modifier.padding(top = 20.dp)
                    )
                    Text(
                        buildAnnotatedString {
                            append("We’ve sent you a six digit code to ")
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Normal,
                                    color = Color.White,
                                    fontFamily = Outfit,
                                    fontSize = 20.sp,

                                    )
                            ) {
                                append(email)
                            }
                        },
                        modifier = Modifier
                            .padding(top = 10.dp, start = 40.dp, end = 40.dp), style = TextStyle(
                            fontSize = 20.sp,
                            fontFamily = Outfit,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                    )
                    val otpGradient = Brush.horizontalGradient(
                        colors = listOf(
                            Color(color = 0xFFFBDA61),
                            Color(color = 0xFFFF5ACD)
                        )
                    )

                    val otpFields = remember { mutableStateListOf("", "", "", "", "", "") }
                    val focusRequesterList =
                        List(6) { FocusRequester() }

                    Row(
                        modifier = Modifier.padding(vertical = 30.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        otpFields.forEachIndexed { index, value ->
                            Box(
                                modifier = Modifier
                                    .background(
                                        color = Color(color = 0x26FFFFFF),
                                        shape = RoundedCornerShape(15.dp)
                                    )
                                    .border(2.dp, otpGradient, shape = RoundedCornerShape(15.dp))
                                    .size(52.dp)
                                    .offset(y = 12.dp),
                            ) {
                                BasicTextField(
                                    value = value,
                                    onValueChange = { input ->
                                        if (input.length <= 1) {
                                            otpFields[index] = input
                                            if (input.isNotEmpty() && index < otpFields.size - 1) {
                                                focusRequesterList[index + 1].requestFocus()
                                            }
                                        }
                                    },
                                    textStyle = TextStyle(
                                        color = Color.White,
                                        fontSize = 22.sp,
                                        textAlign = TextAlign.Center,
                                        fontWeight = FontWeight.Medium,
                                        fontFamily = Outfit
                                    ),
                                    keyboardOptions = KeyboardOptions(
                                        keyboardType = KeyboardType.Number
                                    ),
                                    keyboardActions = KeyboardActions(
                                        onPrevious = {
                                            if (value.isEmpty() && index > 0) {
                                                focusRequesterList[index - 1].requestFocus()
                                            }
                                        }
                                    ),
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .focusRequester(focusRequesterList[index])
                                        .onKeyEvent { keyEvent ->
                                            if (keyEvent.type == KeyEventType.KeyUp && keyEvent.key == Key.Backspace) {
                                                if (value.isEmpty() && index > 0) {
                                                    focusRequesterList[index - 1].requestFocus()
                                                }
                                                true
                                            } else {
                                                false
                                            }
                                        },

                                    )
                            }
                        }
                    }

                    Text(
                        text = "Resend ?", style = TextStyle(
                            brush = otpGradient,
                            fontSize = 19.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = Manrope
                        ),
                        modifier = Modifier.clickable {

                        }
                    )
                    Spacer(modifier = Modifier.padding(top = 25.dp))
                    GradientButton(
                        text = "Continue",
                        onClick = {
                            val otpString = otpFields.joinToString("")
                            val otpInt = otpString.toIntOrNull()

                            if (otpInt != null) {
                     //           viewModel.sendOtp(email, otpInt)
                            } else {
                                Toast.makeText(
                                    context,
                                    "Invalid OTP. Please enter numbers only.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        },

                        gradient = otpGradient
                    )


                }
            }
        }
    }

}