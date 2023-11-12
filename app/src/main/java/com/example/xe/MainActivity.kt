package com.example.xe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.xe.ui.theme.XeTheme
import com.example.xe.ui.xe.XeScreen
import com.example.xe.utils.rememberWindowSize



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            XeTheme {
                val device = rememberWindowSize().type

                XeScreen(windowType = device)

            }
        }
    }
}

