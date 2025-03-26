package com.delacrixmorgan.kingscup.data.model

import kingscup.composeapp.generated.resources.Res
import kingscup.composeapp.generated.resources.*
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

    class QuestionMaster(
        id: String = "2",
        emoji: StringResource = Res.string.rules_questionMasterEmoji,
        label: StringResource = Res.string.rules_questionMasterLabel,
        description: StringResource = Res.string.rules_questionMasterDescription,
        info: StringResource = Res.string.rules_questionMasterInfo,
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