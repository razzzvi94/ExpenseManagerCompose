package com.example.expensemanagercompose.data

sealed class Screens(val screen: String){
    data object Budget: Screens("budget")
    data object Converter: Screens("converter")
    data object Expenses: Screens("expenses")
    data object AddExpense: Screens("addExpense")
}