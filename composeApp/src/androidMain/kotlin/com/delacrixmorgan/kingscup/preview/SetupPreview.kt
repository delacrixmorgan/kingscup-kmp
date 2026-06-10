package com.delacrixmorgan.kingscup.preview

import androidx.compose.runtime.Composable
import com.delacrixmorgan.kingscup.data.preferences.model.SkinPreference
import com.delacrixmorgan.kingscup.theme.AppTheme
import com.delacrixmorgan.kingscup.ui.setup.SetupScreen
import androidx.compose.ui.tooling.preview.Preview
import com.delacrixmorgan.kingscup.ui.setup.SetupUiState

@Preview
@Composable
private fun SetupPreview() {
    AppTheme {
        SetupScreen(
            state = SetupUiState(
                selectedSkin = SkinPreference.Default
            ),
            onAction = {}
        )
    }
}