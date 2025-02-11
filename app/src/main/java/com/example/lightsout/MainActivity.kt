package com.example.lightsout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.compose.LightsOutTheme
import com.example.lightsout.ui.screens.LightsOutScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LightsOutTheme {
                LightsOutScreen()
            }
        }
    }
}