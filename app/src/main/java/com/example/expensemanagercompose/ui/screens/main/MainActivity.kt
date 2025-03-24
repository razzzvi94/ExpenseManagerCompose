package com.example.expensemanagercompose.ui.screens.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.expensemanagercompose.R
import com.example.expensemanagercompose.data.BottomBarItem
import com.example.expensemanagercompose.data.Screens
import com.example.expensemanagercompose.ui.screens.budget.Budget
import com.example.expensemanagercompose.ui.screens.expenses.Expenses
import com.example.expensemanagercompose.ui.theme.ExpenseManagerComposeTheme
import com.example.expensemanagercompose.ui.theme.GreenBackground
import com.example.expensemanagercompose.ui.theme.OrangeText
import com.example.expensemanagercompose.ui.theme.WhiteText

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExpenseManagerComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainActivityContent(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MainActivityContent(modifier: Modifier = Modifier) {
    MainBottomAppBar()
}

@Composable
fun MainBottomAppBar() {
    val navigationController = rememberNavController()
    val selected = remember { mutableIntStateOf(R.drawable.ic_budget) }

    val items = listOf(
        BottomBarItem("Budget", R.drawable.ic_budget, Screens.Budget.screen),
        BottomBarItem("Expenses", R.drawable.ic_expenses, Screens.Expenses.screen)
    )

    Scaffold(
        bottomBar = {
            BottomAppBar(containerColor = GreenBackground) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    items.forEach { item ->
                        BottomNavItem(
                            item = item,
                            isSelected = selected.intValue == item.iconRes,
                            onClick = {
                                selected.intValue = item.iconRes
                                navigationController.navigate(item.screen) {
                                    popUpTo(0)
                                }
                            },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navigationController,
            startDestination = Screens.Budget.screen,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screens.Budget.screen) { Budget() }
            composable(Screens.Expenses.screen) { Expenses() }
        }
    }
}

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

@Preview(showBackground = true)
@Composable
fun MainActivityPreview() {
    ExpenseManagerComposeTheme {
        MainActivityContent()
    }
}