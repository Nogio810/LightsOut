package com.example.lightsout.data

import com.example.lightsout.R

object DataSource {
    val difficulties = listOf(
        R.string.easy,
        R.string.normal,
        R.string.hard
    )
}

sealed class ContentItem{
    data class Subtitle(val text: String) : ContentItem()
    data class BulletPointGuide(val text: String) : ContentItem()
    data class BulletPointFunction(val text: String) : ContentItem()
}