package com.example.lightsout.ui.components

import android.content.Context
import com.example.lightsout.R

data class FunctionIcon(val textRes: Int, val light: Int, val dark: Int)

val functionIcons = listOf(
    FunctionIcon(R.string.refresh_function, R.drawable.refresh, R.drawable.dark_refresh),
    FunctionIcon(R.string.setting_function, R.drawable.settings, R.drawable.dark_settings),
    FunctionIcon(R.string.answer_function, R.drawable.lightbulb, R.drawable.dark_lightbulb),
    FunctionIcon(R.string.hints_function, R.drawable.priority_high, R.drawable.dark_priority_high),
    FunctionIcon(R.string.guide_function, R.drawable.question_mark, R.drawable.dark_question),
    FunctionIcon(R.string.home_function, R.drawable.home, R.drawable.dark_home)
)

fun getFunctionIcon(context: Context, text: String, darkTheme: Boolean): Int{
    val icon = functionIcons.find { context.resources.getString(it.textRes) == text }
    return icon?.let { if (darkTheme) it.dark else it.light } ?: 0
}