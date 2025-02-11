package com.example.lightsout.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.lightsout.R

@Composable
fun HelpIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    IconButton(
        onClick = onClick,
        modifier = modifier
            .padding(16.dp)
    ) {
        Icon(
            painter = painterResource(R.drawable.question_mark),
            contentDescription = "ヘルプ",
            modifier = Modifier
                .border(
                    2.dp,
                    color = Color.Black,
                    shape = CircleShape
                )
                .padding(2.dp)
                .clip(CircleShape)
        )
    }
}
