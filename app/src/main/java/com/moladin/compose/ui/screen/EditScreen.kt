package com.moladin.compose.ui.screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue

@Composable
fun EditScreen(
    placeholder: String,
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit
) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        maxLines = 1,
        value = value,
        label = {
            Text(text = placeholder)
        },
        placeholder = {
            Text(text = "New car name")
        },
        onValueChange = onValueChange,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Black.copy(alpha = 0.05f)
        )
    )
}