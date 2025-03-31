package com.example.expensemanagercompose.ui.screens.main

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
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
        window.statusBarColor = Color.Black.toArgb()
        WindowInsetsControllerCompat(window, view).apply {
            isAppearanceLightStatusBars = false
            isAppearanceLightNavigationBars = false
        }
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

    val selectedBottom = remember { mutableStateOf<Int?>(R.drawable.ic_budget) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier
                    .statusBarsPadding()
                    .navigationBarsPadding(),
                drawerShape = RectangleShape,
                drawerContainerColor = Color.White
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 0.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        DrawerHeader()
                        HorizontalDivider()

                        drawerItems.filter { it.label != "Logout" }.forEach { item ->
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

                                    item.screen?.let { screen ->
                                        selectedBottom.value = when (screen) {
                                            Screens.Budget.screen -> R.drawable.ic_budget
                                            Screens.Expenses.screen -> R.drawable.ic_expenses
                                            else -> null
                                        }

                                        navController.navigate(screen) {
                                            popUpTo(0)
                                        }
                                    }
                                },
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )
                            HorizontalDivider()
                        }
                    }

                    val logoutItem = drawerItems.find { it.label == "Logout" }
                    logoutItem?.let { item ->
                        Column(
                            modifier = Modifier
                                .padding(start = 16.dp, end = 16.dp, bottom = 24.dp)
                        ) {
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
                                    Toast.makeText(context, "Logout", Toast.LENGTH_SHORT).show()
                                }
                            )
                        }
                    }
                }
            }
        }
    ) {

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val topBarTitle = when (currentRoute) {
            Screens.Budget.screen -> "Budget"
            Screens.Expenses.screen -> "Expenses"
            Screens.Converter.screen -> "Converter"
            else -> "Expense Manager"
        }

        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding(),
            topBar = {
                TopBar(
                    title = topBarTitle,
                    onMenuClick = {
                        coroutineScope.launch { drawerState.open() }
                    }
                )
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
                                isSelected = selectedBottom.value == item.iconRes,
                                onClick = {
                                    selectedBottom.value = item.iconRes
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