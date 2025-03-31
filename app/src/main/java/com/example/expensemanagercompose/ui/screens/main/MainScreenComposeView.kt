package com.example.expensemanagercompose.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.example.expensemanagercompose.R
import com.example.expensemanagercompose.data.BottomBarItem
import com.example.expensemanagercompose.ui.theme.BlackText
import com.example.expensemanagercompose.ui.theme.GreenBackground
import com.example.expensemanagercompose.ui.theme.OrangeText
import com.example.expensemanagercompose.ui.theme.WhiteText

@Composable
fun BottomNavItem(
    item: BottomBarItem,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = item.iconRes),
            contentDescription = null,
            modifier = Modifier.size(26.dp),
            tint = if (isSelected) OrangeText else WhiteText
        )
        Text(
            text = item.label,
            fontFamily = FontFamily(Font(R.font.montserrat_regular)),
            color = if (isSelected) OrangeText else WhiteText,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Composable
fun DrawerHeader() {
    Box(
        modifier = Modifier
            .background(GreenBackground)
            .fillMaxWidth()
            .height(150.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "User Name", color = BlackText)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(title: String, onMenuClick: () -> Unit) {
    TopAppBar(
        title = { Text(text = title) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = WhiteText,
            titleContentColor = BlackText,
            navigationIconContentColor = WhiteText
        ),
        navigationIcon = {
            IconButton(onClick = onMenuClick) {
                Icon(Icons.Rounded.Menu, contentDescription = "Menu", tint = BlackText)
            }
        }
    )
}