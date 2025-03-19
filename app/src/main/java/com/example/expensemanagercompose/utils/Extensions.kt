package com.example.expensemanagercompose.utils

import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp

fun Modifier.show(): Modifier {
    return this
}

fun Modifier.hide(): Modifier {
    return this.then(Modifier.size(0.dp))
}

fun Modifier.invisible(): Modifier {
    return this.then(Modifier.alpha(0f))
}

fun Modifier.toggleVisibility(isVisible: Boolean): Modifier {
    return if (isVisible) this else this.then(Modifier.size(0.dp))
}