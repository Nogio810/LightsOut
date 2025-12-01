package com.example.lightsout.ui.game

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lightsout.R

@Composable
fun ClearOverlay(
    isShowClear: Boolean,
    onRestart: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (isShowClear) {
        OverlayBackground {
            ClearContent(onRestart)
        }
    }
}

@Composable
private fun OverlayBackground(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f))
            .clickable { }, // 何もないところをタップしても反応しない
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

@Composable
private fun ClearContent(onRestart: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(
                color = colorScheme.surface,
                shape = RoundedCornerShape(16.dp)
            )
            .border(4.dp, colorScheme.outline, shape = RoundedCornerShape(16.dp))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.game_clear),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            contentScale = ContentScale.FillWidth
        )
        Spacer(Modifier.height(32.dp))
        Button(
            onClick = onRestart,
            modifier = Modifier
                .width(220.dp)
                .height(80.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorScheme.primary,
                contentColor = colorScheme.onPrimary
            )
        ) {
            Text(
                text = stringResource(R.string.play_again),
                fontSize = 30.sp
            )
        }
        Spacer(Modifier.height(16.dp))
    }
}
