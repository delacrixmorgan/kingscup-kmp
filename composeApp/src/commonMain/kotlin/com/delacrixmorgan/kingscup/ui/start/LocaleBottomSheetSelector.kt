package com.delacrixmorgan.kingscup.ui.start

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Campaign
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.composables.core.DragIndication
import com.composables.core.ModalBottomSheet
import com.composables.core.ModalBottomSheetState
import com.composables.core.Scrim
import com.composables.core.Sheet
import com.composables.core.SheetDetent
import com.composables.core.rememberModalBottomSheetState
import com.delacrixmorgan.kingscup.theme.AppTheme
import kingscup.composeapp.generated.resources.Res
import kingscup.composeapp.generated.resources.locale_selectTitle
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocaleBottomSheetSelector(
    sheetState: ModalBottomSheetState,
    state: StartUiState,
    onAction: (StartAction) -> Unit
) {
    ModalBottomSheet(
        state = sheetState,
        onDismiss = { onAction(StartAction.OnLocalisationBottomSheetDismissed) },
        content = {
            Scrim()
            Sheet(
                Modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
                    )
                    .padding(WindowInsets.navigationBars.only(WindowInsetsSides.Vertical).asPaddingValues()),
            ) {
                Column(Modifier.fillMaxWidth()) {
                    DragIndication(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(vertical = 10.dp)
                            .background(MaterialTheme.colorScheme.onSurfaceVariant, RoundedCornerShape(9999.dp))
                            .width(40.dp)
                            .height(4.dp),
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        modifier = Modifier.padding(vertical = 16.dp, horizontal = 20.dp),
                        text = stringResource(Res.string.locale_selectTitle),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    HorizontalMultiBrowseCarousel(
                        state = rememberCarouselState { state.locales.size },
                        modifier = Modifier.width(412.dp),
                        preferredItemWidth = 186.dp,
                        itemSpacing = 8.dp,
                        contentPadding = PaddingValues(horizontal = 16.dp),
                    ) { index ->
                        val localePreference = state.locales[index]
                        Column(
                            modifier = Modifier
                                .width(202.dp)
                                .aspectRatio(63F / 88F)
                                .maskClip(shape = MaterialTheme.shapes.extraLarge)
                                .background(MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(12.dp))
                                .padding(horizontal = 16.dp, vertical = 32.dp)
                                .clickable { onAction(StartAction.OnLocalisationChanged(localePreference)) },
                        ) {
                            Spacer(Modifier.height(16.dp))
                            Text(
                                modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                                text = localePreference.emoji,
                                style = MaterialTheme.typography.displayLarge,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                            Spacer(Modifier.height(8.dp))
                            Text(
                                modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                                text = stringResource(localePreference.localisedName),
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                            Text(
                                modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                                text = stringResource(localePreference.contributorName),
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                            Spacer(Modifier.weight(1F))

                            if (state.selectedLocale == localePreference) {
                                Image(
                                    modifier = Modifier
                                        .size(24.dp)
                                        .align(alignment = Alignment.CenterHorizontally),
                                    imageVector = Icons.Rounded.CheckCircle,
                                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.tertiaryContainer),
                                    contentDescription = "Selected language"
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .background(MaterialTheme.colorScheme.surfaceContainerHigh, shape = RoundedCornerShape(12.dp))
                            .clip(RoundedCornerShape(12.dp))
                            .clickable { onAction(StartAction.OpenContactUs(open = true)) }
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Image(
                            modifier = Modifier
                                .size(48.dp),
                            imageVector = Icons.Rounded.Campaign,
                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
                            contentDescription = null
                        )
                        Spacer(Modifier.width(16.dp))
                        Column {
                            Text(
                                text = "Can't find what you're looking for?",
                                style = MaterialTheme.typography.labelLarge,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = "Drop me a message!",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                        Spacer(Modifier.weight(1F))
                        Image(
                            modifier = Modifier
                                .size(24.dp),
                            imageVector = Icons.Rounded.ChevronRight,
                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
                            contentDescription = null
                        )
                    }

                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
        }
    )
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        LocaleBottomSheetSelector(
            sheetState = rememberModalBottomSheetState(initialDetent = SheetDetent.FullyExpanded),
            state = StartUiState(),
            onAction = {}
        )
    }
}