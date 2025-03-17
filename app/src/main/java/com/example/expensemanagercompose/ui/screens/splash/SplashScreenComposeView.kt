package com.example.expensemanagercompose.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.example.expensemanagercompose.R
import com.example.expensemanagercompose.ui.theme.GreenBackground
import com.example.expensemanagercompose.ui.theme.WhiteText
import com.example.expensemanagercompose.ui.theme.space_144dp
import com.example.expensemanagercompose.ui.theme.textSize_20sp

@Composable
fun FullscreenSplash(name: String, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenBackground),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painterResource(id = R.drawable.ic_logo),
                "Application logo",
                modifier = modifier.size(space_144dp)
            )
            Text(
                text = name,
                color = WhiteText,
                fontSize = textSize_20sp,
                fontFamily = FontFamily(Font(R.font.montserrat_regular)),
                modifier = modifier
            )
        }
    }
}