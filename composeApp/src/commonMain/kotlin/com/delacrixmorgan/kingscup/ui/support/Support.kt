package com.delacrixmorgan.kingscup.ui.support

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController

@Composable
fun SupportRoot(viewModel: SupportViewModel, navHostController: NavHostController) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    SupportScreen(state = state, onAction = { viewModel.onAction(navHostController, it) })
}

@Composable
fun SupportScreen(
    state: SupportUiState,
    onAction: (SupportAction) -> Unit,
) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Support")
    }
}