package com.example.lightsout.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.lightsout.R

@Composable
fun GameImage(modifier: Modifier = Modifier){
    Image(
        painter = painterResource(R.drawable.lightsoutapppicture),
        contentDescription = null,
        modifier = modifier
            .fillMaxWidth()
            .padding(
                top = 8.dp,
                start = 16.dp,
                end = 16.dp
            ),
        contentScale = ContentScale.FillWidth
    )
}