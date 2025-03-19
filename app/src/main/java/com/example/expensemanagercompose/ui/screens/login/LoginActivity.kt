package com.example.expensemanagercompose.ui.screens.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.expensemanagercompose.R
import com.example.expensemanagercompose.ui.screens.addAction.CustomTextField
import com.example.expensemanagercompose.ui.theme.BlackText
import com.example.expensemanagercompose.ui.theme.ExpenseManagerComposeTheme
import com.example.expensemanagercompose.ui.theme.GreenBackground
import com.example.expensemanagercompose.ui.theme.space_98dp
import com.example.expensemanagercompose.ui.theme.textSize_14sp
import com.example.expensemanagercompose.ui.theme.textSize_18sp

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExpenseManagerComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginActivityContent(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun LoginActivityContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(GreenBackground)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painterResource(id = R.drawable.ic_logo),
            "Application logo",
            modifier = modifier.size(space_98dp)
        )
        Text(
            modifier = modifier,
            color = BlackText,
            fontSize = textSize_18sp,
            textAlign = TextAlign.Center,
            text = "Enter your credentials to become again active"
        )
        SimpleOutlinedTextFieldSample(modifier)
    }
}

@Composable
fun SimpleOutlinedTextFieldSample(modifier: Modifier) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CustomTextField(
            value = name,
            onValueChange = { name = it },
            label = "Name",
            keyboardType = KeyboardType.Text,
            cornerRadius = 8.dp,
            height = 76.dp
        )

        CustomTextField(
            value = email,
            onValueChange = { email = it },
            label = "Email",
            keyboardType = KeyboardType.Email,
            cornerRadius = 8.dp,
            height = 76.dp
        )

        CustomTextField(
            value = password,
            onValueChange = { password = it },
            label = "Password",
            keyboardType = KeyboardType.Password,
            cornerRadius = 8.dp,
            height = 76.dp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoginActivityPreview() {
    ExpenseManagerComposeTheme {
        LoginActivityContent()
    }
}