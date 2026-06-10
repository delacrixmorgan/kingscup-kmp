package com.delacrixmorgan.kingscup.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.delacrixmorgan.kingscup.theme.AppTheme
import kingscup.composeapp.generated.resources.Res
import kingscup.composeapp.generated.resources.ic_play_arrow
import org.jetbrains.compose.resources.painterResource

@Composable
fun NavigationStartIcon(
    modifier: Modifier = Modifier,
    onClicked: () -> Unit
) {
    FilledIconButton(
        modifier = Modifier.padding(16.dp).size(64.dp).then(modifier),
        onClick = onClicked
    ) {
        Icon(
            painter = painterResource(Res.drawable.ic_play_arrow),
            contentDescription = "Start game"
        )
    }
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        NavigationStartIcon {}
    }
}
