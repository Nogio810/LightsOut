package com.example.lightsout.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.lightsout.R

@Composable
fun MassInputField(
    minMass: Int,
    isNumberWrong: Boolean,
    rowNum: String,
    onRowNumChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    val textFieldColors = TextFieldDefaults.colors(
        focusedContainerColor = colorScheme.primaryContainer,
        focusedTextColor = colorScheme.onPrimaryContainer,
        unfocusedContainerColor = colorScheme.primaryContainer,
        unfocusedTextColor = colorScheme.onPrimaryContainer,
        errorContainerColor = colorScheme.errorContainer,
        errorTextColor = colorScheme.onErrorContainer,
        focusedIndicatorColor = colorScheme.primary,
        unfocusedIndicatorColor = colorScheme.primary,
        errorIndicatorColor = colorScheme.error
    )
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.panel_number, minMass),
            modifier = Modifier.padding(16.dp),
            color = colorScheme.onBackground,
            fontWeight = FontWeight.Bold
        )
        OutlinedTextField(
            value = rowNum,
            singleLine = true,
            shape = shapes.medium,
            modifier = Modifier
                .padding(
                    start = 16.dp, end = 16.dp
                )
                .fillMaxWidth(),
            colors = textFieldColors,
            onValueChange = onRowNumChanged,
            label = {
                Text(
                    text = when {
                        isNumberWrong -> stringResource(R.string.wrong_number)
                        else -> stringResource(R.string.enter_number)
                    }, color = if (isNumberWrong) colorScheme.onError else colorScheme.onBackground
                )
            },
            isError = isNumberWrong,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number, imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                    keyboardController?.hide()
                }))
    }
}