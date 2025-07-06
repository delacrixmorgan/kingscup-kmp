package com.delacrixmorgan.kingscup.data.preferences.model

import kingscup.composeapp.generated.resources.Res
import kingscup.composeapp.generated.resources.*
import org.jetbrains.compose.resources.StringResource

enum class LocalePreference(
    val code: String,
    val emoji: String,
    val localisedName: StringResource,
    val contributorName: StringResource,
) {
    English(
        code = "en",
        emoji = "\uD83C\uDDEC\uD83C\uDDE7",
        localisedName = Res.string.locale_english,
        contributorName = Res.string.locale_englishContributor,
    ),
    Chinese(
        code = "zh",
        emoji = "\uD83C\uDDE8\uD83C\uDDF3",
        localisedName = Res.string.locale_chinese,
        contributorName = Res.string.locale_chineseContributor,
    );

    companion object {
        val Default = English
    }
}