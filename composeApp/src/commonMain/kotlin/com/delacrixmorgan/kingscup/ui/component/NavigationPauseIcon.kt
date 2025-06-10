package com.delacrixmorgan.kingscup.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FreeBreakfast
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.delacrixmorgan.kingscup.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun NavigationPauseIcon(
    modifier: Modifier = Modifier,
    onClicked: () -> Unit
) {
    FilledIconButton(
        modifier = Modifier.padding(16.dp).size(64.dp).then(modifier),
        onClick = onClicked
    ) {
        Icon(
            imageVector = Icons.Rounded.FreeBreakfast,
            contentDescription = "Pause game",
        )
    }
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        NavigationPauseIcon { }
    }
}