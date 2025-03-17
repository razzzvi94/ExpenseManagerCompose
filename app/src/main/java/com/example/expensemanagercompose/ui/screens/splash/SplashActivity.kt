package com.example.expensemanagercompose.ui.screens.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.expensemanagercompose.R
import com.example.expensemanagercompose.ui.screens.main.MainActivity
import com.example.expensemanagercompose.ui.theme.ExpenseManagerComposeTheme

@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExpenseManagerComposeTheme {
                val viewModel: SplashScreenViewModel = hiltViewModel()
                val navigateToHome by viewModel.navigateToHome.collectAsState()

                navigateToMainActivity(navigateToHome)

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    FullscreenSplash(
                        name = resources.getString(R.string.str_spend_with_brain),
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    @Composable
    private fun navigateToMainActivity(navigateToHome: Boolean) {
        LaunchedEffect(navigateToHome) {
            if (navigateToHome) {
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ExpenseManagerComposeTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            FullscreenSplash(
                name = "Spend with brain preview",
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}