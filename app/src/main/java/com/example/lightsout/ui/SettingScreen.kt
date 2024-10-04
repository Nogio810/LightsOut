package com.example.lightsout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose.LightsOutTheme
import com.example.lightsout.data.DataSource
import com.example.lightsout.ui.DifficultySetting
import com.example.lightsout.ui.GameScreenApp
import com.example.lightsout.ui.LightsOutScreenView
import com.example.lightsout.ui.MassNumberSetting
import com.example.lightsout.ui.ShowPlayGuide
import com.example.lightsout.ui.StartButton

@Composable
fun LightsOutScreen(
    modifier: Modifier = Modifier
){
    val lightsOutScreenView: LightsOutScreenView = viewModel()
    val lightsOutUiState = lightsOutScreenView.uiState.collectAsState().value
    val onGameScreenBackPressed = { lightsOutScreenView.resetHomeScreenStates() }

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp

    val minMass: Int = screenWidth / 48

    if (lightsOutUiState.isShowingHomepage) {
        SettingScreenApp(
            onClick = {
                lightsOutScreenView.checkUserNumber(minMass)
                lightsOutScreenView.updateGameScreen()
                      },
            minMass = minMass,
            playGuideHide = { lightsOutScreenView.playGuideHide() },
            playGuideShow = { lightsOutScreenView.playGuideShow() },
            modifier = modifier
        )
    } else {
        GameScreenApp(
            onBackPressed = onGameScreenBackPressed,
            minMass = minMass
        )
    }
}

@Composable
private fun SettingScreenApp(
    onClick: () -> Unit,
    lightsOutScreenView: LightsOutScreenView = viewModel(),
    minMass: Int,
    darkTheme: Boolean = isSystemInDarkTheme(),
    playGuideShow: () -> Unit,
    playGuideHide: () -> Unit,
    modifier: Modifier = Modifier
){
    val lightsOutUiState by lightsOutScreenView.uiState.collectAsState()

    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorScheme.background)
                .align(Alignment.Center)
        ) {
            val context = LocalContext.current
            Spacer(
                modifier.windowInsetsBottomHeight(
                    WindowInsets.systemBars
                )
            )
            Box{
                LazyColumn(modifier = Modifier.align(Alignment.TopCenter)) {
                    item{
                        Image(
                            painterResource(R.drawable.lightsoutapppicture),
                            contentDescription = null,
                            modifier = modifier
                                .padding(
                                    top = 8.dp,
                                    start = 16.dp,
                                    end = 16.dp
                                )
                                .fillMaxWidth(),
                            contentScale = ContentScale.FillWidth
                        )
                    }
                    item{
                        MassNumberSetting(
                            isNumberWrong = lightsOutUiState.isNumberWrong,
                            onUserNumberChanged = { lightsOutScreenView.updateUserNumber(it) },
                            userNumber = lightsOutScreenView.userNumber,
                            minMass = minMass
                        )
                    }
                    item{
                        DifficultySetting(
                            difficulty = DataSource.difficulties.map { id -> context.resources.getString(id) },
                            onSelectionChanged ={ lightsOutScreenView.setDifficulties(it) }
                        )
                    }
                    item{Spacer(modifier = Modifier.height(16.dp))}
                    item{
                        StartButton(
                            onClick = onClick,
                            modifier = Modifier
                                .padding(
                                    start = 8.dp,
                                    end = 8.dp
                                )
                                .fillMaxWidth()
                        )
                    }
                    item{Spacer(modifier = modifier.height(16.dp))}
                }
                Column(modifier = Modifier.align(Alignment.TopEnd)) {
                    Spacer(modifier = Modifier.height(20.dp))
                    Row {
                        Image(
                            painter = painterResource(R.drawable.question_mark),
                            contentDescription = null,
                            modifier = Modifier
                                .border(
                                    2.dp,
                                    color = Color.Black,
                                    shape = CircleShape
                                )
                                .padding(2.dp)
                                .clip(CircleShape)
                                .then(
                                    if (lightsOutUiState.isShowPlayGuide) {
                                        Modifier.clickable { playGuideHide() }
                                    } else {
                                        Modifier.clickable { playGuideShow() }
                                    }
                                )
                        )
                        Spacer(modifier = Modifier.width(28.dp))
                    }
                }
            }
        }


        if(lightsOutUiState.isShowPlayGuide){
            ShowPlayGuide(
                hidePlayGuide = { lightsOutScreenView.playGuideHide() },
                modifier = Modifier.align(Alignment.Center),
                minMass = minMass
            )
        }
    }

}


@Composable
@Preview
fun SettingScreenPreview(){
    LightsOutTheme (darkTheme = false){
        LightsOutScreen()
    }
}

@Composable
@Preview
fun SettingScreenDarkThemePreview(){
    LightsOutTheme (darkTheme = true){
        LightsOutScreen()
    }
}