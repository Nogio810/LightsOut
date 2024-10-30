package com.example.lightsout.ui

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.internal.illegalDecoyCallException
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
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
fun SettingIcon(
    onBackPressed: () -> Unit,
    restart: () -> Unit,
    answerShow: () -> Unit,
    guideShow: () -> Unit,
    guideHide: () -> Unit,
    showHints:() -> Unit,
    showPlayGuide: Boolean,
    darkTheme: Boolean = isSystemInDarkTheme(),
    modifier: Modifier
){
    var expanded by remember { mutableStateOf(false) }
    Row(
        modifier = modifier
            .padding(top = 16.dp, end = 8.dp)
            .animateContentSize()
            .height(45.dp)
            .width(if (expanded) 330.dp else 45.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                expanded = !expanded
            }
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = colorScheme.onBackground,
                shape = if (expanded) RoundedCornerShape(45.dp) else CircleShape
            ),

        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = modifier.padding(1.dp))
        Image(
            painter = painterResource(if(darkTheme)R.drawable.dark_settings else R.drawable.settings),
            contentDescription = null,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(if(darkTheme)R.drawable.dark_refresh else R.drawable.refresh),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .then(
                    if (expanded) {
                        Modifier.clickable { restart() }
                    } else {
                        Modifier
                    }
                )
        )
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(if(darkTheme)R.drawable.dark_lightbulb else R.drawable.lightbulb),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .then(
                    if (expanded) {
                        Modifier.clickable { answerShow() }
                    } else {
                        Modifier
                    }
                )
        )
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(if(darkTheme)R.drawable.dark_priority_high else R.drawable.priority_high),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .then(
                    if (expanded) {
                        Modifier.clickable { showHints() }
                    } else {
                        Modifier
                    }
                )
            )
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(if (darkTheme)R.drawable.dark_question else R.drawable.question_mark),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .then(
                    if (expanded) {
                        if (showPlayGuide) {
                            Modifier.clickable { guideHide() }
                        } else {
                            Modifier.clickable { guideShow() }
                        }
                    } else {
                        Modifier
                    }
                )
        )
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(if (darkTheme)R.drawable.dark_home else R.drawable.home),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .then(
                    if (expanded) {
                        Modifier.clickable { onBackPressed() }
                    } else {
                        Modifier
                    }
                )
        )
        Spacer(modifier = modifier.padding(1.dp))
    }
}

@Composable
fun ClickTimes(
    clickTimes: Int,
    answerClickTimes:Int,
    showHints: Boolean,
    modifier: Modifier
){
    Row {
        Text(
            stringResource(R.string.click_Times, clickTimes),
            modifier.padding(
                start = 16.dp,
                bottom = 8.dp
            ),
            color = colorScheme.onBackground
        )
        if (showHints){
            Spacer(modifier = modifier.weight(1f))
            Text(
                stringResource(R.string.answer_click_times, answerClickTimes),
                modifier.padding(
                    end = 16.dp
                ),
                color = colorScheme.onBackground
            )
        }
    }

}

@Composable
fun LightsOutGame(
    massNumber: Int,
    massSize: Int,
    difficulty: String,
    index: MutableList<Int>,
    update: () -> Unit,
    generated: Boolean,
    restartTime: Int,
    restartBool: Boolean,
    answer: Boolean,
    answerIndent: MutableList<Int>,
    questionGenerated: () -> Unit,
    checkCorrect: () -> Unit,
    increaseBackCard: () -> Unit,
    decreaseBackCard: () -> Unit,
    backCard: Int,
    restarted: () -> Unit
){
    val grid = remember (restartTime) {  questionGeneration(
        difficulty = difficulty,
        massNumber = massNumber,
        index = index,
        generated = generated,
        questionGenerated = questionGenerated,
        answerIndent = answerIndent,
        increaseBackCard = increaseBackCard,
        decreaseBackCard = decreaseBackCard,
        backCard = backCard,
        restarted = restarted
    ) }
    Log.d("LightsOutGame", "backCardAlreadyGenerate:$backCard")

    when {
        backCard == 0 && !generated -> {
            Log.d("LightsOutGame", "backCard is 0 and not generated, executing specific logic.")
            // backCardが0でかつ生成されていない場合の処理
        }
        backCard != 0 && generated -> {
            Log.d("LightsOutGame", "backCard is not 0 and already generated, executing alternate logic.")
            // backCardが0でなく生成済みの場合の処理
        }
        backCard == 0 && !restartBool -> {
            Log.d("LightsOutGame", "backCard is 0 and already generated, executing alternate logic.")
            checkCorrect()
        }
    }

    DisplayMass(
        massNumber = massNumber,
        initialGrid = grid,
        massSize = massSize,
        update = update,
        restart = restartTime,
        answerList = answerIndent,
        answer = answer,
        increaseBackCard = increaseBackCard,
        decreaseBackCard = decreaseBackCard,
        backCard = backCard
    )
}

@Composable
fun DisplayMass(
    massNumber: Int,
    initialGrid: MutableList<MutableList<Int>>,
    massSize: Int,
    update: () -> Unit,
    restart: Int,
    answerList: MutableList<Int>,
    answer: Boolean,
    increaseBackCard: () -> Unit,
    decreaseBackCard: () -> Unit,
    backCard: Int
){
    val circleSize = massSize * 0.3
    val grid = remember(restart) {
        mutableStateListOf(*initialGrid.map{it.toMutableStateList()}.toTypedArray())
    }
    LazyVerticalGrid(
        columns = GridCells.Fixed(massNumber),
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                end = 16.dp
            ),
        contentPadding = PaddingValues(0.dp),
        verticalArrangement = Arrangement.spacedBy(0.dp),
        horizontalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        itemsIndexed(grid.flatten()) { index, panelNumber ->
            val row = index / massNumber
            val col = index % massNumber
            val imageRes = if (panelNumber == 0) {
                R.drawable.green
            } else {
                R.drawable.white
            }
            Box {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = null,
                    modifier = Modifier
                        .size(massSize.dp)
                        .aspectRatio(1f)
                        .clickable {
                            toggleCell(
                                grid,
                                row,
                                col,
                                massNumber,
                                increaseBackCard,
                                decreaseBackCard,
                                backCard
                                )
                            update()
                            answerCheck(answerList, row, col, massNumber)
                        }
                )
                if(answer){
                    if(answerList.contains(index)){
                        Canvas(
                            modifier = Modifier
                                .size(circleSize.dp)
                                .align(Alignment.Center),
                            onDraw = {
                                drawCircle(Color.Red)
                            }
                        )
                    }
                }
            }

        }
    }
}


fun questionGeneration(
    difficulty:String,
    massNumber: Int,
    index: MutableList<Int>,
    generated: Boolean,
    questionGenerated: () -> Unit,
    answerIndent: MutableList<Int>,
    increaseBackCard: () -> Unit,
    decreaseBackCard: () -> Unit,
    backCard: Int,
    restarted: () -> Unit
): MutableList<MutableList<Int>>{
    Log.d("LightsOutGame", "questionGeneration called")
    val mutableGrid = MutableList(massNumber) {MutableList(massNumber) { 0 } }
    Log.d("LightsOutGame", "Initial Restart value: $generated")
    if(!generated){
        val mass: Int = when(difficulty){
            "Hard" -> (massNumber * massNumber * 0.7).toInt()
            "Normal" -> (massNumber * massNumber * 0.5).toInt()
            else -> (massNumber * massNumber * 0.3).toInt()
        }
        val range = (0 until massNumber * massNumber).toMutableList()
        range.shuffle()
        index.addAll(range.take(mass).toMutableList())
        answerIndent.addAll(index)
        questionGenerated()
        Log.d("LightsOutGame", "Generate Question")
    }
    Log.d("LightsOutGame", "IndexContents:$index")
    for (i in index){
        val row = i / massNumber
        val col = i % massNumber
        mutableGrid[row][col] = if(mutableGrid[row][col] == 0) {
            increaseBackCard()
            1
        } else {
            decreaseBackCard()
            0
        }
        if (row - 1 >= 0){
            mutableGrid[row - 1][col] = if(mutableGrid[row - 1][col] == 0) {
                increaseBackCard()
                1
            } else {
                decreaseBackCard()
                0
            }
        }
        if (col - 1 >= 0){
            mutableGrid[row][col - 1] = if(mutableGrid[row][col - 1] == 0) {
                increaseBackCard()
                1
            } else {
                decreaseBackCard()
                0
            }
        }
        if (row + 1 <= massNumber - 1){
            mutableGrid[row + 1][col] = if(mutableGrid[row + 1][col] == 0) {
                increaseBackCard()
                1
            } else {
                decreaseBackCard()
                0
            }
        }
        if (col + 1 <= massNumber - 1){
            mutableGrid[row][col + 1] = if(mutableGrid[row][col + 1] == 0) {
                increaseBackCard()
                1
            } else {
                decreaseBackCard()
                0
            }
        }
        Log.d("LightsOutGame", "backCardGenerate:$backCard")
    }
    restarted()
    return mutableGrid
}

@Composable
fun ClearScreen(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .padding(
                start = 8.dp,
                end = 8.dp
            )
            .background(color = colorScheme.surface)
            .border(
                width = 4.dp,
                color = colorScheme.outline
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.game_clear),
            contentDescription = null,
            modifier = modifier
                .padding(
                    top = 12.dp,
                    start = 8.dp,
                    end = 8.dp
                )
                .fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
        Spacer(modifier = modifier.height(32.dp))
        Button(
            onClick = onClick,
            modifier = modifier
                .width(220.dp)
                .height(80.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorScheme.primary,
                contentColor = colorScheme.onPrimary
            )
        ) {
            Text(
                stringResource(R.string.play_again),
                fontSize = 30.sp
            )
        }
        Spacer(modifier = modifier.height(16.dp))
    }
}

fun toggleCell(
    grid: SnapshotStateList<SnapshotStateList<Int>>,
    row: Int,
    col: Int,
    massNumber: Int,
    increaseBackCard: () -> Unit,
    decreaseBackCard: () -> Unit,
    backCard: Int
){
    val newGrid = grid.map { row -> row.toMutableList().toMutableStateList() }.toMutableList()
    newGrid[row][col] = if (newGrid[row][col] == 0) {
        increaseBackCard()
        1
    } else {
        decreaseBackCard()
        0
    }
    if (row - 1 >= 0){
        newGrid[row - 1][col] = if (newGrid[row - 1][col] == 0) {
            increaseBackCard()
            1
        } else {
            decreaseBackCard()
            0
        }
    }
    if (col - 1 >= 0){
        newGrid[row][col - 1] = if (newGrid[row][col - 1] == 0) {
            increaseBackCard()
            1
        } else {
            decreaseBackCard()
            0
        }
    }
    if (row + 1 <= massNumber - 1){
        newGrid[row + 1][col] = if (newGrid[row + 1][col] == 0) {
            increaseBackCard()
            1
        } else {
            decreaseBackCard()
            0
        }
    }
    if (col + 1 <= massNumber - 1){
        newGrid[row][col + 1] = if (newGrid[row][col + 1] == 0) {
            increaseBackCard()
            1
        } else {
            decreaseBackCard()
            0
        }
    }
    grid.clear()
    grid.addAll(newGrid)
    Log.d("LightsOutGame", "backCard:$backCard")
}

fun answerCheck(
    answerIndent: MutableList<Int>,
    row: Int,
    col: Int,
    massNumber: Int
){
    val clickMassIndentNumber = row * massNumber + col
    if (answerIndent.contains(clickMassIndentNumber)){
        answerIndent.remove(clickMassIndentNumber)
    }else{
        answerIndent.add(clickMassIndentNumber)
    }
}