package com.delacrixmorgan.kingscup.data.model

import kingscup.composeapp.generated.resources.Res
import kingscup.composeapp.generated.resources.rules_kingDescription
import kingscup.composeapp.generated.resources.rules_kingEmoji
import kingscup.composeapp.generated.resources.rules_kingInfo
import kingscup.composeapp.generated.resources.rules_kingLabel
import org.jetbrains.compose.resources.StringResource

sealed class Rules(
    val id: String,
    val emoji: StringResource,
    val label: StringResource,
    val description: StringResource,
    val info: StringResource
) {
    class King(
        id: String = "1",
        emoji: StringResource = Res.string.rules_kingEmoji,
        label: StringResource = Res.string.rules_kingLabel,
        description: StringResource = Res.string.rules_kingDescription,
        info: StringResource = Res.string.rules_kingInfo,
    ) : Rules(id, emoji, label, description, info)
}

//enum class Rules {
//    // Classic
//    King,
//    QuestionMaster,
//    NeverHaveIEver,
//    Categories,
//    SnakeEyes,
//    Mate,
//    Heaven,
//    Chicks,
//    Dudes,
//    ThumbMaster,
//    Me,
//    You,
//    Waterfall,
//
//    // Variants
//    BustARhyme,
//    SwapCup,
//    Countdown,
//    TellAJoke,
//
//    // Jokers
//    Viking,
//    NoFirstNames,
//    NoPointing,
//    TRexArms,
//    BeLikeBilly,
//    IWillTellYouWhat,
//    NoPhones,
//    NoSwearing,
//    Photographer,
//    LastOneLaughing,
//    LeftHand,
//    Toilet;
//}