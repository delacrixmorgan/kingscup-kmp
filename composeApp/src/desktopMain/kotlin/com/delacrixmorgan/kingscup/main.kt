package com.delacrixmorgan.kingscup

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.delacrixmorgan.kingscup.ui.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "King's Cup",
    ) {
        App()
    }
}