package com.example.lightsout.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsEndWidth
import androidx.compose.foundation.layout.windowInsetsStartWidth
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.lightsout.R
import com.example.lightsout.data.ContentItem

@Composable
fun MassNumberSetting(
    isNumberWrong: Boolean,
    onUserNumberChanged: (String) ->Unit,
    userNumber: String,
    minMass: Int
){
    val focusManager = LocalFocusManager.current
    Column{
        Text(
            stringResource(R.string.panel_number, minMass),
            modifier = Modifier
                .padding(all = 16.dp),
            color = colorScheme.onBackground,
            fontWeight = FontWeight.Bold
            )

        OutlinedTextField(
            value = userNumber,
            singleLine = true,
            shape = shapes.medium,
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    end = 16.dp
                )
                .fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = colorScheme.primaryContainer,
                focusedTextColor = colorScheme.onPrimaryContainer,
                unfocusedContainerColor = colorScheme.primaryContainer,
                unfocusedTextColor = colorScheme.onPrimaryContainer,
                errorContainerColor = colorScheme.errorContainer,
                errorTextColor = colorScheme.onErrorContainer,
                focusedIndicatorColor = colorScheme.primary,
                unfocusedIndicatorColor = colorScheme.primary,
                errorIndicatorColor = colorScheme.error
            ),
            onValueChange = onUserNumberChanged,
            label = {
                if (isNumberWrong) {
                    Text(
                        stringResource(R.string.wrong_number),
                        color = colorScheme.onError
                    )
                } else {
                    Text(
                        stringResource(R.string.enter_number),
                        color = colorScheme.onBackground
                    )
                }
            },
            isError = isNumberWrong,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            )
        )
    }
}

@Composable
fun DifficultySetting(
    difficulty:List<String>,
    onSelectionChanged: (String) ->Unit = {}
){
    var selectedValue by rememberSaveable { mutableStateOf("") }

    Column {
        Text(
            stringResource(R.string.difficulty_level),
            modifier = Modifier.padding(all = 16.dp),
            color = colorScheme.onBackground,
            fontWeight = FontWeight.Bold
        )
        difficulty.forEach { item ->
            Row(
                modifier = Modifier.selectable(
                    selected = selectedValue == item,
                    onClick = {
                        selectedValue = item
                        onSelectionChanged(item)
                    }
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedValue == item,
                    onClick = {
                        selectedValue = item
                        onSelectionChanged(item)
                    },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = colorScheme.primary,
                        unselectedColor = colorScheme.secondary
                    )
                )
                Text(
                    item,
                    color = colorScheme.onBackground
                )
            }
        }
    }
}

@Composable
fun StartButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Button(
        onClick = onClick,
        modifier = modifier.widthIn(min = 250.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorScheme.primary,
            contentColor = colorScheme.onPrimary
        )
        ) {
        Text(
            stringResource(R.string.start_button),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ShowPlayGuide(
    darkTheme: Boolean = isSystemInDarkTheme(),
    hidePlayGuide: () -> Unit,
    minMass: Int,
    modifier: Modifier = Modifier
){
    val instructions = listOf(
        ContentItem.BulletPointGuide(stringResource(R.string.play_guide_1)),
        ContentItem.BulletPointGuide(stringResource(R.string.play_guide_2)),
        ContentItem.BulletPointGuide(stringResource(R.string.play_guide_3)),
        ContentItem.BulletPointGuide(stringResource(R.string.play_guide_4)),
        ContentItem.BulletPointGuide(stringResource(R.string.play_guide_5)),
        ContentItem.BulletPointGuide(stringResource(R.string.play_guide_6)),
        ContentItem.Subtitle(stringResource(R.string.various_functions)),
        ContentItem.BulletPointFunction(stringResource(R.string.setting_function)),
        ContentItem.BulletPointFunction(stringResource(R.string.refresh_function)),
        ContentItem.BulletPointFunction(stringResource(R.string.answer_function)),
        ContentItem.BulletPointFunction(stringResource(R.string.hints_function)),
        ContentItem.BulletPointFunction(stringResource(R.string.guide_function)),
        ContentItem.BulletPointFunction(stringResource(R.string.home_function))
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
                    .clickable { hidePlayGuide() }
                    .padding(
                        top = 8.dp,
                        end = 16.dp
                    )
            )
        }
        Column(
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .padding(start = 16.dp, end = 16.dp),
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
            ) {
                items(instructions){ item ->
                    when(item) {
                        is ContentItem.Subtitle -> {
                            Text(
                                text = item.text,
                                fontSize = 40.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        is ContentItem.BulletPointGuide -> {
                            when(item.text) {
                                stringResource(R.string.play_guide_1) ->
                                    Text(
                                        text = stringResource(R.string.play_guide_1, minMass),
                                        fontSize = 20.sp,
                                        modifier = Modifier.padding(16.dp)
                                    )
                                else -> Text(
                                    text = item.text,
                                    fontSize = 20.sp,
                                    modifier = Modifier.padding(16.dp)
                                )
                            }
                        }
                        is ContentItem.BulletPointFunction -> {
                            val icon = when(darkTheme) {
                                true -> when(item.text){
                                    stringResource(R.string.refresh_function) -> R.drawable.dark_refresh
                                    stringResource(R.string.setting_function) -> R.drawable.dark_settings
                                    stringResource(R.string.answer_function) -> R.drawable.dark_lightbulb
                                    stringResource(R.string.hints_function) -> R.drawable.dark_priority_high
                                    stringResource(R.string.guide_function) -> R.drawable.dark_question
                                    stringResource(R.string.home_function) -> R.drawable.dark_home
                                    else -> 0
                                }
                                false -> when(item.text){
                                    stringResource(R.string.refresh_function) -> R.drawable.refresh
                                    stringResource(R.string.setting_function) -> R.drawable.settings
                                    stringResource(R.string.answer_function) -> R.drawable.lightbulb
                                    stringResource(R.string.hints_function) -> R.drawable.priority_high
                                    stringResource(R.string.guide_function) -> R.drawable.question_mark
                                    stringResource(R.string.home_function) -> R.drawable.home
                                    else -> 0
                                }
                            }
                            val myId = "inlineContent"
                            val text = buildAnnotatedString {
                                appendInlineContent(myId, "[myBox]")
                                withStyle(style = SpanStyle(fontSize = 20.sp)) {
                                    append(item.text)
                                }
                            }
                            val inlineContent =
                                mapOf(
                                    Pair(
                                        myId,
                                        InlineTextContent(
                                            Placeholder(
                                                width = 24.sp,
                                                height = 24.sp,
                                                placeholderVerticalAlign = PlaceholderVerticalAlign.TextCenter
                                            )
                                        ) {
                                            Box(modifier = Modifier.fillMaxSize()){
                                                Image(
                                                    painter = painterResource(id = icon),
                                                    contentDescription = null,
                                                    modifier = Modifier.fillMaxSize(),
                                                    contentScale = ContentScale.Fit
                                                )
                                            }
                                        }
                                    )
                                )
                            BasicText(
                                text = text,
                                inlineContent = inlineContent,
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }
                }
                item {
                    Spacer(
                        Modifier.windowInsetsBottomHeight(
                            WindowInsets.systemBars
                        )
                    )
                }
            }

        }
    }
}
