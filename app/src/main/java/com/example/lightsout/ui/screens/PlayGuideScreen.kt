package com.example.lightsout.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.lightsout.R
import com.example.lightsout.data.ContentItem
import com.example.lightsout.ui.components.GuideFunctionText
import com.example.lightsout.ui.components.GuideSubtitle
import com.example.lightsout.ui.components.GuideText
import com.example.lightsout.ui.components.getFunctionIcon

@Composable
fun PlayGuideScreen(
    navController: NavController,
    minMass: Int,
    modifier: Modifier = Modifier
){
    val context = LocalContext.current
    val darkTheme = isSystemInDarkTheme()
    val instructions = listOf(
        ContentItem.BulletPointGuide(context.getString(R.string.play_guide_1, minMass)),
        ContentItem.BulletPointGuide(context.getString(R.string.play_guide_2)),
        ContentItem.BulletPointGuide(context.getString(R.string.play_guide_3)),
        ContentItem.BulletPointGuide(context.getString(R.string.play_guide_4)),
        ContentItem.BulletPointGuide(context.getString(R.string.play_guide_5)),
        ContentItem.BulletPointGuide(context.getString(R.string.play_guide_6)),
        ContentItem.Subtitle(context.getString(R.string.various_functions)),
        ContentItem.BulletPointFunction(context.getString(R.string.setting_function)),
        ContentItem.BulletPointFunction(context.getString(R.string.refresh_function)),
        ContentItem.BulletPointFunction(context.getString(R.string.answer_function)),
        ContentItem.BulletPointFunction(context.getString(R.string.hints_function)),
        ContentItem.BulletPointFunction(context.getString(R.string.guide_function)),
        ContentItem.BulletPointFunction(context.getString(R.string.home_function))
    )
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = colorScheme.background)
            .padding(16.dp)
    ){
        Column(modifier = Modifier.align(Alignment.TopEnd)) {
            Spacer(
                modifier.windowInsetsBottomHeight(
                    WindowInsets.systemBars
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Image(
                painter = painterResource(if (darkTheme) R.drawable.dark_cancel else R.drawable.cancel),
                contentDescription = null,
                modifier = Modifier
                    .clickable { navController.popBackStack() }
                    .padding(
                        top = 8.dp,
                        end = 16.dp
                    )
            )
        }
        Column(
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(
                modifier.windowInsetsBottomHeight(
                    WindowInsets.systemBars
                )
            )
            Text(
                text = stringResource(R.string.play_guide),
                color = colorScheme.onBackground,
                fontSize = 52.sp,
            )
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(modifier = Modifier
                .padding(start = 16.dp)
                .padding(WindowInsets.navigationBars.asPaddingValues())
            ) {
                items(instructions){ item ->
                    when(item) {
                        is ContentItem.Subtitle -> GuideSubtitle(item.text)
                        is ContentItem.BulletPointGuide -> GuideText(item.text)
                        is ContentItem.BulletPointFunction -> {
                            val icon = getFunctionIcon(context, item.text, darkTheme)
                            GuideFunctionText(item.text, icon)
                        }
                    }
                }
            }
        }
    }
}