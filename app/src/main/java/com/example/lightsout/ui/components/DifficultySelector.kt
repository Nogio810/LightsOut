package com.example.lightsout.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.lightsout.R

@Composable
fun DifficultySelector(
    difficultyOptions: List<String>,
    selectedDifficulty: String,
    onDifficultyChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.difficulty_level),
            modifier = Modifier.padding(16.dp),
            color = colorScheme.onBackground,
            fontWeight = FontWeight.Bold
        )
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp) // 間隔を統一
        ) {
            items(difficultyOptions) { difficulty ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = difficulty == selectedDifficulty,
                            onClick = { onDifficultyChanged(difficulty) }
                        )
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = difficulty == selectedDifficulty,
                        onClick = null, // `Row` の `onClick` に統一
                        colors = RadioButtonDefaults.colors(
                            selectedColor = colorScheme.primary,
                            unselectedColor = colorScheme.secondary
                        )
                    )
                    Text(
                        text = difficulty,
                        color = colorScheme.onBackground,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
    }
}