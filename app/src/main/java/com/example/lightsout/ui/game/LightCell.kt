package com.example.lightsout.ui.game

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.lightsout.R

@Composable
fun LightCell(
    isOn: Boolean,
    massSize: Int,
    isAnswer: Boolean,
    modifier: Modifier = Modifier,
) {
    val circleSize = massSize * 0.3

    Box(
        modifier = modifier.size(massSize.dp)
    ) {
        Image(
            painter = painterResource(if (isOn) R.drawable.white else R.drawable.green),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
        if (isAnswer) {
            Image(
                painter = painterResource(R.drawable.red_dot),
                contentDescription = "Answer Mark",
                modifier = Modifier
                    .size(circleSize.dp)
                    .align(Alignment.Center)
            )
        }
    }
}