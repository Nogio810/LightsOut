package com.example.lightsout.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ClickTimes(
    clickText: String,
    answerClickText: String?,
    modifier: Modifier = Modifier
){
    Row {
        Text(
            text = clickText,
            modifier = modifier.padding(start = 16.dp, bottom = 8.dp),
            color = colorScheme.onBackground
        )
        answerClickText?.let {
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = it,
                modifier = modifier.padding(end = 16.dp),
                color = colorScheme.onBackground
            )
        }
    }
}