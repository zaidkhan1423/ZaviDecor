package com.zaid.zavi.feature_auth.presentation.register_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.zaid.zavi.core.utils.AppIcons
import com.zaid.zavi.theme.ZaviDecorTheme
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    navController: NavController,
    uiState: RegisterScreenUiState,
    onEvent: (RegisterScreenUiEvent) -> Unit,
    onShowSnackBar: suspend (message: String, actionLabel: String?, duration: SnackbarDuration) -> Boolean
) {

    LaunchedEffect(uiState) {
        if (uiState.snackBarMessage != null) {
            onShowSnackBar(uiState.snackBarMessage, null, SnackbarDuration.Short)
            onEvent(RegisterScreenUiEvent.OnMessageDisplayed)
        }
    }
    LaunchedEffect(uiState) {
        delay(1500L)
        if (uiState.shouldNavigate) {
            navController.popBackStack()
        }
    }

    var showPassword by remember { mutableStateOf(false) }
    var showConfirmPassword by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(15.dp))
            Text(
                text = "Join",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                fontWeight = FontWeight.W600
            )
            Spacer(modifier = Modifier.size(12.dp))
            Text(
                text = "Create your Account",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                fontWeight = FontWeight.W700
            )

            Spacer(modifier = Modifier.size(15.dp))

            Text(
                text = "Full Name",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontWeight = FontWeight.W600,
                modifier = Modifier
                    .widthIn(500.dp)
                    .padding(horizontal = 30.dp)
            )
            Spacer(modifier = Modifier.size(8.dp))
            OutlinedTextField(
                value = uiState.name,
                onValueChange = {
                    onEvent(RegisterScreenUiEvent.OnNameChange(it))
                    onEvent(RegisterScreenUiEvent.NameEmptyStateChanged(false))
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
                    keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
                ),
                placeholder = {
                    Text(
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                        text = "Enter your name"
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
                if (uiState.isNameBlank) {
                    Text(
                        text = "Name cannot be empty",
                        color = MaterialTheme.colorScheme.error,
                        fontSize = MaterialTheme.typography.bodySmall.fontSize,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Text(
                text = "Email",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontWeight = FontWeight.W600,
                modifier = Modifier
                    .widthIn(500.dp)
                    .padding(horizontal = 30.dp)
            )
            Spacer(modifier = Modifier.size(8.dp))
            OutlinedTextField(
                value = uiState.email,
                onValueChange = {
                    onEvent(RegisterScreenUiEvent.OnEmailChange(it))
                    onEvent(RegisterScreenUiEvent.EmailEmptyStateChanged(false))
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
                    keyboardType = KeyboardType.Email, imeAction = ImeAction.Next
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
                if (uiState.isEmailBlank) {
                    Text(
                        text = "Email cannot be empty",
                        color = MaterialTheme.colorScheme.error,
                        fontSize = MaterialTheme.typography.bodySmall.fontSize,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Text(
                text = "Password",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontWeight = FontWeight.W600,
                modifier = Modifier
                    .widthIn(500.dp)
                    .padding(horizontal = 30.dp)
            )
            Spacer(modifier = Modifier.size(8.dp))

            OutlinedTextField(
                value = uiState.password,
                onValueChange = {
                    onEvent(RegisterScreenUiEvent.OnPasswordChange(it))
                    onEvent(RegisterScreenUiEvent.PasswordEmptyStateChanged(false))
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
                    keyboardType = KeyboardType.Password, imeAction = ImeAction.Next
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
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize, text = "Password"
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
                if (uiState.isPasswordBlank) {
                    Text(
                        text = "Password cannot be empty",
                        color = MaterialTheme.colorScheme.error,
                        fontSize = MaterialTheme.typography.bodySmall.fontSize,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Text(
                text = "Confirm password",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontWeight = FontWeight.W600,
                modifier = Modifier
                    .widthIn(500.dp)
                    .padding(horizontal = 30.dp)
            )
            Spacer(modifier = Modifier.size(8.dp))

            OutlinedTextField(
                value = uiState.confirmPassword,
                onValueChange = {
                    onEvent(RegisterScreenUiEvent.OnConfirmPasswordChange(it))
                    onEvent(RegisterScreenUiEvent.PasswordMatchStateChanged(uiState.password == uiState.confirmPassword))
                },
                singleLine = true,
                textStyle = TextStyle(
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize
                ),
                trailingIcon = {
                    IconButton(onClick = { showConfirmPassword = !showConfirmPassword }) {
                        Icon(
                            painter = painterResource(id = if (showConfirmPassword) AppIcons.VisibilityOn else AppIcons.VisibilityOff),
                            contentDescription = "toggle_show_password"
                        )
                    }
                },
                visualTransformation = if (showConfirmPassword) VisualTransformation.None else PasswordVisualTransformation(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                ),
                shape = RoundedCornerShape(10.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password, imeAction = ImeAction.Done
                ),
                placeholder = {
                    Text(
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                        text = "Confirm password"
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
                if (!uiState.isPasswordMatch) {
                    Text(
                        text = "Passwords do not match",
                        color = MaterialTheme.colorScheme.error,
                        fontSize = MaterialTheme.typography.bodySmall.fontSize,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.size(10.dp))

            Box(
                modifier = Modifier
                    .widthIn(500.dp)
                    .padding(horizontal = 28.dp)
            ) {
                Button(
                    onClick = {
                        onEvent(RegisterScreenUiEvent.OnRegisterUserClick)
                    },
                    modifier = Modifier
                        .widthIn(150.dp)
                        .height(48.dp),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    if (uiState.loading) {
                        CircularProgressIndicator(
                            color = MaterialTheme.colorScheme.background,
                            modifier = Modifier.size(35.dp)
                        )
                    } else {
                        Text(
                            text = "Sign Up", fontSize = MaterialTheme.typography.bodyLarge.fontSize, color = MaterialTheme.colorScheme.background
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.size(30.dp))

            Row {
                Text(
                    text = "Already have an account? ",
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    fontWeight = FontWeight.W700
                )
                Text(text = "Log In",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    fontWeight = FontWeight.W700,
                    modifier = Modifier.clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        navController.popBackStack()
                    })
            }
            Spacer(modifier = Modifier.size(30.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    ZaviDecorTheme {
        RegisterScreen(
            navController = NavController(LocalContext.current),
            uiState = RegisterScreenUiState(),
            onEvent = {},
            onShowSnackBar = { _, _, _ -> false }
        )
    }
}