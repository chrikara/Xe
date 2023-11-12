package com.example.xe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.xe.ui.theme.XeTheme
import com.example.xe.ui.xe.XeScreen
import com.example.xe.ui.xe.XeViewModel
import com.example.xe.utils.rememberWindowSize
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            XeTheme {
                val device = rememberWindowSize().type
                val viewModel = hiltViewModel<XeViewModel>()

                XeScreen(
                    state = viewModel.state,
                    windowType = device,
                    onEvent = viewModel::onEvent,
                    uiEventFlow = viewModel.uiEvent
                )

            }
        }
    }
}

