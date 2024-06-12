package com.zaid.zavi.feature_auth.presentation.login_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.zaid.zavi.core.navigation.NavGraphRoutes
import com.zaid.zavi.core.navigation.Screen
import com.zaid.zavi.core.utils.AppIcons
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavController,
    uiState: LoginScreenUiState,
    loginUser: (String, String) -> Unit,
    resetPassword: (String) -> Unit,
    onShowSnackBar: suspend (message: String, actionLabel: String?, duration: SnackbarDuration) -> Boolean
) {

    LaunchedEffect(uiState) {
        if (uiState.snackBarMessage != null) {
            onShowSnackBar(uiState.snackBarMessage, null, SnackbarDuration.Short)
        }

        if (uiState.shouldNavigate) {
            navController.navigate(Screen.HomeScreen.route) {
                popUpTo(NavGraphRoutes.AUTH) {
                    inclusive = true
                }
            }
        }
    }

    var userEmail by rememberSaveable { mutableStateOf("") }
    var isEmailEmpty by rememberSaveable { mutableStateOf(false) }

    var userPassword by rememberSaveable { mutableStateOf("") }
    var showPassword by rememberSaveable { mutableStateOf(false) }
    var isPasswordEmpty by rememberSaveable { mutableStateOf(false) }

    var showResetPasswordDialog by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(uiState) {
        if (uiState.shouldHideResetPasswordDialog) {
            delay(1000L)
            showResetPasswordDialog = false
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(20.dp))
            Text(
                text = "Login",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                fontWeight = FontWeight.W600
            )
            Spacer(modifier = Modifier.size(18.dp))
            Text(
                text = "Welcome Back",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                fontWeight = FontWeight.W700
            )

            Spacer(modifier = Modifier.size(20.dp))

            OutlinedTextField(
                value = userEmail,
                onValueChange = {
                    userEmail = it
                    isEmailEmpty = false
                },
                singleLine = true,
                textStyle = TextStyle(
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                ),
                shape = RoundedCornerShape(10.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                placeholder = {
                    Text(
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                        text = "Enter your email"
                    )
                },
                modifier = Modifier
                    .widthIn(500.dp)
                    .padding(horizontal = 28.dp)
            )

            Box(
                modifier = Modifier
                    .widthIn(500.dp)
                    .padding(horizontal = 30.dp)
                    .height(21.dp)
            ) {
                if (isEmailEmpty) {
                    Text(
                        text = "Email cannot be empty",
                        color = MaterialTheme.colorScheme.error,
                        fontSize = MaterialTheme.typography.bodySmall.fontSize,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.size(14.dp))

            OutlinedTextField(
                value = userPassword,
                onValueChange = {
                    userPassword = it
                    isPasswordEmpty = false
                },
                singleLine = true,
                textStyle = TextStyle(
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                ),
                shape = RoundedCornerShape(10.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                trailingIcon = {
                    IconButton(onClick = { showPassword = !showPassword }) {
                        Icon(
                            painter = painterResource(id = if (showPassword) AppIcons.VisibilityOn else AppIcons.VisibilityOff),
                            contentDescription = "toggle_show_password"
                        )
                    }
                },
                visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                placeholder = {
                    Text(
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                        text = "Password"
                    )
                },
                modifier = Modifier
                    .widthIn(500.dp)
                    .padding(horizontal = 28.dp)
            )

            Box(
                modifier = Modifier
                    .widthIn(500.dp)
                    .padding(horizontal = 30.dp)
                    .height(21.dp)
            ) {
                if (isPasswordEmpty) {
                    Text(
                        text = "Password cannot be empty",
                        color = MaterialTheme.colorScheme.error,
                        fontSize = MaterialTheme.typography.bodySmall.fontSize,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.size(4.dp))

            Box(
                modifier = Modifier
                    .widthIn(500.dp)
                    .padding(horizontal = 30.dp)
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .clickable {
                            showResetPasswordDialog = true
                        },
                    text = "Forgot Password?",
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize
                )
            }
            Spacer(modifier = Modifier.size(20.dp))

            Button(
                onClick = {
                    when {
                        userEmail.isEmpty() && userPassword.isEmpty() -> {
                            isEmailEmpty = true
                            isPasswordEmpty = true
                        }

                        userEmail.isEmpty() -> {
                            isEmailEmpty = true
                        }

                        userPassword.isEmpty() -> {
                            isPasswordEmpty = true
                        }

                        else -> {
                            loginUser(userEmail, userPassword)
                        }
                    }
                },
                modifier = Modifier
                    .widthIn(500.dp)
                    .height(48.dp)
                    .padding(horizontal = 28.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                if (uiState.loginLoading) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.background,
                        modifier = Modifier.size(35.dp)
                    )
                } else {
                    Text(text = "Log In", fontSize = MaterialTheme.typography.bodyLarge.fontSize)
                }
            }

            Spacer(modifier = Modifier.size(80.dp))
            Row {
                Text(
                    text = "New User ",
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    fontWeight = FontWeight.W700
                )
                Text(
                    text = "Sign Up",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    fontWeight = FontWeight.W700,
                    modifier = Modifier.clickable {
                        navController.navigate(Screen.RegisterScreen.route)
                    }
                )
            }

            Spacer(modifier = Modifier.size(30.dp))

            var userEmailForReset by remember { mutableStateOf("") }
            var isEmailEmptyForReset by remember { mutableStateOf(false) }

            if (showResetPasswordDialog) {
                Dialog(onDismissRequest = {
                    showResetPasswordDialog = false
                }) {
                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        color = MaterialTheme.colorScheme.surface
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 24.dp, bottom = 24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                text = "Reset Password",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "Enter your email address to receive\na password reset link.",
                                style = MaterialTheme.typography.bodyMedium,
                                textAlign = TextAlign.Center
                            )

                            Spacer(modifier = Modifier.height(15.dp))

                            OutlinedTextField(
                                value = userEmailForReset,
                                onValueChange = {
                                    userEmailForReset = it
                                    isEmailEmptyForReset = false
                                },
                                singleLine = true,
                                textStyle = TextStyle(
                                    fontSize = MaterialTheme.typography.bodyLarge.fontSize
                                ),
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    focusedBorderColor = Color.Transparent,
                                    unfocusedBorderColor = Color.Transparent,
                                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                                ),
                                shape = RoundedCornerShape(10.dp),
                                keyboardOptions = KeyboardOptions.Default.copy(
                                    keyboardType = KeyboardType.Email,
                                    imeAction = ImeAction.Done
                                ),
                                placeholder = {
                                    Text(
                                        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                                        text = "Enter your email"
                                    )
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 28.dp)
                            )

                            Box(
                                modifier = Modifier
                                    .height(21.dp)
                            ) {
                                if (isEmailEmptyForReset) {
                                    Text(
                                        text = "Email cannot be empty",
                                        color = MaterialTheme.colorScheme.error,
                                        fontSize = MaterialTheme.typography.bodySmall.fontSize,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.size(5.dp))

                            Button(
                                onClick = {
                                    if (userEmailForReset.isEmpty()) {
                                        isEmailEmptyForReset = true
                                    } else {
                                        resetPassword(userEmailForReset)
                                    }
                                },
                                modifier = Modifier
                                    .height(48.dp)
                                    .padding(horizontal = 28.dp),
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                if (uiState.resetPasswordLoading) {
                                    CircularProgressIndicator(
                                        color = MaterialTheme.colorScheme.background,
                                        modifier = Modifier.size(35.dp)
                                    )
                                } else {
                                    Text(
                                        text = "Submit",
                                        fontSize = MaterialTheme.typography.bodyLarge.fontSize
                                    )
                                }
                            }
                        }
                    }
                }
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        navController = NavController(LocalContext.current),
        uiState = LoginScreenUiState(),
        loginUser = { _, _ -> },
        onShowSnackBar = { _, _, _ -> false },
        resetPassword = { _ -> }
    )
}