package com.example.lightsout.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lightsout.R

@Composable
fun SettingIcon(
    navController: NavController,
    minMass: Int,
    restart: () -> Unit,
    answerShow: () -> Unit,
    showHints: () -> Unit,
    reset: () -> Unit,
    darkTheme: Boolean = isSystemInDarkTheme(),
    modifier: Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    val alpha by animateFloatAsState(if(expanded) 1f else 0f)

    Row(
        modifier = modifier
            .padding(top = 16.dp, end = 8.dp)
            .height(45.dp)
            .clickable { expanded = !expanded }
            .border(
                width = 2.dp,
                color = colorScheme.onBackground,
                shape = if (expanded) RoundedCornerShape(45.dp) else CircleShape
            )
            .animateContentSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
    ) {
        SettingIconButton(darkTheme, R.drawable.settings, R.drawable.dark_settings) {
            expanded = !expanded
        }

        SettingIconButton(darkTheme, R.drawable.refresh, R.drawable.dark_refresh, alpha, restart)
        SettingIconButton(darkTheme, R.drawable.lightbulb, R.drawable.dark_lightbulb, alpha, answerShow)
        SettingIconButton(darkTheme, R.drawable.priority_high, R.drawable.dark_priority_high, alpha, showHints)
        SettingIconButton(darkTheme, R.drawable.question_mark, R.drawable.dark_question, alpha) {
            navController.navigate("playGuide/$minMass")
        }
        SettingIconButton(darkTheme, R.drawable.home, R.drawable.dark_home, alpha) {
            reset()
            navController.navigate("setting")
        }
    }
}