package com.example.expensemanagercompose.ui.screens.main

import android.app.Activity
import android.graphics.Color.BLACK
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.expensemanagercompose.R
import com.example.expensemanagercompose.data.BottomBarItem
import com.example.expensemanagercompose.data.DrawerItem
import com.example.expensemanagercompose.data.Screens
import com.example.expensemanagercompose.ui.screens.budget.Budget
import com.example.expensemanagercompose.ui.screens.converter.Converter
import com.example.expensemanagercompose.ui.screens.expenses.Expenses
import com.example.expensemanagercompose.ui.theme.BlackText
import com.example.expensemanagercompose.ui.theme.ExpenseManagerComposeTheme
import com.example.expensemanagercompose.ui.theme.GreenBackground
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExpenseManagerComposeTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val view = LocalView.current
    val window = (view.context as Activity).window

    SideEffect {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = BLACK

        WindowInsetsControllerCompat(window, view).isAppearanceLightStatusBars = false
    }

    val navController = rememberNavController()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    val bottomBarItems = listOf(
        BottomBarItem("Budget", R.drawable.ic_budget, Screens.Budget.screen),
        BottomBarItem("Expenses", R.drawable.ic_expenses, Screens.Expenses.screen)
    )

    val drawerItems = listOf(
        DrawerItem("Main page", Icons.Default.Home, Screens.Budget.screen),
        DrawerItem("Converter", Icons.Default.Star, Screens.Converter.screen),
        DrawerItem("Logout", Icons.AutoMirrored.Filled.ExitToApp, null)
    )

    val selectedBottom = remember { mutableIntStateOf(R.drawable.ic_budget) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.statusBarsPadding()
            ) {
                DrawerHeader()
                HorizontalDivider()

                drawerItems.forEach { item ->
                    NavigationDrawerItem(
                        label = { Text(text = item.label, color = BlackText) },
                        selected = false,
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.label,
                                tint = BlackText
                            )
                        },
                        onClick = {
                            coroutineScope.launch { drawerState.close() }
                            item.screen?.let {
                                navController.navigate(it) {
                                    popUpTo(0)
                                }
                            } ?: Toast.makeText(context, "Logout", Toast.LENGTH_SHORT).show()
                        }
                    )
                    HorizontalDivider()
                }
            }
        }
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding(),
            topBar = {
                TopBar(onMenuClick = {
                    coroutineScope.launch { drawerState.open() }
                })
            },
            bottomBar = {
                BottomAppBar(
                    containerColor = GreenBackground,
                    modifier = Modifier.navigationBarsPadding()
                ) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        bottomBarItems.forEach { item ->
                            BottomNavItem(
                                item = item,
                                isSelected = selectedBottom.intValue == item.iconRes,
                                onClick = {
                                    selectedBottom.intValue = item.iconRes
                                    navController.navigate(item.screen) {
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
                navController = navController,
                startDestination = Screens.Budget.screen,
                modifier = Modifier.padding(paddingValues)
            ) {
                composable(Screens.Budget.screen) { Budget() }
                composable(Screens.Expenses.screen) { Expenses() }
                composable(Screens.Converter.screen) { Converter() }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainActivityPreview() {
    ExpenseManagerComposeTheme { MainScreen() }
}