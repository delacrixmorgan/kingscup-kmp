package com.delacrixmorgan.kingscup.data.model

import kingscup.composeapp.generated.resources.Res
import kingscup.composeapp.generated.resources.rules_categoriesDescription
import kingscup.composeapp.generated.resources.rules_categoriesEmoji
import kingscup.composeapp.generated.resources.rules_categoriesInfo
import kingscup.composeapp.generated.resources.rules_categoriesLabel
import kingscup.composeapp.generated.resources.rules_chicksDescription
import kingscup.composeapp.generated.resources.rules_chicksEmoji
import kingscup.composeapp.generated.resources.rules_chicksInfo
import kingscup.composeapp.generated.resources.rules_chicksLabel
import kingscup.composeapp.generated.resources.rules_countdownDescription
import kingscup.composeapp.generated.resources.rules_countdownEmoji
import kingscup.composeapp.generated.resources.rules_countdownInfo
import kingscup.composeapp.generated.resources.rules_countdownLabel
import kingscup.composeapp.generated.resources.rules_dudesDescription
import kingscup.composeapp.generated.resources.rules_dudesEmoji
import kingscup.composeapp.generated.resources.rules_dudesInfo
import kingscup.composeapp.generated.resources.rules_dudesLabel
import kingscup.composeapp.generated.resources.rules_heavenDescription
import kingscup.composeapp.generated.resources.rules_heavenEmoji
import kingscup.composeapp.generated.resources.rules_heavenInfo
import kingscup.composeapp.generated.resources.rules_heavenLabel
import kingscup.composeapp.generated.resources.rules_kingDescription
import kingscup.composeapp.generated.resources.rules_kingEmoji
import kingscup.composeapp.generated.resources.rules_kingInfo
import kingscup.composeapp.generated.resources.rules_kingLabel
import kingscup.composeapp.generated.resources.rules_mateDescription
import kingscup.composeapp.generated.resources.rules_mateEmoji
import kingscup.composeapp.generated.resources.rules_mateInfo
import kingscup.composeapp.generated.resources.rules_mateLabel
import kingscup.composeapp.generated.resources.rules_meDescription
import kingscup.composeapp.generated.resources.rules_meEmoji
import kingscup.composeapp.generated.resources.rules_meInfo
import kingscup.composeapp.generated.resources.rules_meLabel
import kingscup.composeapp.generated.resources.rules_neverHaveIEverDescription
import kingscup.composeapp.generated.resources.rules_neverHaveIEverEmoji
import kingscup.composeapp.generated.resources.rules_neverHaveIEverInfo
import kingscup.composeapp.generated.resources.rules_neverHaveIEverLabel
import kingscup.composeapp.generated.resources.rules_questionMasterDescription
import kingscup.composeapp.generated.resources.rules_questionMasterEmoji
import kingscup.composeapp.generated.resources.rules_questionMasterInfo
import kingscup.composeapp.generated.resources.rules_questionMasterLabel
import kingscup.composeapp.generated.resources.rules_rhymeDescription
import kingscup.composeapp.generated.resources.rules_rhymeEmoji
import kingscup.composeapp.generated.resources.rules_rhymeInfo
import kingscup.composeapp.generated.resources.rules_rhymeLabel
import kingscup.composeapp.generated.resources.rules_snakeEyesDescription
import kingscup.composeapp.generated.resources.rules_snakeEyesEmoji
import kingscup.composeapp.generated.resources.rules_snakeEyesInfo
import kingscup.composeapp.generated.resources.rules_snakeEyesLabel
import kingscup.composeapp.generated.resources.rules_swapCupsDescription
import kingscup.composeapp.generated.resources.rules_swapCupsEmoji
import kingscup.composeapp.generated.resources.rules_swapCupsInfo
import kingscup.composeapp.generated.resources.rules_swapCupsLabel
import kingscup.composeapp.generated.resources.rules_tellAJokeDescription
import kingscup.composeapp.generated.resources.rules_tellAJokeEmoji
import kingscup.composeapp.generated.resources.rules_tellAJokeInfo
import kingscup.composeapp.generated.resources.rules_tellAJokeLabel
import kingscup.composeapp.generated.resources.rules_thumbMasterDescription
import kingscup.composeapp.generated.resources.rules_thumbMasterEmoji
import kingscup.composeapp.generated.resources.rules_thumbMasterInfo
import kingscup.composeapp.generated.resources.rules_thumbMasterLabel
import kingscup.composeapp.generated.resources.rules_waterfallDescription
import kingscup.composeapp.generated.resources.rules_waterfallEmoji
import kingscup.composeapp.generated.resources.rules_waterfallInfo
import kingscup.composeapp.generated.resources.rules_waterfallLabel
import kingscup.composeapp.generated.resources.rules_youDescription
import kingscup.composeapp.generated.resources.rules_youEmoji
import kingscup.composeapp.generated.resources.rules_youInfo
import kingscup.composeapp.generated.resources.rules_youLabel
import org.jetbrains.compose.resources.StringResource

sealed class Normal(
    id: String,
    emoji: StringResource,
    label: StringResource,
    description: StringResource,
    info: StringResource
) : Rule(id, emoji, label, description, info) {

    data object King : Rule(
        id = King::class.simpleName.toString(),
        emoji = Res.string.rules_kingEmoji,
        label = Res.string.rules_kingLabel,
        description = Res.string.rules_kingDescription,
        info = Res.string.rules_kingInfo
    )

    data object QuestionMaster : Rule(
        id = QuestionMaster::class.simpleName.toString(),
        emoji = Res.string.rules_questionMasterEmoji,
        label = Res.string.rules_questionMasterLabel,
        description = Res.string.rules_questionMasterDescription,
        info = Res.string.rules_questionMasterInfo
    )

    data object NeverHaveIEver : Rule(
        id = NeverHaveIEver::class.simpleName.toString(),
        emoji = Res.string.rules_neverHaveIEverEmoji,
        label = Res.string.rules_neverHaveIEverLabel,
        description = Res.string.rules_neverHaveIEverDescription,
        info = Res.string.rules_neverHaveIEverInfo
    )

    data object Categories : Rule(
        id = Categories::class.simpleName.toString(),
        emoji = Res.string.rules_categoriesEmoji,
        label = Res.string.rules_categoriesLabel,
        description = Res.string.rules_categoriesDescription,
        info = Res.string.rules_categoriesInfo
    )

    data object SnakeEyes : Rule(
        id = SnakeEyes::class.simpleName.toString(),
        emoji = Res.string.rules_snakeEyesEmoji,
        label = Res.string.rules_snakeEyesLabel,
        description = Res.string.rules_snakeEyesDescription,
        info = Res.string.rules_snakeEyesInfo
    )

    data object Mate : Rule(
        id = Mate::class.simpleName.toString(),
        emoji = Res.string.rules_mateEmoji,
        label = Res.string.rules_mateLabel,
        description = Res.string.rules_mateDescription,
        info = Res.string.rules_mateInfo
    )

    data object Heaven : Rule(
        id = Heaven::class.simpleName.toString(),
        emoji = Res.string.rules_heavenEmoji,
        label = Res.string.rules_heavenLabel,
        description = Res.string.rules_heavenDescription,
        info = Res.string.rules_heavenInfo
    )

    data object Chicks : Rule(
        id = Chicks::class.simpleName.toString(),
        emoji = Res.string.rules_chicksEmoji,
        label = Res.string.rules_chicksLabel,
        description = Res.string.rules_chicksDescription,
        info = Res.string.rules_chicksInfo
    )

    data object Dudes : Rule(
        id = Dudes::class.simpleName.toString(),
        emoji = Res.string.rules_dudesEmoji,
        label = Res.string.rules_dudesLabel,
        description = Res.string.rules_dudesDescription,
        info = Res.string.rules_dudesInfo
    )

    data object ThumbMaster : Rule(
        id = ThumbMaster::class.simpleName.toString(),
        emoji = Res.string.rules_thumbMasterEmoji,
        label = Res.string.rules_thumbMasterLabel,
        description = Res.string.rules_thumbMasterDescription,
        info = Res.string.rules_thumbMasterInfo
    )

    data object Me : Rule(
        id = Me::class.simpleName.toString(),
        emoji = Res.string.rules_meEmoji,
        label = Res.string.rules_meLabel,
        description = Res.string.rules_meDescription,
        info = Res.string.rules_meInfo
    )

    data object You : Rule(
        id = You::class.simpleName.toString(),
        emoji = Res.string.rules_youEmoji,
        label = Res.string.rules_youLabel,
        description = Res.string.rules_youDescription,
        info = Res.string.rules_youInfo
    )

    data object Waterfall : Rule(
        id = Waterfall::class.simpleName.toString(),
        emoji = Res.string.rules_waterfallEmoji,
        label = Res.string.rules_waterfallLabel,
        description = Res.string.rules_waterfallDescription,
        info = Res.string.rules_waterfallInfo
    )

    // Variants
    data object Countdown : Rule(
        id = Countdown::class.simpleName.toString(),
        emoji = Res.string.rules_countdownEmoji,
        label = Res.string.rules_countdownLabel,
        description = Res.string.rules_countdownDescription,
        info = Res.string.rules_countdownInfo
    )

    data object Rhyme : Rule(
        id = Rhyme::class.simpleName.toString(),
        emoji = Res.string.rules_rhymeEmoji,
        label = Res.string.rules_rhymeLabel,
        description = Res.string.rules_rhymeDescription,
        info = Res.string.rules_rhymeInfo
    )

    data object SwapCups : Rule(
        id = SwapCups::class.simpleName.toString(),
        emoji = Res.string.rules_swapCupsEmoji,
        label = Res.string.rules_swapCupsLabel,
        description = Res.string.rules_swapCupsDescription,
        info = Res.string.rules_swapCupsInfo
    )

    data object TellAJoke : Rule(
        id = TellAJoke::class.simpleName.toString(),
        emoji = Res.string.rules_tellAJokeEmoji,
        label = Res.string.rules_tellAJokeLabel,
        description = Res.string.rules_tellAJokeDescription,
        info = Res.string.rules_tellAJokeInfo
    )
}