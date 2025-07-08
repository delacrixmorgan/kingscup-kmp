package com.delacrixmorgan.kingscup.data.preferences.model

import kingscup.composeapp.generated.resources.Res
import kingscup.composeapp.generated.resources.locale_chinese
import kingscup.composeapp.generated.resources.locale_chineseContributor
import kingscup.composeapp.generated.resources.locale_croatian
import kingscup.composeapp.generated.resources.locale_croatianContributor
import kingscup.composeapp.generated.resources.locale_czech
import kingscup.composeapp.generated.resources.locale_czechContributor
import kingscup.composeapp.generated.resources.locale_danish
import kingscup.composeapp.generated.resources.locale_danishContributor
import kingscup.composeapp.generated.resources.locale_dutch
import kingscup.composeapp.generated.resources.locale_dutchContributor
import kingscup.composeapp.generated.resources.locale_english
import kingscup.composeapp.generated.resources.locale_englishContributor
import kingscup.composeapp.generated.resources.locale_filipino
import kingscup.composeapp.generated.resources.locale_filipinoContributor
import kingscup.composeapp.generated.resources.locale_finnish
import kingscup.composeapp.generated.resources.locale_finnishContributor
import kingscup.composeapp.generated.resources.locale_french
import kingscup.composeapp.generated.resources.locale_frenchContributor
import kingscup.composeapp.generated.resources.locale_german
import kingscup.composeapp.generated.resources.locale_germanContributor
import kingscup.composeapp.generated.resources.locale_greek
import kingscup.composeapp.generated.resources.locale_greekContributor
import kingscup.composeapp.generated.resources.locale_hungarian
import kingscup.composeapp.generated.resources.locale_hungarianContributor
import kingscup.composeapp.generated.resources.locale_indonesian
import kingscup.composeapp.generated.resources.locale_indonesianContributor
import kingscup.composeapp.generated.resources.locale_italian
import kingscup.composeapp.generated.resources.locale_italianContributor
import kingscup.composeapp.generated.resources.locale_japanese
import kingscup.composeapp.generated.resources.locale_japaneseContributor
import kingscup.composeapp.generated.resources.locale_lao
import kingscup.composeapp.generated.resources.locale_laoContributor
import kingscup.composeapp.generated.resources.locale_persian
import kingscup.composeapp.generated.resources.locale_persianContributor
import kingscup.composeapp.generated.resources.locale_polish
import kingscup.composeapp.generated.resources.locale_polishContributor
import kingscup.composeapp.generated.resources.locale_portuguese
import kingscup.composeapp.generated.resources.locale_portugueseContributor
import kingscup.composeapp.generated.resources.locale_romanian
import kingscup.composeapp.generated.resources.locale_romanianContributor
import kingscup.composeapp.generated.resources.locale_russian
import kingscup.composeapp.generated.resources.locale_russianContributor
import kingscup.composeapp.generated.resources.locale_slovak
import kingscup.composeapp.generated.resources.locale_slovakContributor
import kingscup.composeapp.generated.resources.locale_spanish
import kingscup.composeapp.generated.resources.locale_spanishContributor
import kingscup.composeapp.generated.resources.locale_thai
import kingscup.composeapp.generated.resources.locale_thaiContributor
import kingscup.composeapp.generated.resources.locale_turkish
import kingscup.composeapp.generated.resources.locale_turkishContributor
import kingscup.composeapp.generated.resources.locale_ukrainian
import kingscup.composeapp.generated.resources.locale_ukrainianContributor
import kingscup.composeapp.generated.resources.locale_vietnamese
import kingscup.composeapp.generated.resources.locale_vietnameseContributor
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
    ),
    Croatian(
        code = "hr",
        emoji = "\uD83C\uDDED\uD83C\uDDF7",
        localisedName = Res.string.locale_croatian,
        contributorName = Res.string.locale_croatianContributor,
    ),
    Czech(
        code = "cs",
        emoji = "\uD83C\uDDE8\uD83C\uDDFF",
        localisedName = Res.string.locale_czech,
        contributorName = Res.string.locale_czechContributor,
    ),
    Danish(
        code = "da",
        emoji = "\uD83C\uDDE9\uD83C\uDDF0",
        localisedName = Res.string.locale_danish,
        contributorName = Res.string.locale_danishContributor,
    ),
    Dutch(
        code = "nl",
        emoji = "\uD83C\uDDF3\uD83C\uDDF1",
        localisedName = Res.string.locale_dutch,
        contributorName = Res.string.locale_dutchContributor,
    ),
    Filipino(
        code = "fil",
        emoji = "\uD83C\uDDF5\uD83C\uDDED",
        localisedName = Res.string.locale_filipino,
        contributorName = Res.string.locale_filipinoContributor,
    ),
    Finnish(
        code = "fi",
        emoji = "\uD83C\uDDEB\uD83C\uDDEE",
        localisedName = Res.string.locale_finnish,
        contributorName = Res.string.locale_finnishContributor,
    ),
    French(
        code = "fr",
        emoji = "\uD83C\uDDEB\uD83C\uDDF7",
        localisedName = Res.string.locale_french,
        contributorName = Res.string.locale_frenchContributor,
    ),
    German(
        code = "de",
        emoji = "\uD83C\uDDE9\uD83C\uDDEA",
        localisedName = Res.string.locale_german,
        contributorName = Res.string.locale_germanContributor,
    ),
    Greek(
        code = "el",
        emoji = "\uD83C\uDDEC\uD83C\uDDF7",
        localisedName = Res.string.locale_greek,
        contributorName = Res.string.locale_greekContributor,
    ),
    Hungarian(
        code = "hu",
        emoji = "\uD83C\uDDED\uD83C\uDDFA",
        localisedName = Res.string.locale_hungarian,
        contributorName = Res.string.locale_hungarianContributor,
    ),
    Indonesian(
        code = "id",
        emoji = "\uD83C\uDDEE\uD83C\uDDE9",
        localisedName = Res.string.locale_indonesian,
        contributorName = Res.string.locale_indonesianContributor,
    ),
    Italy(
        code = "it",
        emoji = "\uD83C\uDDEE\uD83C\uDDF9",
        localisedName = Res.string.locale_italian,
        contributorName = Res.string.locale_italianContributor,
    ),
    Japanese(
        code = "ja",
        emoji = "\uD83C\uDDEF\uD83C\uDDF5",
        localisedName = Res.string.locale_japanese,
        contributorName = Res.string.locale_japaneseContributor,
    ),
    Lao(
        code = "lo",
        emoji = "\uD83C\uDDF1\uD83C\uDDE6",
        localisedName = Res.string.locale_lao,
        contributorName = Res.string.locale_laoContributor,
    ),
    Persian(
        code = "fa",
        emoji = "\uD83C\uDDEE\uD83C\uDDF7",
        localisedName = Res.string.locale_persian,
        contributorName = Res.string.locale_persianContributor,
    ),
    Polish(
        code = "pl",
        emoji = "\uD83C\uDDF5\uD83C\uDDF1",
        localisedName = Res.string.locale_polish,
        contributorName = Res.string.locale_polishContributor,
    ),
    Portuguese(
        code = "pt",
        emoji = "\uD83C\uDDF5\uD83C\uDDF9",
        localisedName = Res.string.locale_portuguese,
        contributorName = Res.string.locale_portugueseContributor,
    ),
    Romanian(
        code = "ro",
        emoji = "\uD83C\uDDF7\uD83C\uDDF4",
        localisedName = Res.string.locale_romanian,
        contributorName = Res.string.locale_romanianContributor,
    ),
    Russian(
        code = "ru",
        emoji = "\uD83C\uDDF7\uD83C\uDDFA",
        localisedName = Res.string.locale_russian,
        contributorName = Res.string.locale_russianContributor,
    ),
    Slovak(
        code = "sk",
        emoji = "\uD83C\uDDF8\uD83C\uDDF0",
        localisedName = Res.string.locale_slovak,
        contributorName = Res.string.locale_slovakContributor,
    ),
    Spanish(
        code = "es",
        emoji = "\uD83C\uDDEA\uD83C\uDDF8",
        localisedName = Res.string.locale_spanish,
        contributorName = Res.string.locale_spanishContributor,
    ),
    Thai(
        code = "th",
        emoji = "\uD83C\uDDF9\uD83C\uDDED",
        localisedName = Res.string.locale_thai,
        contributorName = Res.string.locale_thaiContributor,
    ),
    Turkish(
        code = "tr",
        emoji = "\uD83C\uDDF9\uD83C\uDDF7",
        localisedName = Res.string.locale_turkish,
        contributorName = Res.string.locale_turkishContributor,
    ),
    Ukrainian(
        code = "uk",
        emoji = "\uD83C\uDDFA\uD83C\uDDE6",
        localisedName = Res.string.locale_ukrainian,
        contributorName = Res.string.locale_ukrainianContributor,
    ),
    Vietnamese(
        code = "vi",
        emoji = "\uD83C\uDDFB\uD83C\uDDF3",
        localisedName = Res.string.locale_vietnamese,
        contributorName = Res.string.locale_vietnameseContributor,
    );

    companion object {
        val Default = Chinese
//        val Default = English
    }
}