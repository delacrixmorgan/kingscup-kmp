package com.delacrixmorgan.kingscup.data.support.model

import kingscup.composeapp.generated.resources.Res
import kingscup.composeapp.generated.resources.ic_squark
import kingscup.composeapp.generated.resources.ic_twilight
import org.jetbrains.compose.resources.DrawableResource

enum class Portfolio(
    val backgroundColor: Long,
    val logo: DrawableResource,
    val description: String,
    val url: String,
) {
    Twilight(
        backgroundColor = 0xFF1A120A,
        logo = Res.drawable.ic_twilight,
        description = "Timezone Tracker",
        url = "https://play.google.com/store/apps/details?id=com.delacrixmorgan.twilight.android",
    ),
    Squark(
        backgroundColor = 0xFF4CAF50,
        logo = Res.drawable.ic_squark,
        description = "Zero Clutter Currency Converter",
        url = "https://play.google.com/store/apps/details?id=com.delacrixmorgan.squark",
    )
}