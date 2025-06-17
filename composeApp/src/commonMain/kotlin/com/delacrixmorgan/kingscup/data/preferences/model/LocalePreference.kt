package com.delacrixmorgan.kingscup.data.preferences.model

enum class LocalePreference(
    val code: String,
    val emoji: String
) {
    English(
        code = "en",
        emoji = "\uD83C\uDDEC\uD83C\uDDE7"
    ),
    Chinese(
        code = "zh",
        emoji = "\uD83C\uDDE8\uD83C\uDDF3"
    );

    companion object {
        val Default = English
    }
}