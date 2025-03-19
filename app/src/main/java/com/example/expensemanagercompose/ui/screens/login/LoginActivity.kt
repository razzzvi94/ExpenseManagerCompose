package com.example.expensemanagercompose.ui.screens.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.expensemanagercompose.R
import com.example.expensemanagercompose.ui.screens.addAction.CustomTextField
import com.example.expensemanagercompose.ui.theme.BlackText
import com.example.expensemanagercompose.ui.theme.ExpenseManagerComposeTheme
import com.example.expensemanagercompose.ui.theme.GreenBackground
import com.example.expensemanagercompose.ui.theme.OrangeText
import com.example.expensemanagercompose.ui.theme.WhiteText
import com.example.expensemanagercompose.ui.theme.space_8dp
import com.example.expensemanagercompose.ui.theme.space_98dp
import com.example.expensemanagercompose.ui.theme.textSize_16sp
import com.example.expensemanagercompose.ui.theme.textSize_18sp

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExpenseManagerComposeTheme {
                val viewModel: LoginViewModel = hiltViewModel()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginScreen(modifier = Modifier.padding(innerPadding), viewModel)
                }
            }
        }
    }
}

@Composable
fun LoginScreen(modifier: Modifier = Modifier, viewModel: LoginViewModel) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(GreenBackground)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AppLogo()
        LoginMessage()
        UserInputFields(viewModel)
        RegisterButton()
        ForgotPasswordText()
        LoginPrompt()
    }
}

@Composable
fun AppLogo() {
    Image(
        painter = painterResource(id = R.drawable.ic_logo),
        contentDescription = "Application logo",
        modifier = Modifier.size(space_98dp)
    )
}

@Composable
fun LoginMessage() {
    Text(
        modifier = Modifier.padding(0.dp, 45.dp, 0.dp, 0.dp),
        color = BlackText,
        fontSize = textSize_18sp,
        textAlign = TextAlign.Center,
        text = "Enter your credentials to become again active"
    )
}

@Composable
fun UserInputFields(viewModel: LoginViewModel) {
    val userName by viewModel.userName.collectAsState()
    val userEmail by viewModel.userEmail.collectAsState()
    val userPassword by viewModel.userPassword.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 27.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CustomTextField(
            value = userName,
            onValueChange = { viewModel.onUserNameChange(it) },
            label = "Name",
            keyboardType = KeyboardType.Text
        )
        CustomTextField(
            value = userEmail,
            onValueChange = { viewModel.onUserEmailChange(it) },
            label = "Email", keyboardType = KeyboardType.Email
        )
        CustomTextField(
            value = userPassword,
            onValueChange = { viewModel.onUserPasswordChange(it) },
            label = "Password",
            keyboardType = KeyboardType.Password
        )
    }
}

@Composable
fun RegisterButton() {
    Button(
        onClick = { },
        modifier = Modifier.size(width = 176.dp, height = 45.dp),
        colors = ButtonDefaults.buttonColors(OrangeText),
        shape = RoundedCornerShape(space_8dp)
    ) {
        Text(
            text = "Register",
            color = WhiteText,
            fontSize = textSize_18sp,
            fontFamily = FontFamily(Font(R.font.montserrat_regular)),
        )
    }
}

@Composable
fun ForgotPasswordText() {
    Text(
        modifier = Modifier.padding(0.dp, 53.dp, 0.dp, 0.dp),
        color = OrangeText,
        fontSize = textSize_16sp,
        textAlign = TextAlign.Center,
        text = "Forgot your password?"
    )
}

@Composable
fun LoginPrompt() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 11.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            color = BlackText,
            fontSize = textSize_16sp,
            textAlign = TextAlign.Center,
            text = "Have already an account?"
        )
        Spacer(modifier = Modifier.width(30.dp))
        Text(
            color = OrangeText,
            fontSize = textSize_16sp,
            textAlign = TextAlign.Center,
            text = "Login"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoginActivityPreview() {
    ExpenseManagerComposeTheme {
        val viewModel: LoginViewModel = hiltViewModel()
        LoginScreen(modifier = Modifier, viewModel)
    }
}