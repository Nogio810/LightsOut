package com.example.lightsout.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp

@Composable
fun GuideFunctionText(text: String, iconResId: Int){
    val inlineContentId = "inlineContent"
    val inlineContent = mapOf(
        inlineContentId to InlineTextContent(
            Placeholder(
                width = 24.sp,
                height = 24.sp,
                placeholderVerticalAlign = PlaceholderVerticalAlign.TextCenter
            )
        ){
            Image(
                painter = painterResource(id = iconResId),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }
    )

    BasicText(
        text = buildAnnotatedString {
            appendInlineContent(inlineContentId, "[icon]")
            withStyle(style = SpanStyle(fontSize = 20.sp)){
                append(text)
            }
        },
        inlineContent = inlineContent,
        modifier = Modifier.padding(16.dp)
    )
}