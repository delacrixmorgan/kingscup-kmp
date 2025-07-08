package com.delacrixmorgan.kingscup.preview

import androidx.compose.runtime.Composable
import com.delacrixmorgan.kingscup.data.preferences.model.SkinPreference
import com.delacrixmorgan.kingscup.theme.AppTheme
import com.delacrixmorgan.kingscup.ui.setup.SetupScreen
import com.delacrixmorgan.kingscup.ui.setup.SetupUiState
import org.jetbrains.compose.ui.tooling.preview.Preview

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